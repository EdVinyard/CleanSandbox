package com.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConversationTest {
    @Test
    public void testApp() {
        final var conversation = new Conversation();
        
        assertFalse(conversation.hasParticipants());
    }
}
