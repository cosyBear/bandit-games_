package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.web.ExportFeignClient;
import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExportServiceAdapter implements ExportServicePort {
  private final ExportFeignClient feignClient;

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