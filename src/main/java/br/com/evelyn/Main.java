package br.com.evelyn;

import java.time.LocalDate;
import java.util.List;
import br.com.evelyn.games.dao.GameDao;
import br.com.evelyn.games.utils.Conexao;
import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

         EntityManager em = Conexao.getEntityManager();

         //pesquisar(em);
        //cadastrar(em);
        //listarTodosOsGames(em);
        listarPorProdutora(em, "SNK");
        //listarPorFinalizado(em, true);   // finalizados
        //listarPorFinalizado(em, false);  // não finalizados
        //listarPorFaixaDeLancamento(em, LocalDate.of(1980, 1, 1), LocalDate.of(1990, 12, 31));

    }

    public static void listarTodosOsGames(EntityManager em){
        GameDao dao = new GameDao(em);
        List<Game> games = dao.listarTodosOsGames();

        for (Game game : games){
            System.out.println(game);
        }
    }

    public static void listarPorProdutora(EntityManager em, String produtora) {
        GameDao dao = new GameDao(em);
        List<Game> games = dao.listarPorProdutora(produtora);
        for (Game game : games) {
            System.out.println(game);
        }
    }

    public static void listarPorFinalizado(EntityManager em, boolean finalizado) {
        GameDao dao = new GameDao(em);
        List<Game> games = dao.listarPorFinalizado(finalizado);
        for (Game game : games) {
            System.out.println(game);
        }
    }

    public static void listarPorFaixaDeLancamento(EntityManager em, LocalDate inicio, LocalDate fim) {
        GameDao dao = new GameDao(em);
        List<Game> games = dao.listarPorFaixaDeLancamento(inicio, fim);
        for (Game game : games) {
            System.out.println(game);
        }
    }

    public static void pesquisar(EntityManager em) {

        GameDao dao = new GameDao(em);
        Game game1 = new Game();
        game1.setId(4L);

        Game gameEncontrado = dao.buscarGamePeloId(game1);

        if (gameEncontrado != null){
             System.out.println("Game encontrado!");
            System.out.println(gameEncontrado);
        } else {
            System.out.println("Game não encontrado!");
        }

    }

    public static void cadastrar(EntityManager em) {
        Game game1 = new Game();
        game1.setTitulo("Ikari Warriors");
        game1.setCategoria("Arcade");
        game1.setDataLancamento(LocalDate.of(1986, 1, 1));
        game1.setFinalizado(false);
        game1.setProdutora("SNK");
        game1.setValor(256.88);

        GameDao gameDao = new GameDao(em);
        em.getTransaction().begin();
        gameDao.salvar(game1);
        em.getTransaction().commit();
        em.close();
    }

}
