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

    List<Blocklist> list = Lists.newArrayList(
            new Blocklist(1),
            new Blocklist(2),
            new Blocklist(3)
    );

    @Test
    public void shouldReturnFullList() {
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

    @Test
    public void shouldReturnPartialListByStart() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(2, -1);

        Assertions.assertThat(response.size())
                  .isEqualTo(2);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListBySize() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(-1, 2);

        Assertions.assertThat(response.size())
                  .isEqualTo(2);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);
    }

    @Test
    public void shouldReturnPartialListByStartAndSize() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(2, 1);

        Assertions.assertThat(response.size())
                  .isEqualTo(1);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(2);
    }

    @Test
    public void shouldReturnPartialListByStartAndSizeOutsideArray() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(2, 10);

        Assertions.assertThat(response.size())
                  .isEqualTo(2);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListByStartOutsideArray() {
       MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(-100, -1);

        Assertions.assertThat(response.size())
                  .isEqualTo(3);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(2).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListByStartOutsideArrayAndPositive() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(100, -1);

        Assertions.assertThat(response.size())
                  .isEqualTo(3);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(2).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListByStartAndSizeBothOutsideArrayAndPositive() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(100, 10);

        Assertions.assertThat(response.size())
                  .isEqualTo(3);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(2).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListBy0Start() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(0, -1);

        Assertions.assertThat(response.size())
                  .isEqualTo(3);

        Assertions.assertThat(response.get(0).id)
                  .isEqualTo(1);

        Assertions.assertThat(response.get(1).id)
                  .isEqualTo(2);

        Assertions.assertThat(response.get(2).id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListBy0Size() {
        MockitoAnnotations.initMocks(this);
        service = new BlocklistService();
        service.repo = repo;

        Mockito.when(repo.findAll())
               .thenReturn(list);

        List<Blocklist> response = service.getAllBlockedIps(-1, 0);

        Assertions.assertThat(response.size())
                  .isEqualTo(0);
    }
}
