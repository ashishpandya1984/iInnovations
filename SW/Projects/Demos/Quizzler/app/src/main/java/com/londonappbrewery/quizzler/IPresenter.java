package com.londonappbrewery.quizzler;

import android.os.Bundle;

/**
 * Created by apandya on 8/4/2017.
 */

interface IPresenter {

    public  void viewCreated(Bundle applicationState);

    public void onAnswerSubmit(final boolean answer);

    public void onApplicationInstanceSave( Bundle applicationState );
}
