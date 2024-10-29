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

import com.peloteros.app.peloteros.entity.Roles;
import com.peloteros.app.peloteros.service.RolesService;

@RestController 
@RequestMapping("/roles")
@CrossOrigin(origins= {"http://localhost/5500"})
public class RolesController {
	
	@Autowired
	private RolesService rolesService;

	public RolesController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Roles> repository = rolesService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Roles roles : repository) {
	        Map<String, Object> rolesMap = new HashMap<>();
	        rolesMap.put("RoleID", roles.getRoleID());
	        rolesMap.put("Nombre", roles.getNombre());
	        
	        responseList.add(rolesMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{RoleID}")
	public ResponseEntity<?> buscar(@PathVariable Integer RoleID) {
		Roles roles = rolesService.findById(RoleID);
	    if (roles != null) {
	        Map<String, Object> rolesMap = new HashMap<>();
	        rolesMap.put("RoleID", roles.getRoleID());
	        rolesMap.put("Nombre", roles.getNombre());
	        return new ResponseEntity<>(rolesMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Rol ID " + RoleID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Roles roles) {
		System.out.println("Rol recibido: " + roles.getNombre());
		int isExist = rolesService.isExistNombre(roles.getNombre());
		if(isExist==0)
		{
	        rolesService.insert(roles);
	        return new ResponseEntity<>("Rol Creado", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("El rol no se puede registrar porque " + roles.getNombre().toUpperCase() + " ya existe",HttpStatus.CONFLICT); //Http status code
	}
	
	@PutMapping("/editar/{RoleID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer RoleID,
                                        @RequestBody Roles newRole) {		
		Roles roles = rolesService.findById(RoleID);		
		if(roles!=null) {
			roles.setNombre(newRole.getNombre());
			rolesService.update(roles);			
			return new ResponseEntity<>("Plato editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Rol ID " + RoleID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{RoleID}")
	public ResponseEntity<?> borrar(@PathVariable Integer RoleID) {
	    Roles roles = rolesService.findById(RoleID);

	    if (roles!= null) {
	        try {
	            rolesService.delete(RoleID);
	            return new ResponseEntity<>("Rol eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el rolo porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el rol", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Plato ID " + RoleID + " no encontrado", HttpStatus.NOT_FOUND);
	}

}
