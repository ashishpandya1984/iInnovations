package com.londonappbrewery.destini;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by apandya on 8/7/2017.
 */

interface IStoryView {

    public void setAns1ButtonText(final String text);

    public void setAns2ButtonText( final String text);

    public void setNextStoryText( final String text );
}
