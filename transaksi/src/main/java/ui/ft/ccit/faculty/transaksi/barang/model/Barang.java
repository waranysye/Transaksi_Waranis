package ui.ft.ccit.faculty.transaksi.barang.model;

import jakarta.persistence.*;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;

@Entity
@Table(name = "barang")
public class Barang {

    @Id
    @Column(name = "id_barang", columnDefinition = "CHAR(4)")
    private String idBarang;


    private String nama;

    private Integer stok;

    private Double harga;

    @Column(name = "persen_laba")
    private Double persenLaba;

    private Double diskon = 0.0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_jenis_brg")
    private JenisBarang jenisBarang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pemasok")
    private Pemasok pemasok;


    protected Barang() {
        // wajib untuk JPA
    }

    // ✅ CONSTRUCTOR YANG BENAR
    public Barang(
            String idBarang,
            String nama,
            Integer stok,
            Double harga,
            Double persenLaba,
            JenisBarang jenisBarang,
            Pemasok pemasok
    ) {
        this.idBarang = idBarang;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.persenLaba = persenLaba;
        this.jenisBarang = jenisBarang;
        this.pemasok = pemasok;
    }

    // ===== GETTER & SETTER =====

    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public Integer getStok() { return stok; }
    public void setStok(Integer stok) { this.stok = stok; }

    public Double getHarga() { return harga; }
    public void setHarga(Double harga) { this.harga = harga; }

    public Double getPersenLaba() { return persenLaba; }
    public void setPersenLaba(Double persenLaba) { this.persenLaba = persenLaba; }

    public Double getDiskon() { return diskon; }
    public void setDiskon(Double diskon) { this.diskon = diskon; }

    public JenisBarang getJenisBarang() { return jenisBarang; }
    public void setJenisBarang(JenisBarang jenisBarang) { this.jenisBarang = jenisBarang; }

    public Pemasok getPemasok() { return pemasok; }
    public void setPemasok(Pemasok pemasok) { this.pemasok = pemasok; }
}
