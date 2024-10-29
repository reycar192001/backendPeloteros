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

import com.peloteros.app.peloteros.entity.Descuentos;
import com.peloteros.app.peloteros.service.DescuentosService;

@RestController 
@RequestMapping("/descuentos")
@CrossOrigin(origins= {"http://localhost/5500"})
public class DescuentosController {
	
	@Autowired
	private DescuentosService descuentosService;
	
	public DescuentosController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Descuentos> repository = descuentosService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Descuentos descuentos : repository) {
	        Map<String, Object> descuentosMap = new HashMap<>();
	        descuentosMap.put("DescuentoID", descuentos.getDescuentoID());
	        descuentosMap.put("Descripcion", descuentos.getDescripcion());
	        descuentosMap.put("PorcentajeDescuento", descuentos.getPorcentajeDescuento());
	        descuentosMap.put("FechaInicio", descuentos.getFechaInicio());
	        descuentosMap.put("FechaFin", descuentos.getFechaFin());
	        
	        responseList.add(descuentosMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{DescuentoID}") //Http Method GET
	public ResponseEntity<?> buscar(@PathVariable Integer DescuentoID) {
	    Descuentos descuentos = descuentosService.findById(DescuentoID);
	    if (descuentos != null) {
	        Map<String, Object> descuentosMap = new HashMap<>();
	        descuentosMap.put("DescuentoID", descuentos.getDescuentoID());
	        descuentosMap.put("Descripcion", descuentos.getDescripcion());
	        descuentosMap.put("PorcentajeDescuento", descuentos.getPorcentajeDescuento());
	        descuentosMap.put("FechaInicio", descuentos.getFechaInicio());
	        descuentosMap.put("FechaFin", descuentos.getFechaFin());

	        return new ResponseEntity<>(descuentosMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("DescuentoID " + DescuentoID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Descuentos descuentos) {	
		descuentosService.insert(descuentos);		
		return new ResponseEntity<>("Descuento Creado",HttpStatus.CREATED); //Http status code	
	}
	
	@PutMapping("/editar/{DescuentoID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer DescuentoID,
                                        @RequestBody Descuentos newDescuento) {		
		Descuentos descuentos = descuentosService.findById(DescuentoID);		
		if(descuentos!=null) {
			descuentos.setDescripcion(newDescuento.getDescripcion());
			descuentos.setPorcentajeDescuento(newDescuento.getPorcentajeDescuento());
			descuentos.setFechaInicio(newDescuento.getFechaInicio());
			descuentos.setFechaFin(newDescuento.getFechaFin());
			descuentosService.update(descuentos);			
			return new ResponseEntity<>("Descuento editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Descuento ID " + DescuentoID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{DescuentoID}")
	public ResponseEntity<?> borrar(@PathVariable Integer DescuentoID) {
	    Descuentos descuentos = descuentosService.findById(DescuentoID);
	    
	    if (descuentos != null) {
	        try {
	        	descuentosService.delete(DescuentoID);
	            return new ResponseEntity<>("Descuento eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el descuento porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el descuento", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    return new ResponseEntity<>("Descuento ID " + DescuentoID + " no encontrado", HttpStatus.NOT_FOUND);
	}
}
