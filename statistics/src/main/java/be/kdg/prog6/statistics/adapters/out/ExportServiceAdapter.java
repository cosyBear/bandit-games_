package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Service
public class ExportServiceAdapter implements ExportServicePort {
  private final ExportFeignClient feignClient;

  public ExportServiceAdapter(ExportFeignClient feignClient) {
    this.feignClient = feignClient;
  }

  @Override
  public ExportData exportPlayerStatistics() {
    byte[] fileContent = feignClient.exportPlayerStatistics();

    return new ExportData(
            fileContent,
            "player_statistics.csv",
            "CSV",
            LocalDateTime.now()
    );
  }
}

@FeignClient(name = "exportService", url = "http:///chatbox.westeurope.cloudapp.azure.com:8000")
interface ExportFeignClient {
  @PostMapping("api/export-player-statistics")
  byte[] exportPlayerStatistics();
}
