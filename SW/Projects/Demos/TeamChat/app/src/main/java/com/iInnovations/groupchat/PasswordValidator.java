package com.iInnovations.groupchat;

/**
 * Created by User on 26-12-2017.
 */

class PasswordValidator
{
    public static boolean isValidPassword(final String password, final String confirmPassword, StringBuilder errMsg)
    {
        if(password.trim().isEmpty())
        {
            errMsg.append( "This is a mandatory field" );
            return false;
        }

        if(!(password.trim().length() > 4))
        {
            errMsg.append( "Password should be at least 4 letters" );
            return false;
        }

        if( !password.trim().equals( confirmPassword.trim() ) )
        {
            errMsg.append( "Password does not match with the confirm password" );
            return false;
        }

        return true;
    }
}
