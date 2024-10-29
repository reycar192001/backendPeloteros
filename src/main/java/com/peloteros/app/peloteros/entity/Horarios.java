package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

@Entity
@Table(name="horarios")
public class Horarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer HorarioID;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate Fecha;
	
	@Column(nullable = false)
	private LocalTime HoraInicio;
	
	@Column(nullable = false)
	private LocalTime HoraFin;
	
	@Column(nullable = false)
	private String Estado;
	
	@OneToMany(mappedBy = "horariosObj")
	@JsonManagedReference("horarios-reservas")
	private Collection<Reservas> itemReservas=new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="horarios_canchas",
	joinColumns=@JoinColumn(name = "Horario_ID"),
	inverseJoinColumns = @JoinColumn(name="Cancha_ID"))
	private Set<Canchas> itemsCanchas=new HashSet<>();
	
	public Horarios() {
	}

	public Horarios(Integer horarioID, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
		super();
		HorarioID = horarioID;
		Fecha = fecha;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
		Estado = estado;
	}
	
	@PostPersist
	public void portPersist() {
		Fecha = LocalDate.now();
	}

	public Integer getHorarioID() {
		return HorarioID;
	}

	public void setHorarioID(Integer horarioID) {
		HorarioID = horarioID;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return HoraInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		HoraInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return HoraFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		HoraFin = horaFin;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Collection<Reservas> getItemReservas() {
		return itemReservas;
	}

	public void setItemReservas(Collection<Reservas> itemReservas) {
		this.itemReservas = itemReservas;
	}

	public Set<Canchas> getItemsCanchas() {
		return itemsCanchas;
	}

	public void setItemsCanchas(Set<Canchas> itemsCanchas) {
		this.itemsCanchas = itemsCanchas;
	}

	public void addCanchas(Canchas canchas) {
		itemsCanchas.add(canchas);
	}
	
}
