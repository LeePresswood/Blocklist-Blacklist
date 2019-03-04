package com.leepresswood.adaware;

import com.leepresswood.adaware.job.blocklist.Blocklist;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlocklistIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnFullList() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(49999);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(1);

        Assertions.assertThat(body.getBody()[49998].id)
                  .isEqualTo(49999);
    }

    @Test
    public void shouldReturnFullListWithBadParams() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?abc=123", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(49999);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(1);

        Assertions.assertThat(body.getBody()[49998].id)
                  .isEqualTo(49999);
    }

    @Test
    public void shouldReturnPartialListBasedOnStart() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=49998", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(2);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(49998);

        Assertions.assertThat(body.getBody()[1].id)
                  .isEqualTo(49999);
    }

    @Test
    public void shouldReturnPartialListBasedOnSize() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?size=3", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(3);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(1);

        Assertions.assertThat(body.getBody()[1].id)
                  .isEqualTo(2);

        Assertions.assertThat(body.getBody()[2].id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnPartialListBasedOnStartAndSize() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=40&size=3", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(3);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(40);

        Assertions.assertThat(body.getBody()[1].id)
                  .isEqualTo(41);

        Assertions.assertThat(body.getBody()[2].id)
                  .isEqualTo(42);
    }

    @Test
    public void shouldReturnDefaultPartialListBasedOnTooLargeStartAndSize() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=50000&size=3", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(0);
    }

    @Test
    public void shouldReturnDefaultPartialListBasedOnStartAndTooLargeSize() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=49997&size=2222", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(3);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(49997);

        Assertions.assertThat(body.getBody()[1].id)
                  .isEqualTo(49998);

        Assertions.assertThat(body.getBody()[2].id)
                  .isEqualTo(49999);
    }

    @Test
    public void shouldReturnDefaultPartialListBasedOnStartAnd0Size() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=1&size=0", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(0);
    }

    @Test
    public void shouldReturnDefaultPartialListBasedOn0Start() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=0", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(49999);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(1);

        Assertions.assertThat(body.getBody()[49998].id)
                  .isEqualTo(49999);
    }

    @Test
    public void shouldReturnPartialListBasedOnStartAndSizeAndBadParams() {
        ResponseEntity<Blocklist[]> body = this.restTemplate.getForEntity("/ips?start=1&size=3&abc=123", Blocklist[].class);

        Assertions.assertThat(body.getBody().length)
                  .isEqualTo(3);

        Assertions.assertThat(body.getBody()[0].id)
                  .isEqualTo(1);

        Assertions.assertThat(body.getBody()[1].id)
                  .isEqualTo(2);

        Assertions.assertThat(body.getBody()[2].id)
                  .isEqualTo(3);
    }

    @Test
    public void shouldReturnErrorForStringStart() {
        ResponseEntity<Map> body = this.restTemplate.getForEntity("/ips?start=abc", Map.class);

        Assertions.assertThat(body.getStatusCode())
                  .isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(body.getBody().get("reason"))
                  .isEqualTo("Bad Request");
    }

    @Test
    public void shouldReturnErrorForStringSize() {
        ResponseEntity<Map> body = this.restTemplate.getForEntity("/ips?size=abc", Map.class);

        Assertions.assertThat(body.getStatusCode())
                  .isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(body.getBody().get("reason"))
                  .isEqualTo("Bad Request");
    }
}