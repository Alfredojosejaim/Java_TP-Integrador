package com.mycompany.trabajopractico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "incidentes")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Problema> listaProblemas;

    @Enumerated(EnumType.STRING)
    private EstadoIncidente estado;

    @ManyToOne
    private Reporte reporte;

    @ManyToOne
    private Tecnico tecnico;

    @ManyToOne
    private Cliente cliente;

    public Incidente() {
        this.listaProblemas = new ArrayList<>();
        this.estado = EstadoIncidente.EN_PROCESO;
    }

    public void setEstado(EstadoIncidente estado) {
        this.estado = estado;
    }

    public void addProblema(Problema problema) {
        this.listaProblemas.add(problema);
    }

    public void setProblema(Problema problema) {
        this.listaProblemas.clear();
        this.listaProblemas.add(problema);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoIncidente getEstado() {
        return this.estado;
    }

    public List<Problema> getListaProblemas() {
        return this.listaProblemas;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Reporte getReporte() {
        return this.reporte;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Tecnico getTecnico() {
        return this.tecnico;
    }
}