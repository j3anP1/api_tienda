package ec.edu.espe.api_tienda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTiendaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void productoInexistenteEnConsultaDevuelve404ConMensajeYCodigo() throws Exception {
		mockMvc.perform(get("/api/productos/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.mensaje").value("No existe el producto con id: 999"))
				.andExpect(jsonPath("$.codigoEstado").value(404));
	}

	@Test
	void productoInexistenteEnEliminacionDevuelve404ConMensajeYCodigo() throws Exception {
		mockMvc.perform(delete("/api/productos/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.mensaje").value("No existe el producto con id: 999"))
				.andExpect(jsonPath("$.codigoEstado").value(404));
	}

	@Test
	void precioNoValidoDevuelve400ConMensajeYCodigo() throws Exception {
		mockMvc.perform(post("/api/productos")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"id": 100, "nombre": "Producto de prueba", "precio": 0, "stock": 1}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.mensaje").value("El precio debe ser mayor que cero"))
				.andExpect(jsonPath("$.codigoEstado").value(400));
	}

	@Test
	void stockNegativoDevuelve400ConMensajeYCodigo() throws Exception {
		mockMvc.perform(post("/api/productos")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{"id": 101, "nombre": "Producto de prueba", "precio": 1, "stock": -1}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.mensaje").value("El stock no puede ser negativo"))
				.andExpect(jsonPath("$.codigoEstado").value(400));
	}
}
