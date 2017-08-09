package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements IView {

    // TODO: Declare member variables here:
    IPresenter m_presentor = null;

    Button m_trueButton = null;

    Button m_falseButton = null;

    ProgressBar m_quizProgress = null;

    TextView m_questionText = null;

    TextView m_scoreText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating the presentor to control the user interface
        m_presentor = new QuizPresenter(this);

        m_trueButton = (Button) findViewById(R.id.true_button);
        m_falseButton = (Button) findViewById(R.id.false_button);
        m_quizProgress = (ProgressBar) findViewById(R.id.progress_bar);
        m_questionText = (TextView) findViewById(R.id.question_text_view);
        m_scoreText = (TextView) findViewById(R.id.score);

        m_presentor.viewCreated( savedInstanceState );
    }

    public void onClickTrue(View view) {
        m_presentor.onAnswerSubmit(true);
    }

    public void onClickFalse(View view) {
        m_presentor.onAnswerSubmit(false);
    }

    @Override
    public void displayCorrectAnswer(String answer) {
        Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateProgressAndScore(int newProgress, String newScore)
    {
        m_quizProgress.setProgress(newProgress);
        m_scoreText.setText(newScore);
    }

    @Override
    public void displayNextQuestion(final int questionId) {
        m_questionText.setText(questionId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(m_presentor != null)
            m_presentor.onApplicationInstanceSave(outState);
    }

    public void displayEndOfQuiz(final int endMessageId)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder( this );
        alertDialog.setMessage(endMessageId);

        alertDialog.setPositiveButton("Close application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.show();
    }
}
