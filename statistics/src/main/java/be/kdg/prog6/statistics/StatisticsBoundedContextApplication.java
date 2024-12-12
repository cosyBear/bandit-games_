package be.kdg.prog6.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "be.kdg.prog6.statistics.adapters.out")
public class StatisticsBoundedContextApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsBoundedContextApplication.class, args);
    }

}
