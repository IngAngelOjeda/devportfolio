package com.devportfolio.repository;

import com.devportfolio.model.SobreMi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SobreMiRepository extends JpaRepository<SobreMi, Long> {
}