package com.example.demo.repository;

import com.example.demo.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {}
