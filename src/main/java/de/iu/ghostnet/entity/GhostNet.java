package de.iu.ghostnet.entity;

import jakarta.persistence.*;

@Entity
public class GhostNet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;          // GPS
    private String size;              // geschätzte Größe

    @Enumerated(EnumType.STRING)
    private Status status = Status.GEMELDET;

    // Daten der meldenden Person (dürfen leer sein = anonym)
    private String reporterName;
    private String reporterPhone;

    // max. eine bergende Person, optional
    @ManyToOne
    private Person salvager;

    // getters/setters ...
}