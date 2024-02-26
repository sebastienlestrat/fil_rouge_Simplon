package com.recyclascore.backend.repository;

import com.recyclascore.backend.entity.MaterialObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialObjectRepository  extends JpaRepository<MaterialObject, Long> {
}
