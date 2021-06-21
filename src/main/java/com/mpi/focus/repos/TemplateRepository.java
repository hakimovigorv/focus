package com.mpi.focus.repos;


import com.mpi.focus.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
