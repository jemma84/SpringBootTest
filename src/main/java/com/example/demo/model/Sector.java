package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sector {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "sector_id")
        private Long sectorId;

        private Long parentId;

        private String name;

        @Transient
        private boolean isParentGroup;

        @ManyToMany
        @JoinTable(name = "person_sector",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id"))
        List<Sector> persons;
}
