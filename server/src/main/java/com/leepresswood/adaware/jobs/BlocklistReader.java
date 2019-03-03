package com.leepresswood.adaware.jobs;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class BlocklistReader extends FlatFileItemReader<Blocklist> {
    public BlocklistReader() {
        super();

        setResource(new ClassPathResource("ipv4-block.csv"));

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("network", "geoname_id", "registered_country_geoname_id", "represented_country_geoname_id", "is_anonymous_proxy", "is_satellite_provider");
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<Blocklist> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Blocklist.class);

        DefaultLineMapper<Blocklist> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);

        setLinesToSkip(1);
    }
}
