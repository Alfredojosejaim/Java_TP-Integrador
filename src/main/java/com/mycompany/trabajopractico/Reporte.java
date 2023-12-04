package com.mycompany.trabajopractico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Incidente> incidentes;

    public Reporte() {
        this.incidentes = new ArrayList<Incidente>();
    }

    public void addIncidente(Incidente incidente) {
        this.incidentes.add(incidente);
    }

    public List<Incidente> getIncidentes() {
        return this.incidentes;
    }
}