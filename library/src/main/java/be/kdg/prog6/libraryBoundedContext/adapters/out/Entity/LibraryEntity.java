package be.kdg.prog6.libraryBoundedContext.adapters.out.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(catalog = "library")
@Data @NoArgsConstructor @AllArgsConstructor
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "libraryEntity"
    )
    private List<GameEntity> games = new ArrayList<>();


}
