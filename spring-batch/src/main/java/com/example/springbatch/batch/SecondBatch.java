package com.example.springbatch.batch;

import com.example.springbatch.entity.WinEntity;
import com.example.springbatch.repository.WinRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SecondBatch {

    private final JobRepository jobRepository;
    private final WinRepository winRepository;
    private final PlatformTransactionManager platformTransactionManager;


    public SecondBatch(JobRepository jobRepository, WinRepository winRepository,
        PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.winRepository = winRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Job secondJob() {
        return new JobBuilder("secondJob", jobRepository)
            .start()
            .build();
    }


}
