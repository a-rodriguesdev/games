package br.com.evelyn.games.dao;

public class GameDao {

    private EntityManager em;

    public GameDao(EntityManager em){
        this.em = em;
    }

    public void salvar(Game game){
        em.persist(game);
    }
}
