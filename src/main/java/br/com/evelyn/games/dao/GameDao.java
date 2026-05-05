package br.com.evelyn.games.dao;

import java.util.List;

import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;

public class GameDao {

    private EntityManager em;

    public GameDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Game game) {
        if (game.getId() == null) {
            Long proximo = em.createQuery(
                    "SELECT COALESCE(MAX(g.id), 0L) + 1 FROM Game g",
                    Long.class).getSingleResult();
            game.setId(proximo);
        }
        em.persist(game);
    }

    public void atualizar(Game game){
        em.merge(game);
    }

    public void remover(Game game){
        Game gameExcluir = em.find(Game.class, game.getId());
        em.remove(gameExcluir);
    }

    public Game buscarGamePeloId(Game game){
        return em.find(Game.class, game.getId());
    }

    public List<Game> listarTodosOsGames() {
        String jpqlQuery = "SELECT g FROM Game g ORDER BY g.titulo ASC";
        return em.createQuery(jpqlQuery, Game.class).getResultList();
    }
}
