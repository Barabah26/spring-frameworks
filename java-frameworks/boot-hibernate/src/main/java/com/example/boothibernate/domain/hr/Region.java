package com.example.boothibernate.domain.hr;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class Region {
    @Id
    @Column(name = "region_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "region_name", length = 25)
    private String regionName;

    @OneToMany(mappedBy = "regions")
    private Set<Country> countries = new LinkedHashSet<>();
}