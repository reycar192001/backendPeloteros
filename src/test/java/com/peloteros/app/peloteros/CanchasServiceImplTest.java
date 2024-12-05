package com.peloteros.app.peloteros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.repository.CanchasRepository;
import com.peloteros.app.peloteros.service.CanchasServiceImpl;

@SpringBootTest
public class CanchasServiceImplTest {

    @Autowired
    private CanchasServiceImpl canchasService;

    @Autowired
    private CanchasRepository canchasRepository;

    @BeforeEach
    public void setUp() {
        canchasRepository.deleteAll();
    }

    @Test
    public void testInsert() {
        Canchas cancha = new Canchas();
        cancha.setNumeroCancha(5);
        cancha.setDescripcion("Cancha de fútbol");
        cancha.setEstado("Disponible");
        cancha.setImagen("imagen.png");
        cancha.setPrecioPorHora(100.0); 

        canchasService.insert(cancha);

        Canchas foundCancha = canchasRepository.findById(cancha.getCanchaID()).orElse(null);
        assertNotNull(foundCancha);
        assertEquals(5, foundCancha.getNumeroCancha());
        assertEquals("Cancha de fútbol", foundCancha.getDescripcion());
        assertEquals("Disponible", foundCancha.getEstado());
        assertEquals("imagen.png", foundCancha.getImagen());
        assertEquals(100.0, foundCancha.getPrecioPorHora());
    }
}