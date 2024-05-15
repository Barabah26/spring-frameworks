package com.example.boothibernate.domain.hr;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {
    @Id
    @Column(name = "country_id", nullable = false, columnDefinition = "char(2)", length = 2)
    private String id;

    @Column(name = "country_name", length = 40)
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region regions;
}