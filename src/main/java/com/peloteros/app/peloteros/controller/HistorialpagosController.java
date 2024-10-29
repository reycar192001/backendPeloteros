package com.peloteros.app.peloteros.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.peloteros.app.peloteros.entity.Historialpagos;
import com.peloteros.app.peloteros.entity.Pagos;
import com.peloteros.app.peloteros.service.HistorialpagosService;


@RestController 
@RequestMapping("/historialpagos")
@CrossOrigin(origins= {"http://localhost/5500"})
public class HistorialpagosController {

	@Autowired
	private HistorialpagosService historialpagosService;
	
	public HistorialpagosController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Historialpagos> repository = historialpagosService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Historialpagos historialpagos : repository) {
	        Map<String, Object> historialMap = new HashMap<>();
	        historialMap.put("HistorialPagoID", historialpagos.getHistorialPagoID());
	        historialMap.put("FechaModificacion", historialpagos.getFechaModificacion());
	        historialMap.put("EstadoAnterior", historialpagos.getEstadoAnterior());
	        historialMap.put("EstadoNuevo", historialpagos.getEstadoNuevo());
	        historialMap.put("Descripcion", historialpagos.getDescripcion());
	        
	        Pagos pagos = historialpagos.getPagosObj();
	        if (pagos!= null) {
	        	historialMap.put("PagoID", pagos.getPagoID());
	        }
	        
	        responseList.add(historialMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{HistorialPagoID}")
	public ResponseEntity<?> buscar(@PathVariable Integer HistorialPagoID) {
	    Historialpagos historialpagos = historialpagosService.findById(HistorialPagoID);
	    if (historialpagos != null) {
	        Map<String, Object> historialMap = new HashMap<>();
	        historialMap.put("HistorialPagoID", historialpagos.getHistorialPagoID());
	        historialMap.put("FechaModificacion", historialpagos.getFechaModificacion());
	        historialMap.put("EstadoAnterior", historialpagos.getEstadoAnterior());
	        historialMap.put("EstadoNuevo", historialpagos.getEstadoNuevo());
	        historialMap.put("Descripcion", historialpagos.getDescripcion());
	        
	        Pagos pagos = historialpagos.getPagosObj();
	        if (pagos!= null) {
	        	historialMap.put("PagoID", pagos.getPagoID());
	        }

	        return new ResponseEntity<>(historialMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Historial ID " + HistorialPagoID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Historialpagos historialpagos) {	
		historialpagosService.insert(historialpagos);		
		return new ResponseEntity<>("Historial Creado",HttpStatus.CREATED); //Http status code	
	}
	
	@PutMapping("/editar/{HistorialPagoID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer HistorialPagoID,
                                        @RequestBody Historialpagos newHistorial) {		
		Historialpagos historialpagos = historialpagosService.findById(HistorialPagoID);		
		if(historialpagos!=null) {
			historialpagos.setFechaModificacion(newHistorial.getFechaModificacion());
			historialpagos.setEstadoAnterior(newHistorial.getEstadoAnterior());
			historialpagos.setEstadoNuevo(newHistorial.getEstadoNuevo());
			historialpagos.setDescripcion(newHistorial.getDescripcion());
			historialpagos.setPagosObj(newHistorial.getPagosObj());
			historialpagosService.update(historialpagos);			
			return new ResponseEntity<>("Historial editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Historial ID " + HistorialPagoID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{HistorialPagoID}")
	public ResponseEntity<?> borrar(@PathVariable Integer HistorialPagoID) {
	    Historialpagos historialpagos = historialpagosService.findById(HistorialPagoID);

	    if (historialpagos!= null) {
	        try {
	        	historialpagosService.delete(HistorialPagoID);
	            return new ResponseEntity<>("historial eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el historial porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el historial", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Historial ID " + HistorialPagoID + " no encontrado", HttpStatus.NOT_FOUND);
	}
}
