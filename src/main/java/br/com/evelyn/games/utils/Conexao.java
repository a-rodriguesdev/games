package br.com.evelyn.games.utils;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class Conexao {

    private static final EntityManagerFactory EMF = createEntityManagerFactory();

    private Conexao() {}

    private static EntityManagerFactory createEntityManagerFactory() {
        String user = firstNonBlank(System.getenv("GAMES_DB_USER"), System.getProperty("games.db.user"));
        String password = firstNonBlank(System.getenv("GAMES_DB_PASSWORD"), System.getProperty("games.db.password"));
        if (user != null && password != null) {
            Map<String, Object> overrides = new HashMap<>();
            overrides.put("jakarta.persistence.jdbc.user", user);
            overrides.put("jakarta.persistence.jdbc.password", password);
            return Persistence.createEntityManagerFactory("games", overrides);
        }
        return Persistence.createEntityManagerFactory("games");
    }

    private static String firstNonBlank(String a, String b) {
        if (a != null && !a.isBlank()) {
            return a;
        }
        if (b != null && !b.isBlank()) {
            return b;
        }
        return null;
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void closeFactory() {
        if (EMF.isOpen()) {
            EMF.close();
        }
    }
}
