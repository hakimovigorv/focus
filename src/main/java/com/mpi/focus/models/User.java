package com.mpi.focus.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    public User() {

    }

    public User(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", updatable = false)
    private Long userID;

    private String username;

    private String login;

    private String password;

    private boolean active;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private Set<Task> tasks = new HashSet<Task>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
