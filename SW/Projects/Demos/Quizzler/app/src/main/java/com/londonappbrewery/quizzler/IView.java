package com.londonappbrewery.quizzler;

/**
 * Created by apandya on 8/4/2017.
 */

interface IView {

    public void displayCorrectAnswer(final String answer);

    public void updateProgressAndScore(final int newProgress, final String newScore);

    public void displayNextQuestion(final int questionId);

    public void displayEndOfQuiz(final int messageId);
}
