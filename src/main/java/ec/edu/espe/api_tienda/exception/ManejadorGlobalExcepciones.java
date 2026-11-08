package ec.edu.espe.api_tienda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<RespuestaError> manejarProductoNoEncontrado(
            ProductoNoEncontradoException excepcion) {
        return crearRespuesta(excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<RespuestaError> manejarProductoInvalido(
            ProductoInvalidoException excepcion) {
        return crearRespuesta(excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RespuestaError> crearRespuesta(String mensaje, HttpStatus estado) {
        RespuestaError respuesta = new RespuestaError(mensaje, estado.value());
        return ResponseEntity.status(estado).body(respuesta);
    }
}

