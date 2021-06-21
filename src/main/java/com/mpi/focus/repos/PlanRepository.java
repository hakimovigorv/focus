package com.mpi.focus.repos;

import com.mpi.focus.models.Plan;
import com.mpi.focus.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
