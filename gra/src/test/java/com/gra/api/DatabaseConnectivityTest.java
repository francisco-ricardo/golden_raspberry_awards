package com.gra.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class DatabaseConnectivityTest {

    @Inject
    EntityManager em;

    @Test
    public void testDatabaseConnectionAndQuery() {

        Integer result = (Integer) em.createNativeQuery("SELECT 1").getSingleResult();
        assertEquals(1, result);

        long count = em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class).getSingleResult();
        assertTrue(count >= 0);
    }
}