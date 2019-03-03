package com.leepresswood.adaware;

import org.springframework.stereotype.Service;

@Service
public class InfoService {

//    @Cacheable("ip")
//    public Whois getIpInfo(String ip) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        BufferedReader r = runWhois(ip);
//        return r == null ? null : buildResult(r, ip);
//    }
//
//    private BufferedReader runWhois(String ip) {
//        ProcessBuilder builder = new ProcessBuilder("whois", ip);
//        builder.redirectErrorStream(true);
//        Process process;
//
//        try {
//            process = builder.start();
//            return new BufferedReader(new InputStreamReader(process.getInputStream()));
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    private static Whois buildResult(BufferedReader input, String ip) {
//        try {
//            Whois results = new Whois(ip);
//
//            String line;
//            int count = 0;
//            boolean start = false;
//            while (count < 1000 && (results.country.length() == 0 || results.state.length() == 0) && (line = input.readLine()) != null) {
//                String[] tokenized = line.split(": ");
//                tokenized[0] = tokenized[0].trim();
//
//                if (line.contains("Domain Name") || line.contains("Registrar URL")) {
//                    start = true;
//                }
//
//                if (start && tokenized.length == 2) {
//                    if (results.state.length() == 0 && tokenized[0].equals("Tech State/Province")) {
//                        results.state = tokenized[1];
//                    } else if (results.country.length() == 0 && tokenized[0].equals("Tech Country")) {
//                        results.country = tokenized[1];
//                    }
//                }
//
//                if(start)
//                    count++;
//            }
//
//            return results;
//        } catch (IOException e) {
//            return null;
//        }
//    }
}
