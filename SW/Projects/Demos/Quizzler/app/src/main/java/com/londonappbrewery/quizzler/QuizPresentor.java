package com.londonappbrewery.quizzler;

import android.os.Bundle;

/**
 * Created by apandya on 8/4/2017.
 */

class QuizPresenter implements IPresenter {

    // Constants
    final String SCORE_FORMAT = "Score %d/%d";

    // Variables
    private IView m_quizView;

    private TrueFalse[] m_questionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    private int m_currentQuestion = 0;

    private int m_successCount = 0;

    // Methods
    public QuizPresenter(IView view)
    {
        m_quizView = view;
    }

    private int getCurrentProgress() {
        return (int) (((double) m_currentQuestion / (double) m_questionBank.length) * 100d);
    }

    @Override
    public void viewCreated(Bundle applicationState)
    {
        if( applicationState != null) {
            m_currentQuestion = applicationState.getInt("CurrentQuestionKey");
            m_successCount = applicationState.getInt("CurrentScoreKey");
        }

        m_quizView.displayNextQuestion(m_questionBank[m_currentQuestion].getQuestionResourceId());
        m_quizView.updateProgressAndScore(getCurrentProgress(), String.format(SCORE_FORMAT, m_successCount, m_questionBank.length));
    }

    @Override
    public void onAnswerSubmit(boolean answer) {

        if(m_currentQuestion == m_questionBank.length)
            return;

        final boolean isAnswerCorrect = (answer == m_questionBank[m_currentQuestion].isAanswer());

        m_successCount += isAnswerCorrect ? 1 : 0;
        m_currentQuestion++;

        m_quizView.displayCorrectAnswer(isAnswerCorrect ? "True" : "False");

        m_quizView.updateProgressAndScore(getCurrentProgress(), String.format(SCORE_FORMAT, m_successCount, m_questionBank.length));

        if(m_currentQuestion < m_questionBank.length) {
            m_quizView.displayNextQuestion(m_questionBank[m_currentQuestion].getQuestionResourceId());
        }
        else {
            m_quizView.displayEndOfQuiz(R.string.quiz_over);
        }
    }

    @Override
    public void onApplicationInstanceSave(Bundle applicationState) {
        applicationState.putInt("CurrentQuestionKey", m_currentQuestion);
        applicationState.putInt("CurrentScoreKey", m_successCount);
    }
}