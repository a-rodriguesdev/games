package br.com.evelyn.games.dao;

import java.time.LocalDate;
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

    public List<Game> listarPorProdutora(String produtora) {
        String jpql = "SELECT g FROM Game g WHERE UPPER(g.produtora.nomeProdutora) = UPPER(:produtora) ORDER BY g.titulo ASC";
        return em.createQuery(jpql, Game.class)
                .setParameter("produtora", produtora)
                .getResultList();
    }

    public List<Game> listarPorIdProdutora(Long idProdutora) {
        String jpql = "SELECT g FROM Game g WHERE g.produtora.id = :idProdutora ORDER BY g.titulo ASC";
        return em.createQuery(jpql, Game.class)
                .setParameter("idProdutora", idProdutora)
                .getResultList();
    }

    public List<Game> listarPorFinalizado(boolean finalizado) {
        String jpql = "SELECT g FROM Game g WHERE g.finalizado = :finalizado ORDER BY g.titulo ASC";
        return em.createQuery(jpql, Game.class)
                .setParameter("finalizado", finalizado)
                .getResultList();
    }

    public List<Game> listarPorFaixaDeLancamento(LocalDate dataInicio, LocalDate dataFim) {
        String jpql = "SELECT g FROM Game g WHERE g.dataLancamento BETWEEN :inicio AND :fim ORDER BY g.dataLancamento ASC";
        return em.createQuery(jpql, Game.class)
                .setParameter("inicio", dataInicio)
                .setParameter("fim", dataFim)
                .getResultList();
    }
}
