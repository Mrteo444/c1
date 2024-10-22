package com.practica1.practica1.controlers;


import com.practica1.practica1.repository.TablaProducRepository;
import org.springframework.ui.Model;
import com.practica1.practica1.entidades.Stock;
import com.practica1.practica1.entidades.Productos; // Asegúrate de importar la entidad Productos
import com.practica1.practica1.repository.StockRepository;
 // Asegúrate de tener un servicio para productos
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
    private TablaProducRepository productosRepository; // Repositorio de productos

    // READ: Mostrar todos los registros de Stock
    @GetMapping("/tabla")
    public String listarStock(Model model) {
        List<Stock> stockList = stockRepository.findAll();
        model.addAttribute("stockList", stockList);
        return "stock/tabla";  // Carga tabla.html desde templates/stock
    }

    // CREAR: Mostrar formulario para agregar nuevo stock
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("stock", new Stock());
        List<Productos> productosList = productosRepository.findAll(); // Carga todos los productos
        model.addAttribute("productosList", productosList); // Agrega la lista de productos al modelo
        return "stock/formulario";  // Carga formulario.html desde templates/stock
    }

    // CREAR: Guardar nuevo registro de Stock
    @PostMapping("/formulario")
    public String crearStock(@ModelAttribute Stock stock, @RequestParam int productoId) {
        // Busca el producto en el repositorio usando el ID recibido
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("ID de producto no válido: " + productoId));
        stock.setProductos(producto); // Asocia el producto con el stock
        stockRepository.save(stock);
        return "redirect:/stock/tabla";  // Redirige a la tabla de stock
    }

    // EDITAR: Mostrar formulario para editar un stock existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de stock no válido: " + id));
        model.addAttribute("stock", stock);
        List<Productos> productosList = productosRepository.findAll(); // Carga todos los productos
        model.addAttribute("productosList", productosList); // Agrega la lista de productos al modelo
        return "stock/formulario";  // Reutiliza formulario.html para edición
    }

    // EDITAR: Guardar los cambios del stock editado
    @PostMapping("/editar/{id}")
    public String actualizarStock(@PathVariable int id, @ModelAttribute Stock stock, @RequestParam int productoId) {
        // Busca el producto en el repositorio usando el ID recibido
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("ID de producto no válido: " + productoId));
        stock.setId_s(id);  // Asegúrate de que el ID sea el mismo
        stock.setProductos(producto); // Asocia el producto con el stock
        stockRepository.save(stock);
        return "redirect:/stock/tabla";  // Redirige a la tabla de stock
    }

    // ELIMINAR: Eliminar un registro de stock
    @GetMapping("/eliminar/{id}")
    public String eliminarStock(@PathVariable int id) {
        stockRepository.deleteById(id);
        return "redirect:/stock/tabla";  // Redirige a la tabla de stock
    }
}
