package com.leepresswood.adaware;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class InfoService {

    @Cacheable("ip")
    public Whois getIpInfo(String ip) {
        BufferedReader r = runWhois(ip);
        return r == null ? null : buildResult(r, ip);
    }

    private BufferedReader runWhois(String ip) {
        ProcessBuilder builder = new ProcessBuilder("whois", ip);
        builder.redirectErrorStream(true);
        Process process;

        try {
            process = builder.start();
            return new BufferedReader(new InputStreamReader(process.getInputStream()));
        } catch (IOException e) {
            return null;
        }
    }

    private static Whois buildResult(BufferedReader input, String ip) {
        try {
            Whois results = new Whois(ip);

            String line;
            int count = 0;
            while (count < 500 && (results.country.equals("") || results.state.equals(""))
                    && (line = input.readLine()) != null) {
                String[] tokenized = line.split(": ");

                if (results.state.equals("") && tokenized[0].equals("Registrant State/Province")) {
                    results.state = tokenized[1];
                } else if (results.country.equals("") && tokenized[0].equals("Registrant Country")) {
                    results.country = tokenized[1];
                }

                count++;
            }

            return results;
        } catch (IOException e) {
            return null;
        }
    }
}
