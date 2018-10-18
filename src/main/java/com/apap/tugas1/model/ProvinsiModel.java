package com.apap.tugas1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "provinsi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProvinsiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "presentase_tunjangan", nullable = false)
    private double presentaseTunjangan;

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getPresentaseTunjangan() {
        return presentaseTunjangan;
    }
}
