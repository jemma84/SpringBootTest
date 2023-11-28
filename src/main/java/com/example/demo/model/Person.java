package com.example.demo.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="person_id")
        private Long id;

        @Column(nullable=false)
        private String login;

        @Column(nullable=false, unique=true)
        private String email;

        @Column(nullable=false)
        private String password;

        //@NotEmpty(message = "At least one sector is required")
        //@Size(min = 1, max = 5)
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(name = "person_sector",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id"))
        List<Sector> sectors;
}
