package br.com.evelyn;

import java.time.LocalDate;

import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game();
        game1.setTitulo("Mega Man 1");
        game1.setCategoria("Plataforma");
        game1.setDataLancamento(LocalDate.of(1987, 12, 1));
        game1.setFinalizado(true);
        game1.setProdutora("Capcom");
        game1.setValor(128.0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("games");
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(game1);
            em.getTransaction().commit();
        } finally {
            emf.close();
        }
    }
}
