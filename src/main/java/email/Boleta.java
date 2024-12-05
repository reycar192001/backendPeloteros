package email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder           
@AllArgsConstructor 
@NoArgsConstructor  
@Data 
public class Boleta {
    private String para;
    private String nombreCliente;  
    private String dni;
    private String direccion; 
    private String email;     
    private String numeroBoleta;
    private String fecha; 
    // private List<DetalleVentaEntity> detalles;
    private double total;   
}