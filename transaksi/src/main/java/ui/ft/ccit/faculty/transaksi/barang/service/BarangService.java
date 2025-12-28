package ui.ft.ccit.faculty.transaksi.barang.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BarangService {

    private final BarangRepository barangRepository;

    public BarangService(BarangRepository barangRepository) {
        this.barangRepository = barangRepository;
    }

    // ===================== READ =====================

    public List<Barang> getAll() {
        return barangRepository.findAll();
    }

    public List<Barang> getAllWithPagination(int page, int size) {
        return barangRepository
                .findAll(PageRequest.of(page, size))
                .getContent();
    }

    public Barang getById(String id) {
        return barangRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Barang", id));
    }

    public List<Barang> searchByNama(String keyword) {
        return barangRepository.findByNamaContainingIgnoreCase(keyword);
    }

    // ===================== CREATE =====================

    public Barang save(Barang barang) {
        if (barang.getIdBarang() == null || barang.getIdBarang().isBlank()) {
            throw new IllegalArgumentException("idBarang wajib diisi");
        }

        if (barangRepository.existsById(barang.getIdBarang())) {
            throw new DataAlreadyExistsException("Barang", barang.getIdBarang());
        }

        return barangRepository.save(barang);
    }

    public List<Barang> saveBulk(List<Barang> barangList) {
        for (Barang barang : barangList) {
            if (barang.getIdBarang() == null || barang.getIdBarang().isBlank()) {
                throw new IllegalArgumentException("idBarang wajib diisi untuk setiap barang");
            }

            if (barangRepository.existsById(barang.getIdBarang())) {
                throw new DataAlreadyExistsException("Barang", barang.getIdBarang());
            }
        }
        return barangRepository.saveAll(barangList);
    }

    // ===================== UPDATE =====================

    public Barang update(String id, Barang updated) {

    Barang existing = getById(id);

    existing.setNama(updated.getNama());
    existing.setStok(updated.getStok());
    existing.setHarga(updated.getHarga());
    existing.setPersenLaba(updated.getPersenLaba());
    existing.setDiskon(updated.getDiskon());

    existing.setJenisBarang(updated.getJenisBarang());
    existing.setPemasok(updated.getPemasok());


    return barangRepository.save(existing);
}




    // ===================== DELETE =====================

    public void deleteBulk(List<String> ids) {

        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("List ID tidak boleh kosong");
        }

        if (ids.size() > 100) {
            throw new IllegalArgumentException("Maksimal 100 data per bulk delete");
        }

        long existingCount = barangRepository.countByIdBarangIn(ids);
        if (existingCount != ids.size()) {
            throw new IllegalStateException("Sebagian ID tidak ditemukan, operasi dibatalkan");
        }

        barangRepository.deleteAllById(ids);
    }

    public void delete(String id) {
        if (!barangRepository.existsById(id)) {
            throw new DataNotFoundException("Barang", id);
        }
        barangRepository.deleteById(id);
    }
}
