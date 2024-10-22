package com.codigo.Examen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private String estado;//Activo , inactivo

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;



    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoEntity> pedidos;
}