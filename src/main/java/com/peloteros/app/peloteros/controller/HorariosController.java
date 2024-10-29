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

import com.peloteros.app.peloteros.entity.Horarios;
import com.peloteros.app.peloteros.service.HorariosService;


@RestController 
@RequestMapping("/horarios")
@CrossOrigin(origins= {"http://localhost/5500"})
public class HorariosController {

	@Autowired
	private HorariosService horariosService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Horarios> repository = horariosService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Horarios horarios : repository) {
	        Map<String, Object> horarioMap = new HashMap<>();
	        horarioMap.put("HorarioID", horarios.getHorarioID());
	        horarioMap.put("Fecha", horarios.getFecha());
	        horarioMap.put("HoraInicio", horarios.getHoraInicio());
	        horarioMap.put("HoraFin", horarios.getHoraFin());
	        horarioMap.put("Estado", horarios.getEstado());
	         
	        responseList.add(horarioMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{HorarioID}")
	public ResponseEntity<?> buscar(@PathVariable Integer HorarioID) {
	    Horarios horarios = horariosService.findById(HorarioID);
	    if (horarios != null) {
	        Map<String, Object> horarioMap = new HashMap<>();
	        horarioMap.put("HorarioID", horarios.getHorarioID());
	        horarioMap.put("Fecha", horarios.getFecha());
	        horarioMap.put("HoraInicio", horarios.getHoraInicio());
	        horarioMap.put("HoraFin", horarios.getHoraFin());
	        horarioMap.put("Estado", horarios.getEstado());

	        return new ResponseEntity<>(horarioMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Horario ID " + HorarioID+ " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Horarios horarios) {	
		horariosService.insert(horarios);		
		return new ResponseEntity<>("Horario Creado",HttpStatus.CREATED); //Http status code	
	}
	
	@PutMapping("/editar/{HorarioID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer HorarioID,
                                        @RequestBody Horarios newHorario) {		
		Horarios horarios = horariosService.findById(HorarioID);		
		if(horarios!=null) {
			horarios.setHorarioID(newHorario.getHorarioID());
			horarios.setHoraInicio(newHorario.getHoraInicio());
			horarios.setHoraFin(newHorario.getHoraFin());
			horarios.setEstado(newHorario.getEstado());
			horariosService.update(horarios);			
			return new ResponseEntity<>("horario editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("horario ID " + HorarioID+ " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{HorarioID}")
	public ResponseEntity<?> borrar(@PathVariable Integer HorarioID) {
	    Horarios horarios = horariosService.findById(HorarioID);

	    if (horarios != null) {
	        try {
	        	horariosService.delete(HorarioID);
	            return new ResponseEntity<>("horario eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el horario porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el horario", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Horario ID " + HorarioID + " no encontrado", HttpStatus.NOT_FOUND);
	}
}
