package ec.edu.espe.api_tienda.service;

import ec.edu.espe.api_tienda.exception.ProductoInvalidoException;
import ec.edu.espe.api_tienda.exception.ProductoNoEncontradoException;
import ec.edu.espe.api_tienda.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    //Lista de Productos
    private final List<Producto> productos = new ArrayList<>(
            List.of(
                    new Producto(1L,"Fideos",2.00,30),
                    new Producto(2L,"Atún",2.50,15),
                    new Producto(3L,"Tomates",0.25,8),
                    new Producto(4L,"Chocolate",0.50,21),
                    new Producto(5L,"Carne", 4.50,2)
            )
    );

    //Metodo para listar todos productos
    public List< Producto> listarProd(){
        return List.copyOf(productos);
    }

    //Metodo para buscar por ID
    public Producto buscarProdId(Long id) {
        Producto productoEncontrado = null;

        for (Producto producto: productos){
            if (producto.getId().equals(id)) {
                productoEncontrado = producto;
                break;
            }
        }
        if (productoEncontrado == null){
            throw new ProductoNoEncontradoException(id);
        }
        return productoEncontrado;
    }

    //Metodo para agregar producto
    public Producto agregarProd(Producto producto){
        //Reglas de negocio
        //ID
        if(producto.getId() == null || producto.getId() <= 0 ){
            throw new ProductoInvalidoException("ID inválido");
        }
        //Precio
        if(producto.getPrecio() <= 0 ){

            throw new ProductoInvalidoException("El precio debe ser mayor que cero");
        }
        //Stock
        if (producto.getStock()<0){
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }

        productos.add(producto);
        return producto;
    }

    //Metodo para eliminar producto
    public void eliminarProd (Long id){
        if (id == null || id <= 0) {
            throw new ProductoInvalidoException("El ID proporcionado para eliminar no es válido");
        }
        Producto productoEncontrado = buscarProdId(id);
        productos.remove(productoEncontrado);
    }
}
