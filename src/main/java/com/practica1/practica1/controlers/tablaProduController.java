package com.practica1.practica1.controlers;


import org.springframework.ui.Model;
import com.practica1.practica1.entidades.Productos;
import com.practica1.practica1.repository.TablaProducRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class tablaProduController {
    @Autowired
    private TablaProducRepository tablaProducRepository;

    // READ
    @GetMapping("/tabla")
    public String tablas(Model model) {
        List<Productos> tablas = tablaProducRepository.findAll();
        model.addAttribute("tablas", tablas);
        return "tabla"; // Este es el nombre del archivo de vista Thymeleaf
    }
    // CREAR
    @GetMapping("/tabla/form")
    public String formulario(Model model) {
        model.addAttribute("producto", new Productos());
        return "formulario";
    }
    ////
    @PostMapping("/tabla/form")
    public String crear(@ModelAttribute Productos productos) {
        tablaProducRepository.save(productos);
        return "redirect:/tabla";
    }

    // EDITAR
    @GetMapping("/tabla/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Productos producto = tablaProducRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tabla id:" + id));
        model.addAttribute("producto", producto); // Asegúrate de que el nombre sea "producto"
        return "formulario"; // Debe coincidir con el nombre del archivo HTML de tu formulario
    }

    @PostMapping("/tabla/editar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute Productos tabla) {
        tabla.setId_p(id);
     tablaProducRepository.save(tabla);
        return "redirect:/tabla";
    }

    // ELIMINAR
    @GetMapping("/tabla/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        tablaProducRepository.deleteById(id);
        return "redirect:/tabla";
    }

}
