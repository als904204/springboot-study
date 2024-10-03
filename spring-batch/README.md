# Spring-Batch
- https://spring.io/guides/gs/batch-processing/

## Spring-Batch 란?
> 대량의 데이터를 처리하고 배치 작업을 실행하는 프레임워크
> 
> 배치 작업은 비동기적으로 실행된다.
> 
> 예) 매일 밤 사용자 DB를 조회 해 권한 상승을 한다거나 대용량 데이터를 다른 시스템을 이전하는 작업

## 주요 개념
- Job : 배치 처리의 전체 과정, 한 개 또는 여러 개의 Step로 구성
  - 전체적인 흐름과 설정을 담당
  - 어떤 Step 들이 실행될 것인지, 어떤 순서로, 어떤 조건에서 실패하거나 성공할 것인지 정의
- Step: Job의 독립적인 단계, ItemReader,ItemProcessor,ItemWriter로 구성
  - chunk(데이터 묶음) 단위로 처리
  - ItemReader: 데이터 소스에서 단일 항목 또는 데이터 묶음을 읽음
  - ItemProcessor: ItemReader를 통해 읽어 들인 데이터를 변환하거나 처리
  - ItemWriter: 처리된 데이터를 DB 에 저장

---
# 시작하기

## 0. 요구 사항
- resources/[sample-data.csv](src%2Fmain%2Fresources%2Fsample-data.csv)
  파일을 읽어 소문자로 된 값들을 모두 대문자로 변경한다


- resources/schema-all.sql
```sql
DROP TABLE people IF EXISTS;

CREATE TABLE people (
                 person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
                 first_name VARCHAR(20),
                 last_name VARCHAR(20)
);
```
> SpringBoot 가 시작될 때, schema-*.sql 로 시작하는 파일을 자동 실행 함


## 1. 의존성
- build.gradle
```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-batch'
}
```

## 2.코드 예제 
### 2-1. Domain (Person.java)
- 예제 Person 클래스(이 클래스를 대상으로 Batch 할것 임)
```java
public record Person(String firstName, String lastName) {

}
```

### 2-2. Processor (PersonItemProcessor.java)
- Batch 에서 실행할 작업을 정의하는 Process 클래스
- 여기선 소문자 to 대문자로 변환하는 작업
```java
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Person process(final Person person) {
    final String firstName = person.firstName().toUpperCase();
    final String lastName = person.lastName().toUpperCase();

    final Person transformedPerson = new Person(firstName, lastName);

    log.info("Converting (" + person + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }

}
```
### 2-3. Reader,Writer (BatchConfiguration.java)
- Reader : 데이터 읽어올 파일,속성,클래스 정의
- Writer : DB 에 저장할 구조 정의
- Processor 클래스 빈 등록
```java
@Configuration
public class BatchConfiguration {
    
    // Reader
    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
            .name("personItemReader")
            .resource(new ClassPathResource("sample-data.csv"))
            .delimited()
            .names("firstName", "lastName")
            .targetType(Person.class)
            .build();
    }

    // Writer
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(dataSource)
            .beanMapped()
            .build();
    }
    
    // Processor 빈 등록 
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }
}
```
### 2-4 Job,Step (BatchConfiguration.java)
- Job : 배치 처리의 전체 과정(처리 흐름), 한 개 또는 여러 개의 Step로 구성(여기선 한 개의 Step 만 함)
- Step : Job의 독립적인 단계, ItemReader,ItemProcessor,ItemWriter로 구성
```java
@Configuration
public class BatchConfiguration {
    // 2.3 Codes...

    // Job 구성, 전체적인 Step 흐름 설정
    // step1 실행하라
    @Bean
    public Job importUserJob(JobRepository jobRepository,Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
            .listener(listener)
            .start(step1)
            .build();
    }

    // Step 정의 chunk(묶음) 단위로 처리
    // 한 번에 3개 데이터 처리하도록 설정
    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
        FlatFileItemReader<Person> reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
        return new StepBuilder("step1", jobRepository)
            .<Person, Person> chunk(3, transactionManager)
            .reader(reader)         // 데이터 읽음
            .processor(processor)   // 데이터 가공
            .writer(writer)         // 데이터 저장
            .build();
    }

}
```

### 2-5 Listener (JobCompletionNotificationListener.java)
- 작업이 완료될 때 알림
```java
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // 작업이(Job) 완료된 후 afterJob 호출
  // 배치 상태(BatchStatus.COMPLETED) 성공이라면 DB 조회 후 출력
  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      jdbcTemplate
          .query("SELECT first_name, last_name FROM people", new DataClassRowMapper<>(Person.class))
          .forEach(person -> log.info("Found <{{}}> in the database.", person));
    }
  }
}
```

### 2.6 적용하기(MainClassApplication.java)

```java
@SpringBootApplication
public class BatchProcessingApplication {
  public static void main(String[] args) {
    System.exit(SpringApplication.exit(SpringApplication.run(BatchProcessingApplication.class, args)));
  }
}

```

