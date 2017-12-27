package com.iInnovations.groupchat;

/**
 * Created by apandya on 12/20/2017.
 */

class ChatMessage {

    protected String author = null;

    protected String message = null;

    public ChatMessage()
    {
    }

    public ChatMessage(String m_author, String m_message)
    {
        this.author = m_author;
        this.message = m_message;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
