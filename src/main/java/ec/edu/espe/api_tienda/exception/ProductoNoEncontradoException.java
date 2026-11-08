package ec.edu.espe.api_tienda.exception;

public class ProductoNoEncontradoException extends RuntimeException{
    public ProductoNoEncontradoException (Long id){
        super("No existe el producto con id: "+ id);
    }
}


