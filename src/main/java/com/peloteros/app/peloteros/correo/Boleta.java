package com.peloteros.app.peloteros.correo;


public class Boleta {
	private String tipodoc;    
    private String nombreCliente;  
    private String dni;
    private String direccion; 
    private String email; 
    private String fecha;
    private String hora;
    private Integer idcancha;
    private String precio;
 // private List<DetalleVentaEntity> detalles;
    
    
	public String getTipodoc() {
		return tipodoc;
	}
	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Integer getIdcancha() {
		return idcancha;
	}
	public void setIdcancha(Integer idcancha) {
		this.idcancha = idcancha;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Boleta(String tipodoc, String nombreCliente, String dni, String direccion, String email, String fecha,
			String hora, Integer idcancha, String precio) {
		super();
		this.tipodoc = tipodoc;
		this.nombreCliente = nombreCliente;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.fecha = fecha;
		this.hora = hora;
		this.idcancha = idcancha;
		this.precio = precio;
	}
	public Boleta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	  
}