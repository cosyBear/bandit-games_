package be.kdg.prog6.friends.adapters.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity @Table(name = "address", catalog = "friends")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String street;
    private String city;
    private String country;
    private String houseNumber;
    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerJpaEntity playerJpaEntity;
}
