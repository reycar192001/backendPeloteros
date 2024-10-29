package com.peloteros.app.peloteros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.entity.Horarios;
import com.peloteros.app.peloteros.service.CanchasService;
import com.peloteros.app.peloteros.service.HorariosService;
import com.peloteros.app.peloteros.vo.HorariosCanchas;


@RestController
@RequestMapping("/horarioscanchas")
@CrossOrigin(origins= {"http://localhost/5500"})
public class HorariosCanchasController {
	
	@Autowired
	private HorariosService horariosService;
	
	@Autowired
	private CanchasService canchasService;
	
	public HorariosCanchasController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		return new ResponseEntity<>(horariosService.findAll_withCanchas(),HttpStatus.OK);
		
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody HorariosCanchas horariosCanchas) {
		Integer HorarioID = horariosCanchas.getHorarios().getHorarioID();
		Horarios horarioDb = horariosService.findById(HorarioID);
		
		if(horarioDb!=null)
		{
			Integer CanchaID = horariosCanchas.getCanchas().getCanchaID();
			Canchas canchasDb = canchasService.findById(CanchaID);
			
			if(canchasDb!=null)
			{
				horarioDb.addCanchas(canchasDb);
				horariosService.update(horarioDb);
				return new ResponseEntity<>("Horarios Canchas creado",HttpStatus.CREATED); //Http status code
			}
			return new ResponseEntity<>("Horario no encontrado",HttpStatus.NOT_FOUND); //Http status code
		}
		return new ResponseEntity<>("Canchas no encontrado!",HttpStatus.NOT_FOUND); //Http status code
		
	}
	
	@GetMapping("/buscar/{HorarioID}") //Http Method GET
	public ResponseEntity<?> buscar(@PathVariable Integer HorarioID) {
	    Horarios horarioDb = horariosService.findHorariosCanchasById(HorarioID);

	    if (horarioDb != null) {
	        Map<String, Object> horarioMap = new HashMap<>();
	        horarioMap.put("HorarioID", horarioDb.getHorarioID());
	        horarioMap.put("Estado", horarioDb.getEstado());

	        List<Integer> CanchaID = horarioDb.getItemsCanchas().stream()
	                .map(Canchas::getCanchaID)
	                .collect(Collectors.toList());
	        horarioMap.put("CanchaID", CanchaID);

	        return new ResponseEntity<>(horarioMap, HttpStatus.FOUND);
	    }

	    return new ResponseEntity<>("¡Horario Cancha ID " + HorarioID + " no encontrado!", HttpStatus.NOT_FOUND);
	}

}
