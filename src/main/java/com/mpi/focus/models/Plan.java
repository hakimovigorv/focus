package com.mpi.focus.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Plan {

    public Plan() {
    }
    public Plan(String planName, LocalDateTime actionData, String location) {
        this.planName = planName;
        this.actionData = actionData;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "plan_id",  updatable = false)
    private Long planID;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "action_data")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime actionData;

    private String location;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan", fetch = FetchType.EAGER)
    private List<Template> templates;

}
