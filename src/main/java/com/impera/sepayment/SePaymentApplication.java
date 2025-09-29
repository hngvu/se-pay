package com.impera.sepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SePaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SePaymentApplication.class, args);
    }

}
