package com.apap.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "jabatan")
public class JabatanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setGajiPokok(Double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public Double getGajiPokok() {
        return gajiPokok;
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private Double gajiPokok;

    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
    private List<JabatanPegawaiModel> jabatanPegawaiModelList;

    public List<JabatanPegawaiModel> getJabatanPegawaiModelList() {
        return jabatanPegawaiModelList;
    }

    public void setJabatanPegawaiModelList(List<JabatanPegawaiModel> jabatanPegawaiModelList) {
        this.jabatanPegawaiModelList = jabatanPegawaiModelList;
    }
}
