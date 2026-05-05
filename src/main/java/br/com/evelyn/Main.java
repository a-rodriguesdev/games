package br.com.evelyn.games.model;

import br.com.evelyn.games.model.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Game game1 = new Game();
        game1.setTitulo("Megaman 1");
        game1.setCategoria("Plataforma");
        game1.setDantaLancamento(LocalDate.of(1987, 12, 1));
        game1.setProdutora("Capcom");
        game1.setValor(128.00);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("games");
        EntityManager em = emf.createEntityManager();

        em.persist(game1);
    }
}
