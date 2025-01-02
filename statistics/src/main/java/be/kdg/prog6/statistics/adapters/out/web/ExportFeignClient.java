package be.kdg.prog6.statistics.adapters.out.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "exportService", url = "http:///chatbox.westeurope.cloudapp.azure.com:8000")
public interface ExportFeignClient {
    @PostMapping("api/export-player-statistics")
    byte[] exportPlayerStatistics();
}
