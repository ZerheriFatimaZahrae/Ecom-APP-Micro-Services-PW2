package org.sid.discoveryservice;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurikaServer
public class DiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }

}
