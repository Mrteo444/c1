package com.practica1.practica1.controlers;

import com.practica1.practica1.repository.TablaProducRepository;
import org.springframework.ui.Model;
import com.practica1.practica1.entidades.Stock;
import com.practica1.practica1.entidades.Productos; 
import com.practica1.practica1.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TablaProducRepository productosRepository; 

    @GetMapping("/tabla")
    public String listarStock(Model model) {
        List<Stock> stockList = stockRepository.findAll();
        model.addAttribute("stockList", stockList);
        return "stock/tabla";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("stock", new Stock());
        List<Productos> productosList = productosRepository.findAll();
        model.addAttribute("productosList", productosList);
        return "stock/formulario";
    }

    @PostMapping("/formulario")
    public String crearStock(@ModelAttribute Stock stock, @RequestParam int productoId) {
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("ID de producto no válido: " + productoId));
        stock.setProductos(producto);
        stockRepository.save(stock);
        return "redirect:/stock/tabla";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de stock no válido: " + id));
        model.addAttribute("stock", stock);
        List<Productos> productosList = productosRepository.findAll();
        model.addAttribute("productosList", productosList);
        return "stock/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarStock(@PathVariable int id, @ModelAttribute Stock stock, @RequestParam int productoId) {
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("ID de producto no válido: " + productoId));
        stock.setId_s(id);
        stock.setProductos(producto);
        stockRepository.save(stock);
        return "redirect:/stock/tabla";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarStock(@PathVariable int id) {
        stockRepository.deleteById(id);
        return "redirect:/stock/tabla";
    }
}
