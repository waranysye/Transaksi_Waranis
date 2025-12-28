package ui.ft.ccit.faculty.transaksi.barang;

import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
class BarangRepositoryTest {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private JenisBarangRepository jenisBarangRepository;

    @Autowired
    private PemasokRepository pemasokRepository;

    @Test
    void testSaveBarang() {

        // SIMPAN DULU ENTITY RELASI
        JenisBarang jenis = new JenisBarang(null, "ATK");
        jenis = jenisBarangRepository.save(jenis);

        Pemasok pemasok = new Pemasok(
                "P001",
                "Toko A",
                "Jakarta",
                "08123456789",
                "tokoa@email.com"
        );
        pemasok = pemasokRepository.save(pemasok);

        Barang barang = new Barang(
                "B001",
                "Pensil",
                10,
                2000.0,
                20.0,     // persenLaba
                jenis,
                pemasok
        );

        Barang saved = barangRepository.save(barang);

        assertThat(saved).isNotNull();
        assertThat(saved.getIdBarang()).isEqualTo("B001");
        assertThat(saved.getJenisBarang().getNamaJenis()).isEqualTo("ATK");
    }

    @Test
    void testFindById() {

        JenisBarang jenis = jenisBarangRepository.save(
                new JenisBarang(null, "ATK")
        );

        Pemasok pemasok = pemasokRepository.save(
                new Pemasok("P002", "Toko B", null, null, null)
        );

        Barang barang = new Barang(
                "B002",
                "Pulpen",
                5,
                3000.0,
                15.0,
                jenis,
                pemasok
        );

        barangRepository.save(barang);

        Barang found = barangRepository.findById("B002").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getNama()).isEqualTo("Pulpen");
    }
}
