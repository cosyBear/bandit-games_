package be.kdg.prog6.statistics.adapters.out;

import be.kdg.prog6.statistics.adapters.out.web.ExportFeignClient;
import be.kdg.prog6.statistics.domain.ExportData;
import be.kdg.prog6.statistics.ports.out.ExportServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExportServiceAdapter implements ExportServicePort {
  private final ExportFeignClient feignClient;

  @Override
  public ExportData exportPlayerStatistics(UUID userId) {
    log.info("user id {}", userId);
    byte[] fileContent = feignClient.exportPlayerStatistics(userId);

    return new ExportData(
            fileContent,
            "player_statistics_" + userId + ".csv",
            "CSV",
            LocalDateTime.now()
    );
  }
}
