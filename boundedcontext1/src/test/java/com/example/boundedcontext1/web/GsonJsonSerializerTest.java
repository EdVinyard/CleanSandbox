package com.example.boundedcontext1.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.boundedcontext1.web.dto.GreetingDto;
import com.example.boundedcontext1.web.dto.GreetingsDto;

import org.junit.jupiter.api.Test;

public class GsonJsonSerializerTest {
    private static final String EXPECTED = 
            "{\n  \"theLabel\": \"x\",\n  \"aCount\": 1\n}";

    @Test
    public void serializerConfiguration() {
        // Arrange
        var serializer = new GsonJsonSerializer();

        // Act
        var actual = serializer.toJson(new WidgetDto("x", 1));

        // Assert
        assertEquals(EXPECTED, actual);
    }

    public static class WidgetDto {
        public final String theLabel;
        public final int aCount;

        public WidgetDto(String l, int c) {
            theLabel = l;
            aCount = c;
        }
    }

    @Test
    public void greetingDto() {
        // Arrange
        var serializer = new GsonJsonSerializer();
        var dto = new GreetingDto();
        dto.language = "en-US";
        dto.text = "Howdy!";
        var expected = """
                {
                  "language": "en-US",
                  "text": "Howdy!"
                }""";

        // Act
        var actual = serializer.toJson(dto);

        // Assert
        assertEquals(expected, actual);        
    }

    @Test
    public void greetingsDto() {
        // Arrange
        var serializer = new GsonJsonSerializer();

        var g = new GreetingDto();
        g.language = "en-US";
        g.text = "Howdy!";

        var gs = new GreetingsDto();
        gs.preferredLanguage = "en-US";
        gs.greetings = List.of(g);

        var expected = """
                {
                  "preferredLanguage": "en-US",
                  "greetings": [
                    {
                      "language": "en-US",
                      "text": "Howdy!"
                    }
                  ]
                }""";

        // Act
        var actual = serializer.toJson(gs);

        // Assert
        assertEquals(expected, actual);         
    }
}
