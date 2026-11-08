package ec.edu.espe.api_tienda.controller;

import ec.edu.espe.api_tienda.model.Producto;
import ec.edu.espe.api_tienda.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    //Declaración del Service
    private ProductoService prodService;
    //Inyección por constructor
    public ProductoController(ProductoService prodService) {
        this.prodService = prodService;
    }

    //Endpoints
    //Obtener todos los productos
    @GetMapping
    public List<Producto> listarProd(){
        return prodService.listarProd();
    }

    //Obtener productos por ID
    @GetMapping("/{id}")
    public Producto obtenerProd(@PathVariable Long id){

        return prodService.buscarProdId(id);
    }

    //Registar productos
    @PostMapping
    public ResponseEntity<Producto> agregarProd (@RequestBody Producto producto){
        Producto prodAgregado = prodService.agregarProd(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodAgregado);
    }

    //Eliminar productos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProd (@PathVariable Long id){
        prodService.eliminarProd(id);
        return ResponseEntity.noContent().build();
    }
}
