package com.esale.esale.controller;

import com.esale.esale.model.EmpresaTransporte;
import com.esale.esale.service.EmpresaTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportes")
@CrossOrigin(origins = "*")
public class EmpresaTransporteController {

    @Autowired
    private EmpresaTransporteService transporteService;

    @GetMapping
    public List<EmpresaTransporte> listar() {
        return transporteService.obtenerTodas();
    }

    @PostMapping
    public EmpresaTransporte guardar(@RequestBody EmpresaTransporte empresa) {
        return transporteService.guardar(empresa);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        transporteService.eliminar(id);
    }
}
