package de.iu.ghostnet.repository;

import de.iu.ghostnet.entity.GhostNet;
import de.iu.ghostnet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
    List<GhostNet> findByStatusAndSalvagerIsNull(Status status);
}