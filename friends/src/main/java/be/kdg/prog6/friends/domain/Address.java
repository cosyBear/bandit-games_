package be.kdg.prog6.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class Address {

    private AddressId id;
    private String street;
    private String city;
    private String country;
    private String houseNumber;

    public record AddressId(UUID id){}

}
