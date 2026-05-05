package br.com.evelyn.games.utils;

public class Conexao {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("games");

    public static EntityManager getEntityManager(){
        return EMF.createEntityManager();
    }

}
