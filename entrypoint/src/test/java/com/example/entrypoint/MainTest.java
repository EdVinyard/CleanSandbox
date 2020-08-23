package com.example.entrypoint;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ListableBeanFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainTest {
    @Test
    public void createApplicationContext() {
        // Should not throw an exception.  This is expensive, so this is the
        // only test that should actually wire everything up.
        
        // Act
        var ctx = Main.createApplicationContext();

        // Assert
        assertNotNull(ctx.getBean(com.example.boundedcontext1.web.WebService.class));
    }

    @Test
    public void printBeanNames() throws Exception {
        // Arrange
        final var beanFactory = mock(ListableBeanFactory.class);
        when(beanFactory.getBeanDefinitionNames())
            .thenReturn(new String[] {
                "lima",
                "pinto",
                "green",
            });

        final var baos = new ByteArrayOutputStream();
        final var utf8 = StandardCharsets.UTF_8.name();
        try (var printer = new PrintStream(baos, true, utf8)) {

            // Act
            Main.printBeanNames(printer, beanFactory);
        }

        // Assert
        final var actual = baos.toString(utf8);
        assertEquals("green\nlima\npinto\n", actual);
    }
}
