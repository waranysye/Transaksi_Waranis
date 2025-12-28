package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.service.JenisBarangService;

import java.util.List;

@RestController
@RequestMapping("/api/jenis-barang")
@Tag(name = "Jenis Barang")
public class JenisBarangController {

    private final JenisBarangService jenisBarangService;

    public JenisBarangController(JenisBarangService jenisBarangService) {
        this.jenisBarangService = jenisBarangService;
    }

    @GetMapping
    public List<JenisBarang> getAll(){
        return jenisBarangService.getAll();
    }

    @GetMapping("/{id}")
    public JenisBarang getById(@PathVariable Byte id){
        return jenisBarangService.getById(id);
    }

    @PostMapping
    public JenisBarang create(@RequestBody JenisBarang jenisBarang){
        return jenisBarangService.create(jenisBarang);
    }

    @PutMapping("/{id}")
    public JenisBarang update(@PathVariable Byte id,
                              @RequestBody JenisBarang jenisBarang){
        return jenisBarangService.update(id, jenisBarang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Byte id){
        jenisBarangService.delete(id);
    }
}
