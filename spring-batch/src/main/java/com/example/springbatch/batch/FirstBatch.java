package com.example.springbatch.batch;

import com.example.springbatch.entity.AfterEntity;
import com.example.springbatch.entity.BeforeEntity;
import com.example.springbatch.repository.AfterRepository;
import com.example.springbatch.repository.BeforeRepository;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FirstBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final BeforeRepository beforeRepository;
    private final AfterRepository afterRepository;

    public FirstBatch(JobRepository jobRepository,
        PlatformTransactionManager platformTransactionManager, BeforeRepository beforeRepository,
        AfterRepository afterRepository) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.beforeRepository = beforeRepository;
        this.afterRepository = afterRepository;
    }

    @Bean
    public Job firstJob() {
        return new JobBuilder("firstJob", jobRepository)
            .start(firstStep())
            .build();
    }

    /**
     * Step 설정
     * 1. Reader : 데이터를 읽어온다. (10개씩 읽도록 설정)
     * 2. processor : 데이터를 처리한다.
     * 3. writer : 처리한 데이터를 저장한다.
     * @return
     */
    @Bean
    public Step firstStep() {
        return new StepBuilder("firstStep", jobRepository)
            .<BeforeEntity, AfterEntity>chunk(10, platformTransactionManager)
            .reader(beforeReader())
            .processor(middleProcessor())
            .writer(afterWriter())
            .build();
    }

    /**
     * JPA를 이용하여 데이터들을 findAll한다
     *
     * @return
     */
    @Bean
    public RepositoryItemReader<BeforeEntity> beforeReader() {
        return new RepositoryItemReaderBuilder<BeforeEntity>()
            .name("beforeReader")
            .pageSize(10)
            .methodName("findAll")
            .repository(beforeRepository)
            .sorts(Map.of("id", Sort.Direction.ASC))
            .build();
    }

    /**
     * BeforeEntity 값들을 AfterEntity에 덮어 씌우는 작업
     * @return
     */
    @Bean
    public ItemProcessor<BeforeEntity, AfterEntity> middleProcessor() {
        return item -> {
            AfterEntity afterEntity = new AfterEntity();
            afterEntity.setUsername(item.getUsername());
            return afterEntity;
        };
    }

    /**
     * JPA의 save 메소드를 호출하여 DB에 저장 쿼리문 요청
     * @return
     */
    @Bean
    public RepositoryItemWriter<AfterEntity> afterWriter() {
        return new RepositoryItemWriterBuilder<AfterEntity>()
            .repository(afterRepository)
            .methodName("save")
            .build();
    }



}
