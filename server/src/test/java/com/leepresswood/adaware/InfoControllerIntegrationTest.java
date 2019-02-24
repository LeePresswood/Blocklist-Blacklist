package com.leepresswood.adaware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnWhoisForGoogle() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ResponseEntity<Whois> body = this.restTemplate.getForEntity("/connection-info?ip=www.google.com", Whois.class);

        assertThat(body.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getBody().state).isEqualTo("CA");
        assertThat(body.getBody().country).isEqualTo("US");
    }

    @Test
    public void shouldReturnWhoisForFacebook() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ResponseEntity<Whois> body = this.restTemplate.getForEntity("/connection-info?ip=www.facebook.com", Whois.class);

        assertThat(body.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getBody().state).isEqualTo("CA");
        assertThat(body.getBody().country).isEqualTo("US");
    }

    @Test
    public void shouldReturnWhoisForMicrosoft() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ResponseEntity<Whois> body = this.restTemplate.getForEntity("/connection-info?ip=www.microsoft.com", Whois.class);

        assertThat(body.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getBody().state).isEqualTo("WA");
        assertThat(body.getBody().country).isEqualTo("US");
    }

    @Test
    public void shouldReturnWhoisForBaidu() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ResponseEntity<Whois> body = this.restTemplate.getForEntity("/connection-info?ip=www.baidu.com", Whois.class);

        assertThat(body.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.getBody().state).isEqualTo("Beijing");
        assertThat(body.getBody().country).isEqualTo("CN");
    }

    @Test
    public void shouldReturnErrorForNonsense() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ResponseEntity<Map> body = this.restTemplate.getForEntity("/connection-info?ip=AAFSSADADASDSAD", Map.class);

        assertThat(body.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(body.getBody().get("reason")).isEqualTo("Bad Request");
    }

}