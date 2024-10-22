package com.codigo.Examen.service.impl;

import com.codigo.Examen.entity.PedidoEntity;
import com.codigo.Examen.entity.PersonaEntity;
import com.codigo.Examen.repository.PedidoRepository;
import com.codigo.Examen.repository.PersonaRepository;
import com.codigo.Examen.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PersonaRepository personaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public PedidoEntity guardarPedido(Long personaId, PedidoEntity pedido) {
        PersonaEntity personaExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Error: Persona no encontrada"));
        pedido.setPersona(personaExistente);
        return pedidoRepository.save(pedido);
    }

    @Override
    public PedidoEntity obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
    }

    @Override
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        PersonaEntity personaExistente = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Error: Persona no encontrada"));

        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setCantidad(pedido.getCantidad());
        pedidoExistente.setEstado(pedido.getEstado());
        pedidoExistente.setPersona(personaExistente);

        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoExistente.setEstado("ELIMINADO");
        pedidoRepository.save(pedidoExistente);
    }
}
