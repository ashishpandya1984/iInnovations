package com.londonappbrewery.bitcointicker;

/**
 * Created by apandya on 9/13/2017.
 */

interface IPresenter
{
    void viewCreated();

    void currencyChanged( final String newCurrency );
}
