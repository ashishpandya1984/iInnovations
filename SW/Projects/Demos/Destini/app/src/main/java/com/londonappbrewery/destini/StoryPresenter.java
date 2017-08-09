package com.londonappbrewery.destini;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by apandya on 8/7/2017.
 */

class StoryPresenter implements IPresenter {

    IStoryView m_view = null;

    Context m_applicationContext = null;

    StoryState m_currentState = null;

    StoryState m_stateT1 = null;

    StoryState m_stateT2 = null;

    StoryState m_stateT3 = null;

    StoryState m_stateT4 = null;

    StoryState m_stateT5 = null;

    StoryState m_stateT6 = null;

    public StoryPresenter( Context applicationContext , IStoryView view )
    {
        m_view = view;
        m_applicationContext = applicationContext;

        // Instantiation of all the possible states
        m_stateT1 = new StoryState(m_applicationContext.getString(R.string.T1_Story),
                m_applicationContext.getString(R.string.T1_Ans1),
                m_applicationContext.getString(R.string.T1_Ans2), m_view);

        m_stateT2 = new StoryState(m_applicationContext.getString(R.string.T2_Story),
                m_applicationContext.getString(R.string.T2_Ans1),
                m_applicationContext.getString(R.string.T2_Ans2), m_view);

        m_stateT3 = new StoryState(m_applicationContext.getString(R.string.T3_Story),
                m_applicationContext.getString(R.string.T3_Ans1),
                m_applicationContext.getString(R.string.T3_Ans2), m_view);

        m_stateT4 = new StoryState(m_applicationContext.getString(R.string.T4_End), "", "", m_view);
        m_stateT5 = new StoryState(m_applicationContext.getString(R.string.T5_End), "", "", m_view);
        m_stateT6 = new StoryState(m_applicationContext.getString(R.string.T6_End), "", "", m_view);
    }

    @Override
    public void onViewCreated( )
    {
        //Transitions between the different story states
        m_stateT1.setTransitions(m_stateT3, m_stateT2); //T1 -> T3 or T1 -> T2
        m_stateT2.setTransitions(m_stateT3, m_stateT4); //T2 -> T3 or T2 -> T4 End
        m_stateT3.setTransitions(m_stateT6, m_stateT5); //T3 -> T6 End or T3 -> T5 End
        m_stateT4.setTransitions(null, null); // End of story
        m_stateT5.setTransitions(null, null); // End of story
        m_stateT6.setTransitions(null, null); // End of story

        m_currentState = m_stateT1;
        m_stateT1.onStateEnter();
    }

    @Override
    public void onAns1Click() {
        m_currentState  = m_currentState.getNextStateOnAns1();

        if( m_currentState != null)
            m_currentState.onStateEnter();
    }

    @Override
    public void onAns2Click() {
        m_currentState  = m_currentState.getNextStateOnAns2();

        if( m_currentState != null)
            m_currentState.onStateEnter();
    }
}
