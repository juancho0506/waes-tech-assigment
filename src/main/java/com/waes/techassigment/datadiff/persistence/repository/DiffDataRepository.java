package com.waes.techassigment.datadiff.persistence.repository;

import com.waes.techassigment.datadiff.persistence.entity.DiffData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("diffDataRepository")
public interface DiffDataRepository extends JpaRepository<DiffData, Integer> {}
