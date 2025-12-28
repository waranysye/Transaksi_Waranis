package ui.ft.ccit.faculty.transaksi.pemasok.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.service.PemasokService;

import java.util.List;

@RestController
@RequestMapping("/api/pemasok")
@Tag(name = "Pemasok")
public class PemasokController {

    private final PemasokService pemasokService;

    public PemasokController(PemasokService pemasokService) {
        this.pemasokService = pemasokService;
    }

    @GetMapping
    public List<Pemasok> getAll() {
        return pemasokService.findAll();
    }

    @GetMapping("/{id}")
    public Pemasok getById(@PathVariable String id) {
        return pemasokService.findById(id);
    }

    @PostMapping
    public Pemasok create(@RequestBody Pemasok pemasok) {
        return pemasokService.create(pemasok);
    }

    @PutMapping("/{id}")
    public Pemasok update(@PathVariable String id,
                          @RequestBody Pemasok pemasok) {
        return pemasokService.update(id, pemasok);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        pemasokService.delete(id);
    }
}