## 결과
```java
2024-02-04T23:19:42.274+09:00  INFO 75404 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-02-04T23:19:42.383+09:00  INFO 75404 --- [           main] c.e.springbatch.SpringBatchApplication   : Started SpringBatchApplication in 0.593 seconds (process running for 1.026)
2024-02-04T23:19:42.385+09:00  INFO 75404 --- [           main] o.s.b.a.b.JobLauncherApplicationRunner   : Running default command line with: []
2024-02-04T23:19:42.401+09:00  INFO 75404 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=importUserJob]] launched with the following parameters: [{}]
2024-02-04T23:19:42.411+09:00  INFO 75404 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
2024-02-04T23:19:42.419+09:00  INFO 75404 --- [           main] c.e.s.person.PersonItemProcessor         : Converting (Person[firstName=Jill, lastName=Doe]) into (Person[firstName=JILL, lastName=DOE])
2024-02-04T23:19:42.419+09:00  INFO 75404 --- [           main] c.e.s.person.PersonItemProcessor         : Converting (Person[firstName=Joe, lastName=Doe]) into (Person[firstName=JOE, lastName=DOE])
2024-02-04T23:19:42.419+09:00  INFO 75404 --- [           main] c.e.s.person.PersonItemProcessor         : Converting (Person[firstName=Justin, lastName=Doe]) into (Person[firstName=JUSTIN, lastName=DOE])
2024-02-04T23:19:42.424+09:00  INFO 75404 --- [           main] c.e.s.person.PersonItemProcessor         : Converting (Person[firstName=Jane, lastName=Doe]) into (Person[firstName=JANE, lastName=DOE])
2024-02-04T23:19:42.424+09:00  INFO 75404 --- [           main] c.e.s.person.PersonItemProcessor         : Converting (Person[firstName=John, lastName=Doe]) into (Person[firstName=JOHN, lastName=DOE])
2024-02-04T23:19:42.426+09:00  INFO 75404 --- [           main] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 14ms
2024-02-04T23:19:42.427+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : !!! JOB FINISHED! Time to verify the results
2024-02-04T23:19:42.428+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : Found <{Person[firstName=JILL, lastName=DOE]}> in the database.
2024-02-04T23:19:42.428+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : Found <{Person[firstName=JOE, lastName=DOE]}> in the database.
2024-02-04T23:19:42.428+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : Found <{Person[firstName=JUSTIN, lastName=DOE]}> in the database.
2024-02-04T23:19:42.428+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : Found <{Person[firstName=JANE, lastName=DOE]}> in the database.
2024-02-04T23:19:42.428+09:00  INFO 75404 --- [           main] .e.s.c.JobCompletionNotificationListener : Found <{Person[firstName=JOHN, lastName=DOE]}> in the database.
2024-02-04T23:19:42.430+09:00  INFO 75404 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=importUserJob]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 20ms
2024-02-04T23:19:42.432+09:00  INFO 75404 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-02-04T23:19:42.446+09:00  INFO 75404 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```

> SpringBoot 가 실행되고, Batch 작업이 실행된 다음에 성공적으로 작업이 완료되었다는 로그 확인

---

# JPA 성능 문제와 JDBC
스프링 배치 read와 write 부분을 JPA로 구성할 경우 JDBC 대비 처리 속도가 엄청나게 저하 된다.

Reader의 경우 큰 영향을 미치진 않지만,Wirte는 다음과 같은 이유로 성능 저하가 발생한다.

## 이유
Entity의 ID 생성 전략은 보통 IDENTITY로 설정한다. 과정은 다음과 같다.
- ID값은 DB에 INSERT 후 결정된다. 즉 DB에 레코드가 삽입이 된 그 레코드의 ID값을 알 수 있다
- JPA는 엔티티를 영속성 컨텍스트에 넣어 관리한다
  - JPA가 새 엔티티를 영속성 컨텍스트에 등록한다.
  - 엔티티를 DB에 INSERT하는 쿼리문이 실행된다.
  - DB는 AUTO_INCREMENT를 통해 새 ID값 만들 저장한다.
  - JPA는 **새로 생성된 ID 값을** 가져와야 한다.
    - 이 때 JDBC의 getGeneratedKeys() 를 이용하여 DB생성된 ID 값을 조회한다.
  - 조회된 ID값을 영속성 켄텍스트 해당 엔티티에 반영한
> 즉 IDENTITY 전략은 insert 쿼리마다 즉시 ID 값을 조회하는 추가적인 작업이 필요하다
> 
> JDBC 기반으로 작성하게 된다면 청크로 설정한 값이 모이게 된다면 bulk 쿼리로 단 1번의 insert가 수행되지만
> JPA의 IDENTITY 전략 때문에 bulk 쿼리 대신 각각의 수만큼 insert가 수행된다


