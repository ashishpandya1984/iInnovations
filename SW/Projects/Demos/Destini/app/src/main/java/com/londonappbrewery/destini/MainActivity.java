package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IStoryView {

    Button m_btnAns1 = null;

    Button m_btnAns2 = null;

    TextView m_storyText = null;

    IPresenter m_storyPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_btnAns1 = (Button) findViewById(R.id.btn_ans1);
        m_btnAns2 = (Button) findViewById(R.id.btn_ans2);
        m_storyText = (TextView) findViewById(R.id.storyTextView);

        m_storyPresenter = new StoryPresenter( getApplicationContext() , this);
        m_storyPresenter.onViewCreated();
    }

    @Override
    public void setAns1ButtonText(String text) {
        if( text.isEmpty() )
            m_btnAns1.setVisibility(View.INVISIBLE );
        else
            m_btnAns1.setText(text);
    }

    @Override
    public void setAns2ButtonText(String text) {
        if( text.isEmpty() )
            m_btnAns2.setVisibility(View.INVISIBLE );
        else
            m_btnAns2.setText(text);
    }

    @Override
    public void setNextStoryText(String text) {
        m_storyText.setText(text);
    }

    public void ans1Click(View view) {
        m_storyPresenter.onAns1Click();
    }

    public void ans2Click(View view) {
        m_storyPresenter.onAns2Click();
    }
}
