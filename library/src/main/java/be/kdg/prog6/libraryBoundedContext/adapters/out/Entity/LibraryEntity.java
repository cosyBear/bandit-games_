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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID libraryId;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "playerId")
    private PlayerEntity player;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "library")
    private List<GameEntity> games = new ArrayList<>();
}
