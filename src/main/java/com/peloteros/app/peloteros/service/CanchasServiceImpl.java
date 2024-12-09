package com.peloteros.app.peloteros.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.BuscarDisponibilidadModel;
import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.repository.CanchasRepository;

/**
 * Implementación del servicio de canchas que gestiona las operaciones CRUD 
 * y la búsqueda de disponibilidad de canchas.
 */
@Service
public class CanchasServiceImpl implements CanchasService {

    @Autowired
    private CanchasRepository repository;

    /**
     * Inserta una nueva cancha en la base de datos.
     * 
     * @param canchas la cancha a insertar
     */
    @Override
    @Transactional
    public void insert(Canchas canchas) {
        repository.save(canchas);
    }

    /**
     * Actualiza una cancha existente en la base de datos.
     * 
     * @param canchas la cancha a actualizar
     */
    @Override
    @Transactional
    public void update(Canchas canchas) {
        repository.save(canchas);
    }

    /**
     * Elimina una cancha de la base de datos por su ID.
     * 
     * @param CanchaID el ID de la cancha a eliminar
     */
    @Override
    @Transactional
    public void delete(Integer CanchaID) {
        repository.deleteById(CanchaID);
    }

    /**
     * Busca una cancha por su ID.
     * 
     * @param CanchaID el ID de la cancha a buscar
     * @return la cancha encontrada, o null si no existe
     */
    @Override
    @Transactional(readOnly = true)
    public Canchas findById(Integer CanchaID) {
        return repository.findById(CanchaID).orElse(null);
    }

    /**
     * Recupera todas las canchas de la base de datos.
     * 
     * @return una colección de todas las canchas
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Canchas> findAll() {
        return repository.findAll();
    }

    /**
     * Busca la disponibilidad de las canchas según la fecha y el número de jugadores.
     * 
     * @param fechaConsulta la fecha de consulta
     * @param numJugadores el número de jugadores para verificar la disponibilidad
     * @return una lista de modelos de disponibilidad de canchas
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<BuscarDisponibilidadModel> findDisponibilidadByFechaAndJugadores(Date fechaConsulta, int numJugadores) {
        List<Object[]> results = repository.findDisponibilidadByFechaAndJugadores(fechaConsulta, numJugadores);
        
        List<BuscarDisponibilidadModel> disponibilidadList = new ArrayList<>();
        for (Object[] row : results) {
            BuscarDisponibilidadModel disponibilidad = new BuscarDisponibilidadModel();
	        disponibilidad.setHorarioId((Integer) row[0]);
	        disponibilidad.setHoraInicio((Time) row[1]);
	        disponibilidad.setCanchaId((Integer) row[2]);
	        disponibilidad.setNumeroCancha((Integer) row[3]);
	        disponibilidad.setPrecioHora((Double) row[4]);
	        disponibilidad.setEstado((String) row[5]);
	        disponibilidadList.add(disponibilidad);
        }
        
        return disponibilidadList;
    }
}

