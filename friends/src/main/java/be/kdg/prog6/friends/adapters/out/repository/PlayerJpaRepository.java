package be.kdg.prog6.friends.adapters.out.repository;

import be.kdg.prog6.friends.adapters.out.entity.PlayerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerJpaRepository extends JpaRepository<PlayerJpaEntity, UUID> {
    @Query("""
                SELECT p FROM PlayerJpaEntity p
                 WHERE LOWER(p.nickname) LIKE LOWER(CONCAT('%', :nickname, '%'))
            """)
    List<PlayerJpaEntity> searchAllByNicknameIgnoreCase(String nickname);
}
