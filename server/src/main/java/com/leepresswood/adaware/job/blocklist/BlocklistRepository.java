package com.leepresswood.adaware.job.blocklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocklistRepository extends JpaRepository<Blocklist, Long> {
}
