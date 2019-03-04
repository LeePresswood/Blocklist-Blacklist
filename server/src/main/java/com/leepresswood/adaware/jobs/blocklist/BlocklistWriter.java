package com.leepresswood.adaware.jobs.blocklist;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class BlocklistWriter implements ItemWriter<Blocklist> {

    @Autowired
    public BlocklistRepository repo;

    @Override
    @Transactional
    public void write(List<? extends Blocklist> ips) {
        repo.saveAll(ips);
    }
}
