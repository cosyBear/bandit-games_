package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity @Table(catalog = "friends", name = "address")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressJpaEntity {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String country;
    private String houseNumber;
    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerJpaEntity playerJpaEntity;

    public AddressJpaEntity(UUID id, String street, String city, String country, String houseNumber) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.houseNumber = houseNumber;
    }



    public Address convertToDomain(){
        return new Address(
                new Address.AddressId(this.id),
                this.street,
                this.city,
                this.country,
                this.houseNumber
        );
    }
}
