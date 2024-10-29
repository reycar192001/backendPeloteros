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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.peloteros.app.peloteros.entity.AuthCredentials;
import com.peloteros.app.peloteros.entity.Roles;
import com.peloteros.app.peloteros.entity.Usuarios;
import com.peloteros.app.peloteros.service.UsuariosService;

@RestController 
@RequestMapping("/usuarios")
@CrossOrigin(origins= {"http://localhost/5500"})
public class UsuariosController {
	
	@Autowired
	private UsuariosService usuariosService;
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private final AuthenticationManager authenticationManager;

    @Autowired
    public UsuariosController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentials authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Usuario autenticado con éxito");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
	
	@GetMapping("/listar")
	public ResponseEntity<List<Map<String, Object>>> listar() {
	    Collection<Usuarios> repository = usuariosService.findAll();
	    
	    if (repository.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<Map<String, Object>> responseList = new ArrayList<>();
	    
	    for (Usuarios usuarios : repository) {
	        Map<String, Object> usuarioMap = new HashMap<>();
	        usuarioMap.put("UsuarioID", usuarios.getUsuarioID());
	        usuarioMap.put("Nombre", usuarios.getNombre());
	        usuarioMap.put("Correo", usuarios.getCorreo());
	        usuarioMap.put("Telefono", usuarios.getTelefono());
	        usuarioMap.put("Password", usuarios.getPassword());
	        
	        Roles roles = usuarios.getRolesObj();
	        if (roles!= null) {
	            usuarioMap.put("RoleID", roles.getRoleID());
	        }
	        
	        responseList.add(usuarioMap);
	    }
	    
	    return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{UsuarioID}")
	public ResponseEntity<?> buscar(@PathVariable Integer UsuarioID) {
	    Usuarios usuarios = usuariosService.findById(UsuarioID);
	    if (usuarios != null) {
	        Map<String, Object> usuarioMap = new HashMap<>();
	        usuarioMap.put("UsuarioID", usuarios.getUsuarioID());
	        usuarioMap.put("Nombre", usuarios.getNombre());
	        usuarioMap.put("Correo", usuarios.getCorreo());
	        usuarioMap.put("Telefono", usuarios.getTelefono());
	        usuarioMap.put("Password", usuarios.getPassword());

	        Roles roles = usuarios.getRolesObj();
	        if (roles!= null) {
	            usuarioMap.put("RoleID", roles.getRoleID());
	        }

	        return new ResponseEntity<>(usuarioMap, HttpStatus.OK); // Código de estado HTTP
	    }
	    return new ResponseEntity<>("Usuario ID " + UsuarioID + " no encontrado", HttpStatus.NOT_FOUND); // Código de estado HTTP
	}
	
	@PostMapping("/agregar") // Http Method POST
    public ResponseEntity<?> agregar(@RequestBody Usuarios usuarios) {
        int isExist = usuariosService.isExistNombre(usuarios.getNombre());
        if (isExist == 0) {
            // Encriptar la contraseña antes de guardar
            String encodedPassword = passwordEncoder.encode(usuarios.getPassword());
            usuarios.setPassword(encodedPassword); // Asignar la contraseña encriptada
            usuariosService.insert(usuarios);
            return new ResponseEntity<>("Usuario Creado", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("El usuario no se puede registrar porque " + usuarios.getNombre().toUpperCase() + " ya existe", HttpStatus.CONFLICT); // Http status code
    }
	
	@PutMapping("/editar/{UsuarioID}") //Http Method PUT
	public ResponseEntity<?> editar(@PathVariable Integer UsuarioID,
                                        @RequestBody Usuarios newUsuario) {		
		Usuarios usuarios = usuariosService.findById(UsuarioID);		
		if(usuarios!=null) {
			usuarios.setNombre(newUsuario.getNombre());
			usuarios.setCorreo(newUsuario.getCorreo());
			usuarios.setTelefono(newUsuario.getTelefono());
			usuarios.setPassword(newUsuario.getPassword());
			usuarios.setRolesObj(newUsuario.getRolesObj());
			usuariosService.update(usuarios);			
			return new ResponseEntity<>("Usuario editado",HttpStatus.OK); //Http status code
		}		
		return new ResponseEntity<>("Usuario ID " + UsuarioID + " no encontrado",HttpStatus.NOT_FOUND); //Http status code
	}
	
	@DeleteMapping("/borrar/{UsuarioID}")
	public ResponseEntity<?> borrar(@PathVariable Integer UsuarioID) {
	    Usuarios usuarios = usuariosService.findById(UsuarioID);

	    if (usuarios!= null) {
	        try {
	            usuariosService.delete(UsuarioID);
	            return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
	        } catch (DataIntegrityViolationException e) {
	            return new ResponseEntity<>("No se puede eliminar el usuario porque está relacionado con otros registros", HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Se produjo un error al intentar eliminar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("Usuario ID " + UsuarioID + " no encontrado", HttpStatus.NOT_FOUND);
	}

}
