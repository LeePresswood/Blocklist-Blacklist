package com.leepresswood.adaware;

import com.leepresswood.adaware.jobs.blocklist.Blocklist;
import com.leepresswood.adaware.jobs.blocklist.BlocklistReader;
import com.leepresswood.adaware.jobs.blocklist.BlocklistWriter;
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
    BlocklistWriter blocklistWriter() {
        return new BlocklistWriter();
    }

    @Bean
    BlocklistReader blocklistReader() {
        return new BlocklistReader();
    }

    @Bean
    public Job blocklistJob() {

//        Step countryStep = stepBuilderFactory.get("step-1")
//                .<Blocklist, Blocklist>chunk(1)
//                .reader(blocklistReader())
//                .writer(blocklistWriter())
//                .build();

        Step blocklistStep = stepBuilderFactory.get("step-2")
                .<Blocklist, Blocklist>chunk(1)
                .reader(blocklistReader())
                .writer(blocklistWriter())
                .build();

        Job job = jobBuilderFactory.get("blocklistJob")
                                   .incrementer(new RunIdIncrementer())
                                   .start(blocklistStep)
                                   .build();

        return job;

    }
}