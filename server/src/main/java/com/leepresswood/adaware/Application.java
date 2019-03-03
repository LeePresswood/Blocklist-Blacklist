package com.leepresswood.adaware;

import com.leepresswood.adaware.jobs.Blocklist;
import com.leepresswood.adaware.jobs.BlocklistReader;
import com.leepresswood.adaware.jobs.BlocklistWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableBatchProcessing
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    BlocklistWriter writer() {
        return new BlocklistWriter();
    }

    @Bean
    BlocklistReader reader() {
        return new BlocklistReader();
    }

    @Bean
    public Job blocklistJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Blocklist, Blocklist>chunk(1)
                .reader(reader())
                .writer(writer())
                .build();

        Job job = jobBuilderFactory.get("blocklistJob")
                                   .incrementer(new RunIdIncrementer())
                                   .start(step)
                                   .build();

        return job;

    }
}