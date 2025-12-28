package ui.ft.ccit.faculty.transaksi.pemasok.service;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository;

import java.util.List;

@Service
public class PemasokService {

    private final PemasokRepository pemasokRepository;

    public PemasokService(PemasokRepository pemasokRepository) {
        this.pemasokRepository = pemasokRepository;
    }

    public List<Pemasok> findAll() {
        return pemasokRepository.findAll();
    }

    public Pemasok findById(String id) {
        return pemasokRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pemasok dengan id " + id + " tidak ditemukan"));
    }

    public Pemasok create(Pemasok pemasok) {
        if (pemasokRepository.existsById(pemasok.getIdPemasok())) {
            throw new RuntimeException("Pemasok dengan id " + pemasok.getIdPemasok() + " sudah ada");
        }
        return pemasokRepository.save(pemasok);
    }

    public Pemasok update(String id, Pemasok dataBaru) {
        Pemasok pemasok = findById(id);

        pemasok.setNamaPemasok(dataBaru.getNamaPemasok());
        pemasok.setAlamat(dataBaru.getAlamat());
        pemasok.setTelepon(dataBaru.getTelepon());
        pemasok.setEmail(dataBaru.getEmail());

        return pemasokRepository.save(pemasok);
    }

    public void delete(String id) {
        Pemasok pemasok = findById(id);
        pemasokRepository.delete(pemasok);
    }
}
