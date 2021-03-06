package com.apap.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public String getNip() {
        return nip;
    }

    public String getNama() {
        return nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public InstansiModel getInstansi() {
        return instansi;
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "nip", nullable = false, unique = true)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tahun_masuk", nullable = false)
    private String tahunMasuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instansi",referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private InstansiModel instansi;

    @OneToMany(mappedBy = "pegawai",fetch = FetchType.LAZY)
    private List<JabatanPegawaiModel> jabatanPegawaiModelList;

    public List<JabatanPegawaiModel> getJabatanPegawaiModelList() {
        return jabatanPegawaiModelList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public void setInstansi(InstansiModel instansi) {
        this.instansi = instansi;
    }

    public void setJabatanPegawaiModelList(List<JabatanPegawaiModel> jabatanPegawaiModelList) {
        this.jabatanPegawaiModelList = jabatanPegawaiModelList;
    }
}
