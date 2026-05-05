package br.com.evelyn;

import java.time.LocalDate;
import br.com.evelyn.games.dao.GameDao;
import br.com.evelyn.games.utils.Conexao;
import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

        Game game1 = new Game();
        game1.setId(22L);
        game1.setTitulo("Battletoads");
        game1.setCategoria("Luta");
        game1.setDataLancamento(LocalDate.of(1991, 6, 1));
        game1.setFinalizado(true);
        game1.setProdutora("Tradewest, Rare");
        game1.setValor(99.89);

        try (EntityManager em = Conexao.getEntityManager()) {
            GameDao gameDao = new GameDao(em);
            em.getTransaction().begin();
            //gameDao.salvar(game1);
            gameDao.atualizar(game1);
            em.getTransaction().commit();
        } finally {
            Conexao.closeFactory();
        }
    }
}
