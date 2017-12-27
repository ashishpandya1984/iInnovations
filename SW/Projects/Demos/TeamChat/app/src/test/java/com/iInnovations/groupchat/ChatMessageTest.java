package com.iInnovations.groupchat;

import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

/**
 * Created by User on 26-12-2017.
 */
public class ChatMessageTest
{
    @Test
    public void getAuthor() throws Exception
    {
        ChatMessage msg = new ChatMessage();
        assertNull(msg.getAuthor());

        ChatMessage msg2 = new ChatMessage("DummyAuthor", "Dummy message");
        assertEquals(msg.getAuthor(), "DummyAuthor", "DummyAuthor");
    }

    @Test
    public void getMessage() throws Exception
    {
        ChatMessage msg = new ChatMessage();
        assertNull(msg.getMessage());

        ChatMessage msg2 = new ChatMessage("DummyAuthor", "Dummy message");
        assertEquals(msg.getMessage(), "Dummy message", "Dummy message");
    }
}