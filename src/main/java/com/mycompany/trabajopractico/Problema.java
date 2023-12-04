package com.mycompany.trabajopractico;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Problema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private TipoProblema tipo;

    @OneToMany(mappedBy = "problema")
    private List<Tecnico> listaTecnico;

    private LocalTime fechaTentativa;

    private String codigo;
    private LocalTime tiempoEstimado;

    @ManyToOne
    private Incidente incidente;

    public Problema() {
    }

    public Problema(String codigo, String descripcion, TipoProblema tipo, LocalTime tiempoEstimado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.tiempoEstimado = tiempoEstimado;
    }

    // Método para asignar incidente
    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
        if (incidente != null) {
            incidente.addProblema(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoProblema(TipoProblema tipo) {
        this.tipo = tipo;
    }

    public void setListaTecnico(List<Tecnico> tecnicos) {
        this.listaTecnico = tecnicos;
    }

    public void setFechaTentativa(LocalTime fechaTentativa) {
        this.fechaTentativa = fechaTentativa;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public TipoProblema getTipoProblema() {
        return this.tipo;
    }

    public List<Tecnico> getListaTecnicos() {
        return this.listaTecnico;
    }

    public LocalTime getFechaTentativa() {
        return this.fechaTentativa;
    }

    public Incidente getIncidente() {
        return this.incidente;
    }
}