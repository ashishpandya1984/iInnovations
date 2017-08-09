package com.londonappbrewery.quizzler;

/**
 * Created by apandya on 8/2/2017.
 */

public class TrueFalse {

    private int m_questionResourceId;

    private boolean m_answer;

    public TrueFalse(final int questionReousrceId, final boolean answer)
    {
        m_questionResourceId = questionReousrceId;
        m_answer = answer;
    }

    public int getQuestionResourceId() {
        return m_questionResourceId;
    }

    public void setQuestionResourceId(int m_questionResourceId) {
        this.m_questionResourceId = m_questionResourceId;
    }

    public boolean isAanswer() {
        return m_answer;
    }

    public void setAnswer(boolean m_answer) {
        this.m_answer = m_answer;
    }
}
