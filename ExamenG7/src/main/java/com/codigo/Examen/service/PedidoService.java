package com.codigo.Examen.service;

import com.codigo.Examen.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {
    PedidoEntity guardarPedido(Long personaId, PedidoEntity pedido);
    PedidoEntity obtenerPedidoPorId(Long id);
    List<PedidoEntity> obtenerTodosLosPedidos();
    PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido);
    void eliminarPedido(Long id);
}
