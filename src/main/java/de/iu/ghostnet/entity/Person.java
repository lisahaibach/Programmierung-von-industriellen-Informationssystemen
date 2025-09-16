package de.iu.ghostnet.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;   // für Rückfragen (für Bergende Pflicht)

    // optional: Komfort – zeigt, ob sich jemand als bergend registriert hat
    private boolean salvager;

    @OneToMany(mappedBy = "salvager")
    private List<GhostNet> assignedNets;

}