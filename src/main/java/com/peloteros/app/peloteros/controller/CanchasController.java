package com.peloteros.app.peloteros.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peloteros.app.peloteros.entity.BuscarDisponibilidadModel;
import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.service.CanchasService;

/**
 * Controlador REST para gestionar las operaciones de las canchas.
 * Proporciona métodos para listar, buscar, agregar, editar y eliminar canchas,
 * así como consultar la disponibilidad de las canchas según la fecha y el número de jugadores.
 */
@RestController 
@RequestMapping("/canchas")
@CrossOrigin(origins= {"http://localhost/5500"})
public class CanchasController {

	@Autowired
	private CanchasService canchasService;
	
	/**
     * Constructor del controlador.
     */
	public CanchasController() {
	}
	
	/**
     * Listar todas las canchas.
     * 
     * @return una lista de canchas en formato JSON
     */
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Canchas> repository = canchasService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Canchas canchas : repository) {
	        Map<String, Object> canchasMap = new HashMap<>();
	        canchasMap.put("CanchaID", canchas.getCanchaID());
	        canchasMap.put("NumeroCancha", canchas.getNumeroCancha());
	        canchasMap.put("Imagen", canchas.getImagen());
	        canchasMap.put("Descripcion", canchas.getDescripcion());
	        canchasMap.put("PrecioPorHora", canchas.getPrecioPorHora());
	        canchasMap.put("Estado", canchas.getEstado());
	        
	        responseList.add(canchasMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	/**
     * Buscar una cancha por su ID.
     * 
     * @param CanchaID el ID de la cancha a buscar
     * @return la cancha encontrada o un mensaje de error si no se encuentra
     */
	@GetMapping("/buscar/{CanchaID}") //Http Method GET
	public ResponseEntity<?> buscar(@PathVariable Integer CanchaID) {
	    Canchas canchas = canchasService.findById(CanchaID);
	    if (canchas != null) {
	        Map<String, Object> canchasMap = new HashMap<>();
	        canchasMap.put("CanchaID", canchas.getCanchaID());
	        canchasMap.put("NumeroCancha", canchas.getNumeroCancha());
	        canchasMap.put("Imagen", canchas.getImagen());
	        canchasMap.put("Descripcion", canchas.getDescripcion());
	        canchasMap.put("PrecioPorHora", canchas.getPrecioPorHora());
	        canchasMap.put("Estado", canchas.getEstado());

	        return new ResponseEntity<>(canchasMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Cancha  " + CanchaID + " no encontrada", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	/**
     * Agregar una nueva cancha.
     * 
     * @param canchas los datos de la nueva cancha
     * @return un mensaje indicando que la cancha fue creada
     */
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Canchas canchas) {	
		canchasService.insert(canchas);		
		return new ResponseEntity<>("Cancha Creado",HttpStatus.CREATED); //Http status code	
	}
	
	/**
     * Editar los detalles de una cancha existente.
     * 
     * @param CanchaID el ID de la cancha a editar
     * @param newCancha los nuevos detalles de la cancha
     * @return un mensaje indicando el resultado de la edición
     */
	@PutMapping("/editar/{CanchaID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer CanchaID,
                                        @RequestBody Canchas newCancha) {		
		Canchas canchas = canchasService.findById(CanchaID);		
		if(canchas!=null) {
			canchas.setNumeroCancha(newCancha.getNumeroCancha());
			canchas.setImagen(newCancha.getImagen());
			canchas.setDescripcion(newCancha.getDescripcion());
			canchas.setPrecioPorHora(newCancha.getPrecioPorHora());
			canchas.setEstado(newCancha.getEstado());
			canchasService.update(canchas);
			return new ResponseEntity<>("Detalle editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Cancha ID " + CanchaID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	/**
     * Eliminar una cancha por su ID.
     * 
     * @param CanchaID el ID de la cancha a eliminar
     * @return un mensaje indicando el resultado de la eliminación
     */
	@DeleteMapping("/borrar/{CanchaID}")
	public ResponseEntity<?> borrar(@PathVariable Integer CanchaID) {
	    Canchas canchas = canchasService.findById(CanchaID);
	    
	    if (canchas != null) {
	        try {
	        	canchasService.delete(CanchaID);
	            return new ResponseEntity<>("Cancha eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar la cancha porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar la cancha", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    return new ResponseEntity<>("Cancha ID " + CanchaID + " no encontrado", HttpStatus.NOT_FOUND);
	}
	
	/**
     * Buscar la disponibilidad de las canchas según la fecha y el número de jugadores.
     * 
     * @param fechaConsulta la fecha de consulta de disponibilidad
     * @param numJugadores el número de jugadores para la consulta
     * @return una lista de la disponibilidad de las canchas
     */
	
	@GetMapping("/xfechanum/{fechaConsulta}/{numJugadores}")
	public Collection<BuscarDisponibilidadModel> findDisponibilidadByFechaAndJugadores(
	        @PathVariable("fechaConsulta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaConsulta,
	        @PathVariable("numJugadores") int numJugadores) {
	    
		// Convertir LocalDate a java.sql.Date
	    	Date sqlDate = Date.valueOf(fechaConsulta);
	    // Llamar al servicio con la fecha y el número de cancha
	    return canchasService.findDisponibilidadByFechaAndJugadores(sqlDate, numJugadores);
	}
}
