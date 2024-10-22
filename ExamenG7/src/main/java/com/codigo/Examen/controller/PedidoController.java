package com.codigo.Examen.controller;

import com.codigo.Examen.entity.PedidoEntity;
import com.codigo.Examen.entity.PersonaEntity;
import com.codigo.Examen.service.PedidoService;
import com.codigo.Examen.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pedidos/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/persona/{personaId}")
    public ResponseEntity<PedidoEntity> crearPedido(@PathVariable Long personaId,
                                                    @RequestBody PedidoEntity pedido) {
        PedidoEntity nuevoPedido = pedidoService.guardarPedido(personaId, pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> obtenerPedido(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.obtenerPedidoPorId(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }


    @PutMapping("/{id}/persona/{personaId}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long personaId,
                                                         @RequestBody PedidoEntity pedido) {
        PedidoEntity pedidoActualizado =pedidoService.actualizarPedido(id, personaId, pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

