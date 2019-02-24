package com.leepresswood.adaware;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
class InfoService {

    @Async
    CompletableFuture<Map<String, String>> getIpInfo(String ip) {
        return CompletableFuture.supplyAsync(() -> runWhois(ip))
                                .thenApplyAsync(InfoService::convertToMap);
    }

    private ArrayList<String> runWhois(String ip) {
        ProcessBuilder builder = new ProcessBuilder("whois", ip);
        builder.redirectErrorStream(true);
        Process process = null;
        ArrayList<String> results = new ArrayList<>();

        try {
            process = builder.start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                results.add(line);
            }

            return results;
        } catch (IOException e) {
            return results;
        }
    }

    private static Map<String, String> convertToMap(ArrayList<String> arr) {

        HashMap<String, String> results = new HashMap<>();

        System.out.println(arr);
        return results;
    }
}
