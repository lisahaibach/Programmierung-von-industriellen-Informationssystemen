package de.iu.ghostnet.entity;

import jakarta.persistence.*;

@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;      // GPS
    private String size;          // geschätzte Größe

    @Enumerated(EnumType.STRING)
    private Status status = Status.GEMELDET;

    // Daten der meldenden Person (optional = anonym)
    private String reporterName;
    private String reporterPhone;

    // max. eine bergende Person (optional)
    @ManyToOne
    private Person salvager;

    // ===== Getter/Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    public String getReporterPhone() { return reporterPhone; }
    public void setReporterPhone(String reporterPhone) { this.reporterPhone = reporterPhone; }

    public Person getSalvager() { return salvager; }
    public void setSalvager(Person salvager) { this.salvager = salvager; }
}