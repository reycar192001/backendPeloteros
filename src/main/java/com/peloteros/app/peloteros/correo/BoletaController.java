package com.peloteros.app.peloteros.correo;

import java.io.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peloteros.app.peloteros.entity.RestoredCredentials;
import com.peloteros.app.peloteros.entity.Usuarios;
import com.peloteros.app.peloteros.security.JwtUtil;
import com.peloteros.app.peloteros.service.CanchasService;
import com.peloteros.app.peloteros.service.ReservasService;
import com.peloteros.app.peloteros.service.UsuariosService;

import java.io.IOException;

import java.util.Properties;
import java.util.regex.Pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



//import pe.idat.edu.Entity.DetalleVentaEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/correo")
@CrossOrigin(origins = "*")
public class BoletaController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoletaController.class);
    
    
    @Autowired
    private JavaMailSenderImpl emailSender;
    
    @Autowired
    private PasswordGenerator passwordgenerator;
    @Autowired
	private UsuariosService usuariosService;
    
    @Autowired
    private  ReservasService canchaService;
	
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
	
    @PostMapping("/envioboleta")
    public ResponseEntity<String> generarBoleta(@RequestBody Boleta boleta) {
    	 logger.info("Iniciando generación de boleta para: {}", boleta.getEmail());
    	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	    logger.info("Autenticación actual: {}", auth);
    	    Long lastId = canchaService.getMaxCanchaId();    	    

    	    try {
    	        // Validación del correo
    	        if (!isValidEmail(boleta.getEmail())) {
    	            return ResponseEntity
    	                    .badRequest()
    	                    .body("Formato de correo inválido");
    	        }
    	        // Generación del PDF
    	        byte[] pdf = GeneratePdf.generatePDF2(boleta, lastId);
    	        if (pdf == null || pdf.length == 0) {
    	            logger.error("Error al generar el PDF para: {}", boleta.getEmail());
    	            return ResponseEntity
    	                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
    	                    .body("Error generando el PDF");
    	        }

    	        // Configuración del correo
    	        var message = emailSender.createMimeMessage();
    	        var helper = new MimeMessageHelper(message, true);

    	        logger.info("Preparando correo para: {}", boleta.getEmail());
    	        helper.setTo(boleta.getEmail());
    	        helper.setSubject("Gracias por su preferencia - Detalles de su reserva");

    	        // Contenido del correo en HTML
    	        helper.setText(
    	                "<html>" +
    	                        "<body>" +
    	                        "<p>Estimado(a) <strong>" + boleta.getNombreCliente() + "</strong>,</p>" +
    	                        "<p>Agradecemos su preferencia en <strong>Peloteros</strong>. Nos complace confirmar su reserva.</p>" +
    	                        "<p>En el documento adjunto encontrará el detalle de su transacción:</p>" +
    	                        "<ul>" +
    	                        "<li><strong>Tipo de documento:</strong> " + boleta.getTipodoc() + "</li>" +
    	                        "<li><strong>Número:</strong> " + (boleta.getTipodoc().equals("Boleta") ? "B000" : "F000-00") + lastId + "</li>" +
    	                        "<li><strong>Cliente:</strong> " + boleta.getNombreCliente() + "</li>" +
    	                        "<li><strong>Importe:</strong> S/" + boleta.getPrecio() + "</li>" +
    	                        "<li><strong>Cancha N°:</strong> " + boleta.getIdcancha() + "</li>" +
    	                        "<li><strong>Fecha reservada:</strong> " + boleta.getFecha() + "</li>" +
    	                        "<li><strong>Hora:</strong> " + boleta.getHora() + "</li>" +
    	                        "</ul>" +
    	                        "<p>Si tiene alguna consulta o necesita asistencia, no dude en contactarnos respondiendo a este correo.</p>" +
    	                        "<p>¡Le deseamos una excelente experiencia en nuestras instalaciones!</p>" +
    	                        "<p style='margin-top: 20px;'>Atentamente,</p>" +
    	                        "<p><strong>Equipo de Peloteros</strong></p>" +
    	                        "<hr>" +
    	                        "<p style='font-size: 12px; color: #666;'>Este correo es generado automáticamente. Por favor, no responda directamente a este mensaje.</p>" +
    	                        "</body>" +
    	                        "</html>",true
    	        );

    	        // Adjuntar el PDF generado
    	        String fileName = boleta.getTipodoc().equals("Boleta") ? 
    	                          "Boleta-B000" + lastId + ".pdf" : 
    	                          "Factura-F000-00" + lastId + ".pdf";
    	        helper.addAttachment(fileName, new ByteArrayResource(pdf));

    	        // Enviar el correo
    	        emailSender.send(message);
    	        logger.info("Correo enviado exitosamente a: {}", boleta.getEmail());
    	        return ResponseEntity.ok("Correo enviado correctamente.");
    	    } catch (Exception e) {
    	        logger.error("Error al enviar el correo a {}: {}", boleta.getEmail(), e.getMessage());
    	        return ResponseEntity
    	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
    	                .body("No se pudo enviar el correo. Intente nuevamente.");
    	    }
    	}
    
    
    
    
    @PostMapping("/recuperarcontraseña")
    public ResponseEntity<String> recuperarcontraseña(@RequestBody RestoredCredentials credentials) {  
    	var pass = passwordgenerator.generatePassword(10);
    	
    	
        logger.info("Iniciando recuperación de contraseña para: {}", credentials.getCorreo());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Autenticación actual: {}", auth);

        try {
            if (!isValidEmail(credentials.getCorreo())) {
                return ResponseEntity
                        .badRequest()
                        .body("Formato de correo inválido");
            }          
                        
            var message = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            
            if(ifexistuser(credentials.getCorreo(), credentials.getNombre(),pass)) {     		
        	
            logger.info("Preparando correo para: {}", credentials.getCorreo());
            helper.setTo(credentials.getCorreo());
            helper.setSubject("Recuperación de Contraseña - Peloteros");
            helper.setText(
                    "<html>" +
                            "<body>" +
                            "<p>Estimado(a) <strong>" + credentials.getNombre().toUpperCase() + "</strong>,</p>" +
                            "<p>Hemos recibido una solicitud para restablecer su contraseña. A continuación, encontrará su contraseña temporal:</p>" +
                            "<div style='padding: 15px; background-color: #f4f4f4; border: 1px solid #ccc; margin: 10px 0; text-align: center;'>" +
                            "<h2 style='color: #333;'> " + pass + " </h2>" +
                            "</div>" +
                            "<p>Por favor, utilice esta contraseña para acceder a su cuenta y no olvide cambiarla después de iniciar sesión.</p>" +
                            "<p>Si usted no realizó esta solicitud, por favor ignore este mensaje o contáctenos inmediatamente.</p>" +
                            "<p style='margin-top: 20px;'>Atentamente,</p>" +
                            "<p><strong>Equipo de Peloteros</strong></p>" +
                            "<hr>" +
                            "<p style='font-size: 12px; color: #666;'>Este correo es generado automáticamente. Por favor, no responda a este mensaje.</p>" +
                            "</body>" +
                            "</html>",
                    true
            		);

            	emailSender.send(message);
            	logger.info("Correo enviado exitosamente a: {}", credentials.getCorreo());
            	return ResponseEntity.ok("Correo enviado correctamente.");
            }else {
            	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar la nueva contraseña.");
            }
        } catch (Exception e) {
            logger.error("Error al enviar el correo: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo enviar el correo. Intente nuevamente.");
        }
    }
    
    
    
    
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
    	Long lastId = canchaService.getMaxCanchaId();
        
        return ResponseEntity.ok("El ultimo id es: " + lastId);
    }
    
    private boolean ifexistuser(String email, String nombre,String pass) {
    	
    	String encodedPassword = passwordEncoder.encode(pass);
    	Usuarios usuarios = usuariosService.findByUsername(email);
	    if (usuarios != null) {	
	    	if(usuarios.getCorreo().equals(email) && usuarios.getNombre().equals(nombre)) {
	    		usuarios.setPassword(encodedPassword);			
				usuariosService.update(usuarios);
				System.out.println(pass);
				System.out.println(encodedPassword);
				return true;
	    	}else {
	    		return false;
	    	}
            
	    }else {
	    	return false;
	    } 
	  }
    
    

}
