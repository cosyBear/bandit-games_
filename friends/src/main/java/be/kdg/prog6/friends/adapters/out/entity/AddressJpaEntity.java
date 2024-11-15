package be.kdg.prog6.friends.adapters.out.entity;

import be.kdg.prog6.friends.domain.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Table(catalog = "friends", name = "address")
@Data @AllArgsConstructor @NoArgsConstructor
public class AddressJpaEntity {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String country;
    private String houseNumber;
    @OneToOne
    @JoinColumn(name = "address_id")
    private PlayerJpaEntity playerJpaEntity;

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
