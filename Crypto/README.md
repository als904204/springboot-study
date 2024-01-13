# JPA AttributeConverter 인터페이스를 사용하여 자동으로 변환된 값 바인딩 

## 예시 (암호화 & 복호화)

### 개요
- DB에 어떤 값을 저장할 때 전/후처리가 필요한 요구사항이 있을 수 있다. 예를 들어, 사용자의 개인 정보를 암호화해서 저장하는 경우

예시)
- Member.class


```java
@Builder
@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 1. 데이터 베이스에 저장될 때 암호화
     * 2. 데이터 베이스에서 값을 읽어올 때 복호화
     * */
    private String phone;
    private String email;
    
}
```

- Member가 DB에 저장 될 때, 핸드폰 번호화 이메일은 개인정보 이므로 암호화해서 저장하고 조회할 땐 복호화해서 조회한다.

---

# 1) AttributeConverter를 사용하지 않을 경우
- Service 레벨에서 직접 암호화/복호화 로직 작성

```java
@Transactional
public Long create(MemberCreateRequest createRequest) {
	String phoneRequest = createRequestDto.getPhone();
    String emailRequest = createRequestDto.getEmail();
    
	// Entity화 이전에 암호화 직접 수행
	String phone = "~~ 암호화 모듈로 암호화 수행";
	String email = "~~ 암호화 모듈로 암호화 수행";
    
    
	// 암호화된 phone,email 값을 Entity에 바인딩
 	Member member = Member.builder()
                            .phone(phone)
                            .email(email)
                            .build();
            
    return memberRepository.save(member).getId();
}
```

### 문제점
1. Member 를 생성하기 전에 매번 암호화 코드를 불러와 적용한다
2. Member 를 조회할 때 마다 복호화 코드를 불러와 적용한다 
3. 언제 암호화될지 로직마다 다르다
4. 실수로 암호화/복호화 하지 않고 저장/조회 할 수 있다

### 해결법
- JPA에서 제공하는 AttributeConverter를 사용
- AttributeConverter를 사용하면 DB에 '요청을 날리기 전' / 'DB에 저장된 값을 불러올 때' 자동으로 값을 변환

---

# 2) AttributeConverter 사용


- AttributeConverter 인터페이스 
```java
/**
 *  <X> : 엔티티의 속성 타입
 *  <Y> : 데이터베이스 컬럼 타입
 */
public interface AttributeConverter<X,Y> {

    
    public Y convertToDatabaseColumn (X attribute);

    public X convertToEntityAttribute (Y dbData);

}
```

- 첫 번째 제네릭 X는 Entity의 Field 자료형, 두 번째 제네릭 Y는 Database Column 자료형


- Y convertToDatabaseColumn (X attribute) : DB에 요청을 전송할 때 수행
  X convertToEntityAttribute (Y dbData) : DB에서 불러온 값을 Entity 필드에 바인딩할 때 수행

### 구현
```java
@Converter
@RequiredArgsConstructor
public class CryptoConverter implements AttributeConverter<String, String> {


    private final EncryptionUtil encryptionUtil;

    /*
     * 데이터베이스에 요청을 보낼 때 (암호화)
     * String phone -> varchar phone
     * String email -> varchar email
     * */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptionUtil.encrypt(attribute);
    }

    /*
     * 데이터베이스에서 값을 읽어올 때 (복호화)
     * varchar phone -> String phone
     * varchar email -> String email
     * */
    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptionUtil.decrypt(dbData);
    }
}
```

- convertToDatabaseColumn
1. convertToDatabaseColumn 메서드는 데이터베이스에 요청을 보낼 때 실행
2. String attribute 파라미터에서 Entity 필드의 값을 받아오고, 암호화 모듈을 통해 암호화를 진행한 뒤 반환해준다. 이렇게 반환해 준 값이 실제 DB에 저장된다

- convertToEntityAttribute
1.  데이터베이스에서 불러온 값을 Entity에 바인딩해줄 때 동작하며 해당 메서드에서 반환된 값이 Converter를 적용한 Entity 필드에 바인딩

###  Converter 적용하기

```java
public class Member {
    
    // ...
    @Convert(converter = CryptoConverter.class)
    private String phone;
    @Convert(converter = CryptoConverter.class)
    private String email;
    // ...
}

```

### 실제 jpa 쿼리문 확인해주는 라이브러리
```groovy
dependencies {
	implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.1'
}
```

### 테스트 코드

```java
   @DisplayName("암호화 저장/복호화")
@Test
    void crypto() {
final String phone = "010-1234-1234";
final String email = "example@hello.com";

        Member member = Member.builder()
        .phone(phone)
        .email(email)
        .build();
        System.out.println("=====저장 START======");
        memberRepository.save(member);
        System.out.println("=====저장 END======");



        System.out.println("=====이메일 조회 START======");
        Member findByEmail = memberRepository.findByEmail(email);
        assertThat(member.getEmail()).isEqualTo(findByEmail.getEmail());
        System.out.println("=====이메일 조회 END======");

        System.out.println("=====폰번호 조회 START======");
        Member findByPhone = memberRepository.findByPhone(phone);
        assertThat(member.getPhone()).isEqualTo(findByPhone.getPhone());
        System.out.println("=====폰번호 조회 END======");

        }

```
```text
=====1. 저장 START======
insert into member (email,phone,id) values ('daHV9uwzyspKq7Qo4QgLpVBsaP5uS+n2no9M0AGUQNo=','EtjYum8Ww3KgLPBqkcC4bw==',default);
=====저장 END======

=====2. 이메일 조회 START======
select m1_0.id,m1_0.email,m1_0.phone from member m1_0 where m1_0.email='daHV9uwzyspKq7Qo4QgLpVBsaP5uS+n2no9M0AGUQNo=';
=====이메일 조회 END======

=====3. 폰번호 조회 START======
select m1_0.id,m1_0.email,m1_0.phone from member m1_0 where m1_0.phone='EtjYum8Ww3KgLPBqkcC4bw==';
=====폰번호 조회 END======
```

1. 저장 시 암호화 된 값들로 insert 확인 
2. 이메일로 조회 시 암호화된 이메일을 where 절에 사용하여 가져옴
3. 폰번호 조회시 암호화된 폰 번호로 where 절을 사용하여 가져옴 

---
# 정리
- AttributeConverter를 사용하여 암호화 복호화 반복되는 코드를 개선할 수 있다.

1. Member email 저장 -> AttributeConverter가 암호화 진행 -> DB INSERT
2. Member email 조회 -> where 절에 암호화된 email로 조회 -> AttributeConverter가 복호화 후 객체에 바인딩


