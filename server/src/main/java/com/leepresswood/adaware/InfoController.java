package com.leepresswood.adaware;

import org.springframework.stereotype.Controller;

@Controller
public class InfoController {
//    @Autowired
//    private InfoService service;
//
//    @CrossOrigin
//    @GetMapping(value = "/connection-info", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Whois> getConnectionInfo(@RequestParam(value = "ip", defaultValue = "") String ip)
//            throws InterruptedException, ExecutionException {
//        if (ip == null || ip.length() == 0) {
//            throw new InvalidParameterException();
//        }
//
//        Whois response = service.getIpInfo(ip);
//
//        if (response == null) {
//            throw new InvalidParameterException();
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @ExceptionHandler({ InvalidParameterException.class })
//    public ResponseEntity<Map> userException() {
//        return new ResponseEntity<>(Collections.singletonMap("reason", "Bad Request"), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler({ InterruptedException.class, ExecutionException.class })
//    public ResponseEntity<Map> serverException() {
//        return new ResponseEntity<>(Collections.singletonMap("reason", "Server Error"),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}