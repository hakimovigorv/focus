package com.mpi.focus.repos;

import com.mpi.focus.models.Legend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LegendRepository extends JpaRepository<Legend, Long> {

}