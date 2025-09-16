package de.iu.ghostnet.repository;

import de.iu.ghostnet.entity.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
}