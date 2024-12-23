package com.example.serviceclient;

import com.example.serviceclient.model.Client;
import com.example.serviceclient.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeH2Database(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(null, "John Doe", 30));
            clientRepository.save(new Client(null, "Jane Smith", 28));
        };
    }
}