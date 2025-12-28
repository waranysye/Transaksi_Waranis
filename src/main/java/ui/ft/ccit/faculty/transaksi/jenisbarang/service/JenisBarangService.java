package ui.ft.ccit.faculty.transaksi.jenisbarang.service;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository;

import java.util.List;

@Service
public class JenisBarangService {

    private final JenisBarangRepository jenisBarangRepository;

    public JenisBarangService(JenisBarangRepository jenisBarangRepository) {
        this.jenisBarangRepository = jenisBarangRepository;
    }

    public List<JenisBarang> getAll(){
        return jenisBarangRepository.findAll();
    }

    public JenisBarang getById(Byte id){
        return jenisBarangRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Jenis barang dengan id " + id + " tidak ditemukan"));
    }

    public JenisBarang create(JenisBarang jenisBarang){
        return jenisBarangRepository.save(jenisBarang);
    }

    public JenisBarang update(Byte id, JenisBarang newData){

        JenisBarang j = getById(id);

        j.setNamaJenis(newData.getNamaJenis());

        return jenisBarangRepository.save(j);
    }

    public void delete(Byte id){
        JenisBarang j = getById(id);
        jenisBarangRepository.delete(j);
    }
}
