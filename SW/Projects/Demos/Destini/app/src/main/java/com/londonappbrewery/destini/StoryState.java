package com.londonappbrewery.destini;

import android.net.NetworkInfo;
import android.view.View;

/**
 * Created by apandya on 8/7/2017.
 */

class StoryState {

    protected String m_story = null;

    protected String m_Ans1tButtonCaption = null;

    protected  String m_Ans2ButtonCaption = null;

    protected IStoryView m_storyView = null;

    protected StoryState m_NextStateOnAns1 = null;

    protected StoryState m_NextStateOnAns2 = null;

    public StoryState( final String story, final String ans1Caption, final String ans2Caption, IStoryView storyView )
    {
        m_story = story;
        m_Ans1tButtonCaption = ans1Caption;
        m_Ans2ButtonCaption = ans2Caption;
        m_storyView = storyView;
    }

    public void setTransitions( final StoryState stateOnAns1, final StoryState stateOnAns2 )
    {
        m_NextStateOnAns1 = stateOnAns1;
        m_NextStateOnAns2 = stateOnAns2;
    }

    public void onStateEnter()
    {
        m_storyView.setAns1ButtonText(m_Ans1tButtonCaption);
        m_storyView.setAns2ButtonText(m_Ans2ButtonCaption);

        m_storyView.setNextStoryText(m_story);
    }

    public StoryState getNextStateOnAns1()
    {
        return m_NextStateOnAns1;
    }

    public StoryState getNextStateOnAns2()
    {
        return m_NextStateOnAns2;
    }
}
