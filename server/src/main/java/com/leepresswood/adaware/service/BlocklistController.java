package com.leepresswood.adaware.service;

import com.leepresswood.adaware.job.blocklist.Blocklist;
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
import java.util.List;
import java.util.Map;

@Controller
public class BlocklistController {

    @Autowired
    private BlocklistService service;

    @CrossOrigin
    @GetMapping(value = "/ips", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blocklist>> getAllIps(
            @RequestParam(value = "start", defaultValue = "-1", required = false) int start,
            @RequestParam(value = "size", defaultValue = "-1", required = false) int size
    ) throws InvalidParameterException {
        List<Blocklist> response = service.getAllBlockedIps(start, size);

        if (response == null) {
            throw new InvalidParameterException();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({ InvalidParameterException.class })
    public ResponseEntity<Map> userException() {
        return new ResponseEntity<>(Collections.singletonMap("reason", "Bad Request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Map> serverException() {
        return new ResponseEntity<>(Collections.singletonMap("reason", "Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}