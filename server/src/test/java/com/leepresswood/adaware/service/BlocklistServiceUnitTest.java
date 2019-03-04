package com.leepresswood.adaware.service;

import com.leepresswood.adaware.job.blocklist.Blocklist;
import com.leepresswood.adaware.job.blocklist.BlocklistRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BlocklistServiceUnitTest {

    @Mock
    BlocklistRepository repo;

    @InjectMocks
    BlocklistService service;

    @Test
    public void shouldReturnFullList() {
        List<Blocklist> list = Lists.newArrayList(
                new Blocklist(1),
                new Blocklist(2),
                new Blocklist(3)
        );

        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(-1, -1);

        Assertions.assertThat(response.size())
                  .isEqualTo(3);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(2).id)
                  .isEqualTo(3);
    }
}
