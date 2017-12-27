package com.iInnovations.groupchat;

/**
 * Created by User on 26-12-2017.
 */

class UserNameValidator
{
    public static boolean isValidUserName(final String userName, StringBuilder errMessage)
    {
        if(userName.trim().isEmpty())
        {
            errMessage.append( "This is a mandatory field" );
            return false;
        }

        if(!userName.trim().contains("@"))
        {
            errMessage.append( "Please provide a valid email address" );
            return false;
        }

        return true;
    }
}
