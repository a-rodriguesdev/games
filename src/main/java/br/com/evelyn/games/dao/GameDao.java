package br.com.evelyn.games.dao;

import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;

public class GameDao {

    private EntityManager em;

    public GameDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Game game) {
        em.persist(game);
    }

    public void atualizar(Game game){
        em.merge(game);
    }

}
