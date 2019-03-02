package com.leepresswood.adaware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class InfoController {
    @Autowired
    private InfoService service;

    @CrossOrigin
    @GetMapping(value = "/connection-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Whois> getConnectionInfo(@RequestParam(value = "ip", defaultValue = "") String ip)
            throws InterruptedException, ExecutionException {
        if (ip == null || ip.length() == 0) {
            throw new InvalidParameterException();
        }

        Whois response = service.getIpInfo(ip);

        if (response == null) {
            throw new InvalidParameterException();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({ InvalidParameterException.class })
    public ResponseEntity<Map> userException() {
        return new ResponseEntity<>(Collections.singletonMap("reason", "Bad Request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InterruptedException.class, ExecutionException.class })
    public ResponseEntity<Map> serverException() {
        return new ResponseEntity<>(Collections.singletonMap("reason", "Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}