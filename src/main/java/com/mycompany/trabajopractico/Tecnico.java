package com.mycompany.trabajopractico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean estado;

    @ManyToOne
    private Problema problema;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SistemaOperativo> especialidadSistema;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SoporteOperativo> especialidadSoporte;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL)
    private List<Incidente> listaIncidentes;

    public Tecnico() {
        this.especialidadSistema = new ArrayList<>();
        this.especialidadSoporte = new ArrayList<>();
        this.listaIncidentes = new ArrayList<>();
        this.estado = true; // El técnico está disponible por defecto
    }

    public void setEspecialidadSistema(SistemaOperativo sistemaOperativo) {
        this.especialidadSistema.clear();
        this.especialidadSistema.add(sistemaOperativo);
    }

    public void setEspecialidadSoporte(SoporteOperativo soporteOperativo) {
        this.especialidadSoporte.clear();
        this.especialidadSoporte.add(soporteOperativo);
    }

    public void addIncidente(Incidente incidente) {
        this.listaIncidentes.add(incidente);
        incidente.setTecnico(this); // Asignar el técnico al incidente
        this.estado = false; // El técnico está ocupado cuando tiene incidentes asignados
    }

    public void removeIncidente(Incidente incidente) {
        this.listaIncidentes.remove(incidente);
        this.estado = this.listaIncidentes.isEmpty(); // El técnico está disponible si no tiene incidentes asignados
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public List<SistemaOperativo> getEspecialidadSistema() {
        return this.especialidadSistema;
    }

    public List<SoporteOperativo> getEspecialidadSoporte() {
        return this.especialidadSoporte;
    }

    public List<Incidente> getListaIncidentes() {
        return this.listaIncidentes;
    }
}