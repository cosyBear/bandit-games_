package be.kdg.prog6.friends.adapters.out.repository;

import be.kdg.prog6.friends.adapters.out.entity.AddressJpaEntity;
import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, UUID> {
}
