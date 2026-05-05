package br.com.evelyn.games.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class Conexao {

    private static final String LOCAL_CREDENTIALS_RESOURCE = "/database-local.properties";

    private static final EntityManagerFactory EMF = createEntityManagerFactory();

    private Conexao() {}

    private static EntityManagerFactory createEntityManagerFactory() {
        Properties local = loadClasspathProperties(LOCAL_CREDENTIALS_RESOURCE);

        String user = firstNonBlank(
                System.getenv("GAMES_DB_USER"),
                System.getProperty("games.db.user"),
                local == null ? null : trimToNull(local.getProperty("jakarta.persistence.jdbc.user")));

        String password = firstNonBlank(
                System.getenv("GAMES_DB_PASSWORD"),
                System.getProperty("games.db.password"),
                local == null ? null : trimToNull(local.getProperty("jakarta.persistence.jdbc.password")));

        if (user == null || password == null) {
            throw new IllegalStateException(
                    "Defina credenciais Oracle: variáveis GAMES_DB_USER e GAMES_DB_PASSWORD, "
                            + "ou -Dgames.db.user / -Dgames.db.password, "
                            + "ou crie src/main/resources/database-local.properties "
                            + "(veja database-local.properties.example).");
        }

        Map<String, Object> overrides = new HashMap<>();
        overrides.put("jakarta.persistence.jdbc.user", user);
        overrides.put("jakarta.persistence.jdbc.password", password);
        return Persistence.createEntityManagerFactory("games", overrides);
    }

    private static Properties loadClasspathProperties(String path) {
        try (InputStream in = Conexao.class.getResourceAsStream(path)) {
            if (in == null) {
                return null;
            }
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler recurso " + path, e);
        }
    }

    private static String firstNonBlank(String a, String b, String c) {
        if (a != null && !a.isBlank()) {
            return a;
        }
        if (b != null && !b.isBlank()) {
            return b;
        }
        if (c != null && !c.isBlank()) {
            return c;
        }
        return null;
    }

    private static String trimToNull(String s) {
        if (s == null) {
            return null;
        }
        String t = s.trim();
        return t.isEmpty() ? null : t;
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
