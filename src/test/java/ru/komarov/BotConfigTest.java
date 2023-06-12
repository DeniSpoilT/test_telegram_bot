package ru.komarov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BotConfigTest {
    BotConfig botConfig;

    @BeforeEach
    public void init() {
        botConfig = new BotConfig("test.properties");
    }

    @Test
    void testGet() {
        assertEquals("test_name", botConfig.get("test.name"));
        assertEquals("test_username", botConfig.get("test.username"));
        assertEquals("test_token", botConfig.get("test.token"));
    }

    @Test
    void testGetInvalidKey() {
        BotConfig botConfig = new BotConfig("test.properties");
        Assertions.assertNull(botConfig.get("invalid.key"));
    }

    @Test
    void testInvalidPath() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new BotConfig("invalidPath"));
        assertEquals(botConfig.INVALID_PATH_MESSAGE, exception.getMessage());
    }
}
