package com.recyclascore.backend.repository;

import com.recyclascore.backend.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialObjectRepository  extends JpaRepository<Material, Long> {
}
