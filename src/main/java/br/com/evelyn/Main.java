package br.com.evelyn;

import java.time.LocalDate;
import java.util.List;
import br.com.evelyn.games.dao.CategoriaDao;
import br.com.evelyn.games.dao.GameDao;
import br.com.evelyn.games.utils.Conexao;
import br.com.evelyn.model.Categoria;
import br.com.evelyn.model.Game;
import jakarta.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

         EntityManager em = Conexao.getEntityManager();

         //pesquisar(em);
        //cadastrar(em);
        //listarTodosOsGames(em);
        listarCategoriaPorId(em);
        //listarPorProdutora(em, "SNK");
        //listarPorFinalizado(em, true);   // finalizados
        //listarPorFinalizado(em, false);  // não finalizados
        //listarPorFaixaDeLancamento(em, LocalDate.of(1980, 1, 1), LocalDate.of(1990, 12, 31));

    }

    public static void listarCategoriaPorId(EntityManager em){
        CategoriaDao categoriaDao = new CategoriaDao(em);
        Categoria categoria = new Categoria();
        categoria.setId(2L);
        Categoria categoriaEncontrada = categoriaDao.buscarCategoriaPorId(categoria);
        System.out.println(categoriaEncontrada.toString());
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
        Categoria categoria = new Categoria();
        categoria.setId(2L);

        //CategoriaDao categoriaDao = new CategoriaDao(em);
        em.getTransaction().begin();
        //categoriaDao.salvar(categoria);

        Game game1 = new Game();
        game1.setTitulo("Street of Rage");
        game1.setCategoria(categoria);
        game1.setDataLancamento(LocalDate.of(1991, 7, 1));
        game1.setFinalizado(true);
        game1.setProdutora("SEGA");
        game1.setValor(99.99);

        GameDao gameDao = new GameDao(em);
        gameDao.salvar(game1);
        em.getTransaction().commit();
        em.close();
    }

}
