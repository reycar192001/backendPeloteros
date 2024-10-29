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

import com.peloteros.app.peloteros.entity.Categoria;
import com.peloteros.app.peloteros.service.CategoriaService;

@RestController 
@RequestMapping("/categoria")
@CrossOrigin(origins= {"http://localhost/5500"})
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	public CategoriaController() {
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Categoria> repository = categoriaService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Categoria categoria : repository) {
	        Map<String, Object> categoriaMap = new HashMap<>();
	        categoriaMap.put("CategoriaID", categoria.getCategoriaID());
	        categoriaMap.put("NombreCat", categoria.getNombreCat());
	        categoriaMap.put("Estado", categoria.getEstado());
	        
	        responseList.add(categoriaMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{CategoriaID}")
	public ResponseEntity<?> buscar(@PathVariable Integer CategoriaID) {
		Categoria categoria = categoriaService.findById(CategoriaID);
	    if (categoria != null) {
	        Map<String, Object> categoriaMap = new HashMap<>();
	        categoriaMap.put("CategoriaID", categoria.getCategoriaID());
	        categoriaMap.put("NombreCat", categoria.getNombreCat());
	        categoriaMap.put("Estado", categoria.getEstado());
	        return new ResponseEntity<>(categoriaMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Categoria ID " + CategoriaID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") //Http Method POST
	public ResponseEntity<?> agregar(@RequestBody Categoria categoria) {
		int isExist = categoriaService.isExistNombre(categoria.getNombreCat());
		if(isExist==0)
		{
			categoriaService.insert(categoria);
	        return new ResponseEntity<>("Categoria Creada", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("La categoria no se puede registrar porque " + categoria.getNombreCat().toUpperCase() + " ya existe",HttpStatus.CONFLICT); //Http status code
	}
	
	@PutMapping("/editar/{CategoriaID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer CategoriaID,
                                        @RequestBody Categoria newCategoria) {		
		Categoria categoria = categoriaService.findById(CategoriaID);		
		if(categoria!=null) {
			categoria.setNombreCat(newCategoria.getNombreCat());
			categoriaService.update(categoria);			
			return new ResponseEntity<>("Plato editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Categoria ID " + CategoriaID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{CategoriaID}")
	public ResponseEntity<?> borrar(@PathVariable Integer CategoriaID) {
	    Categoria categoria = categoriaService.findById(CategoriaID);

	    if (categoria!= null) {
	        try {
	        	categoriaService.delete(CategoriaID);
	            return new ResponseEntity<>("Categoria eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar la categoria porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar la categoria", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Categoria ID " + CategoriaID + " no encontrado", HttpStatus.NOT_FOUND);
	}


}
