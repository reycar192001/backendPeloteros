package com.peloteros.app.peloteros.controller;

import java.sql.Date;
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

import com.peloteros.app.peloteros.entity.Horarios;
import com.peloteros.app.peloteros.entity.Reservas;
import com.peloteros.app.peloteros.entity.Usuarios;
import com.peloteros.app.peloteros.service.ReservasService;

@RestController 
@RequestMapping("/reservas")
@CrossOrigin(origins= {"http://localhost/5500"})
public class ReservasController {
	
	@Autowired
	private ReservasService reservasService;
	
	@GetMapping("/listar") //Http Method POST
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Reservas> repository = reservasService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Reservas reservas : repository) {
	        Map<String, Object> reservasMap = new HashMap<>();
	        reservasMap.put("ReservaID", reservas.getReservaID());
	        reservasMap.put("Estado", reservas.getEstado());
	        reservasMap.put("FechaReserva", reservas.getFechaReserva());
	        
	        Usuarios usuarios = reservas.getUsuariosObj();
	        if (usuarios!= null) {
	            reservasMap.put("UsuarioID", usuarios.getUsuarioID());
	        }
	        
	        Horarios horarios = reservas.getHorariosObj();
	        if (horarios!= null) {
	            reservasMap.put("HorarioID", horarios.getHorarioID());
	        }
	        
	        responseList.add(reservasMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/listarxfecha/{fecha}") //Http Method POST
	public ResponseEntity<List<Map<String, Object>>> listarxfecha(@PathVariable Date fecha) {
	    Collection<Reservas> repository = reservasService.findAll_withDate(fecha);
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Reservas reservas : repository) {
	        Map<String, Object> reservasMap = new HashMap<>();
	        reservasMap.put("ReservaID", reservas.getReservaID());
	        reservasMap.put("Estado", reservas.getEstado());
	        reservasMap.put("FechaReserva", reservas.getFechaReserva());
	        
	        Usuarios usuarios = reservas.getUsuariosObj();
	        if (usuarios!= null) {
	            reservasMap.put("UsuarioID", usuarios.getUsuarioID());
	        }
	        
	        Horarios horarios = reservas.getHorariosObj();
	        if (horarios!= null) {
	            reservasMap.put("HorarioID", horarios.getHorarioID());
	        }
	        
	        responseList.add(reservasMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{ReservaID}") //Http Method GET
	public ResponseEntity<?> buscar(@PathVariable Integer ReservaID) {
	    Reservas reservas = reservasService.findById(ReservaID);
	    if (reservas != null) {
	        Map<String, Object> reservasMap = new HashMap<>();
	        reservasMap.put("ReservaID", reservas.getReservaID());
	        reservasMap.put("Estado", reservas.getEstado());
	        
	        Usuarios usuarios = reservas.getUsuariosObj();
	        if (usuarios!= null) {
	            reservasMap.put("UsuarioID", usuarios.getUsuarioID());
	        }
	        
	        Horarios horarios = reservas.getHorariosObj();
	        if (horarios!= null) {
	            reservasMap.put("HorarioID", horarios.getHorarioID());
	        }

	        return new ResponseEntity<>(reservasMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Reservas ID " + ReservaID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Reservas reservas) {
		reservasService.insert(reservas);		
		return new ResponseEntity<>("Reserva Creada",HttpStatus.CREATED); //Http status code	
	}
	
	@PutMapping("/editar/{ReservaID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer ReservaID,
                                        @RequestBody Reservas newReserva) {		
		Reservas reservas = reservasService.findById(ReservaID);		
		if(reservas!=null) {
			reservas.setEstado(newReserva.getEstado());
			reservas.setUsuariosObj(newReserva.getUsuariosObj());
			reservas.setHorariosObj(newReserva.getHorariosObj());
			reservasService.update(reservas);			
			return new ResponseEntity<>("Reserva editada",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Reserva ID " + ReservaID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{ReservaID}")
	public ResponseEntity<?> borrar(@PathVariable Integer ReservaID) {
	    Reservas reservas = reservasService.findById(ReservaID);

	    if (reservas != null) {
	        try {
	        	reservasService.delete(ReservaID);
	            return new ResponseEntity<>("Reserva eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar la reserva porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar la reserva", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("ReservaID " + ReservaID + " no encontrado", HttpStatus.NOT_FOUND);
	}

}
