package br.com.evelyn.games.dao;

import java.util.List;

import br.com.evelyn.model.Produtora;
import jakarta.persistence.EntityManager;

public class ProdutoraDao {
    private final EntityManager em;

    public ProdutoraDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Produtora produtora) {
        if (produtora.getId() == null) {
            Long proximo = em.createQuery(
                    "SELECT COALESCE(MAX(p.id), 0L) + 1 FROM Produtora p",
                    Long.class).getSingleResult();
            produtora.setId(proximo);
        }
        em.persist(produtora);
    }

    public Produtora buscarPorId(Long id) {
        return em.find(Produtora.class, id);
    }

    public List<Produtora> listarTodas() {
        String jpql = "SELECT p FROM Produtora p ORDER BY p.nomeProdutora ASC";
        return em.createQuery(jpql, Produtora.class).getResultList();
    }
}
