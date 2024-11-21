package email;

import java.io.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import email.GeneratePdf;
//import pe.idat.edu.Entity.DetalleVentaEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BoletaController {
    @Autowired
    private JavaMailSender emailSender;

    
    /*
    
    @PostMapping("/envioboleta")
    public ResponseEntity<Void> generarBoleta(@RequestBody Boleta boleta) {
        try {
            byte[] pdf = GeneratePdf.generatePDF2(boleta);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(boleta.getPara());
            helper.setSubject("Lau Chun agradece su preferencia estimado(a) "+boleta.getNombreCliente());
            helper.setText("Gracias por comprar con nosotros, su codigo de compra es  " + boleta.getNumeroBoleta() + ", adjunto encontrará el documento correspondiente a la compra realizada.");
            helper.addAttachment("Boleta-"+boleta.getNumeroBoleta()+".pdf", new ByteArrayResource(pdf));
            emailSender.send(message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }   
    */
    @PostMapping("/envioboleta")
    public ResponseEntity<Void> generarBoleta() {
        try {
//	        byte[] pdf = GeneratePdf.generatePDF2();
//	        MimeMessage message = emailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//	        helper.setTo("guerrero964759246@gmail.com");
//	        helper.setSubject("Lau Chun agradece su preferencia estimado(a) Harvey guerrero");
//	        helper.setText("Gracias por comprar con nosotros, su codigo de compra es  B001, adjunto encontrará el documento correspondiente a la compra realizada.");
//	        helper.addAttachment("Boleta-B001.pdf", new ByteArrayResource(pdf));
//	        emailSender.send(message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } 
     
}
