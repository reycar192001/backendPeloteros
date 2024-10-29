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

import com.peloteros.app.peloteros.entity.Pagos;
import com.peloteros.app.peloteros.entity.Reservas;
import com.peloteros.app.peloteros.service.PagosService;

@RestController 
@RequestMapping("/pagos")
@CrossOrigin(origins= {"http://localhost/5500"})
public class PagosController {

	@Autowired
	private PagosService pagosService;
	
	public PagosController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Pagos> repository = pagosService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Pagos pagos : repository) {
	        Map<String, Object> pagosMap = new HashMap<>();
	        pagosMap.put("PagoID", pagos.getPagoID());
	        pagosMap.put("Monto", pagos.getMonto());
	        pagosMap.put("FechaPago", pagos.getFechaPago());
	        pagosMap.put("MetodoPago", pagos.getMetodoPago());
	        
	        Reservas reservas = pagos.getReservasObj();
	        if (reservas!= null) {
	            pagosMap.put("ReservaID", reservas.getReservaID());
	        }
	        
	        responseList.add(pagosMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{PagoID}")
	public ResponseEntity<?> buscar(@PathVariable Integer PagoID) {
	    Pagos pagos = pagosService.findById(PagoID);
	    if (pagos != null) {
	        Map<String, Object> pagosMap = new HashMap<>();
	        pagosMap.put("PagoID", pagos.getPagoID());
	        pagosMap.put("Monto", pagos.getMonto());
	        pagosMap.put("FechaPago", pagos.getFechaPago());
	        pagosMap.put("MetodoPago", pagos.getMetodoPago());
	        
	        Reservas reservas = pagos.getReservasObj();
	        if (reservas!= null) {
	            pagosMap.put("ReservaID", reservas.getReservaID());
	        }

	        return new ResponseEntity<>(pagosMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("pago ID " + PagoID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Pagos pagos) {	
		pagosService.insert(pagos);		
		return new ResponseEntity<>("Pago Creado",HttpStatus.CREATED); //Http status code	
	}
	
	@PutMapping("/editar/{PagoID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer PagoID,
                                        @RequestBody Pagos newPago) {		
		Pagos pagos = pagosService.findById(PagoID);		
		if(pagos!=null) {
			pagos.setMonto(newPago.getMonto());
			pagos.setFechaPago(newPago.getFechaPago());;
			pagos.setMetodoPago(newPago.getMetodoPago());
			pagos.setReservasObj(newPago.getReservasObj());
			pagosService.update(pagos);			
			return new ResponseEntity<>("Pago editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Pago ID " + PagoID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{PagoID}")
	public ResponseEntity<?> borrar(@PathVariable Integer PagoID) {
	    Pagos pagos = pagosService.findById(PagoID);

	    if (pagos!= null) {
	        try {
	        	pagosService.delete(PagoID);
	            return new ResponseEntity<>("Pago eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el pago porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el pago", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Pago ID " + PagoID + " no encontrado", HttpStatus.NOT_FOUND);
	}

}
