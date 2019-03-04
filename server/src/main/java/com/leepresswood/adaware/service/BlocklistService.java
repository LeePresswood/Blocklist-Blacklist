package com.leepresswood.adaware.service;

import com.leepresswood.adaware.job.blocklist.Blocklist;
import com.leepresswood.adaware.job.blocklist.BlocklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocklistService {

    @Autowired
    public BlocklistRepository repo;

    public List<Blocklist> getAllBlockedIps() {
        return repo.findAll();
    }
}
