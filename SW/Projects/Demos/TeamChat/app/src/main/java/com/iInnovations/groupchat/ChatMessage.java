package com.iInnovations.groupchat;

/**
 * Created by apandya on 12/20/2017.
 */

class ChatMessage {

    private String m_author;

    private String m_message;

    public ChatMessage(String m_author, String m_message) {
        this.m_author = m_author;
        this.m_message = m_message;
    }

    public ChatMessage()
    {
    }

    public String getAuthor() {
        return m_author;
    }

    public String getMessage() {
        return m_message;
    }
}
