package com.mycompany.trabajopractico;

import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TrabajoPractico {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrabajoPractico");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente1 = new Cliente("Empresa A", "123456789");
            Cliente cliente2 = new Cliente("Empresa B", "987654321");

            Incidente incidente1 = new Incidente();
            Incidente incidente2 = new Incidente();

            Problema problema1 = new Problema("P001", "Error en el sistema", TipoProblema.COMPLEJO, LocalTime.of(0, 30));
            Problema problema2 = new Problema("P002", "Problema de conexión", TipoProblema.LEVE, LocalTime.of(0, 15));

            Tecnico tecnico1 = new Tecnico();
            Tecnico tecnico2 = new Tecnico();

            tecnico1.setEspecialidadSistema(SistemaOperativo.WINDOWS);
            tecnico1.setEspecialidadSoporte(SoporteOperativo.SAP);

            tecnico2.setEspecialidadSistema(SistemaOperativo.LINUX_UBUNTU);
            tecnico2.setEspecialidadSoporte(SoporteOperativo.TANGO);

            tecnico1.addIncidente(incidente1);
            tecnico2.addIncidente(incidente2);

            incidente1.setProblema(problema1);
            incidente2.setProblema(problema2);

            cliente1.addIncidente(incidente1);
            cliente2.addIncidente(incidente2);

            em.persist(cliente1);
            em.persist(cliente2);
            em.persist(incidente1);
            em.persist(incidente2);
            em.persist(problema1);
            em.persist(problema2);
            em.persist(tecnico1);
            em.persist(tecnico2);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}