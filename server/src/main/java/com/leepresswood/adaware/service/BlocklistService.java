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

    public List<Blocklist> getAllBlockedIps(int start, int size) {
        List<Blocklist> allPosts = repo.findAll();

        if (size < 0) {
            size = 999999999;
        }

        int startIndex = start - 1;
        if (startIndex < 0 || startIndex > allPosts.size()) {
            startIndex = 0;
        }

        int endIndex = startIndex + size;
        if(endIndex < 0 || endIndex > allPosts.size()){
            endIndex = allPosts.size();
        }

        return repo.findAll().subList(startIndex, endIndex);
    }
}
