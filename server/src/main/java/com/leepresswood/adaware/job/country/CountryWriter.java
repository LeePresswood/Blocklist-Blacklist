package com.leepresswood.adaware.job.country;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CountryWriter implements ItemWriter<Country> {

    @Autowired
    public CountryRepository repo;

    @Override
    @Transactional
    public void write(List<? extends Country> ips) {
        repo.saveAll(ips);
    }
}
