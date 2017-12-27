package com.iInnovations.groupchat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 27-12-2017.
 */
public class PasswordValidatorTest {
    @Test
    public void isValidPasswordTest() throws Exception
    {
        StringBuilder errMsg = new StringBuilder();

        // Empty password must not be allowed
        assertTrue( PasswordValidator.isValidPassword("", "", errMsg ) == false);
        assertTrue( errMsg.toString().equals( "This is a mandatory field" ) );

        errMsg = new StringBuilder();

        // Password must be at least more than 4 letters in length
        assertTrue( PasswordValidator.isValidPassword("pass", "", errMsg) == false );
        assertTrue( errMsg.toString().equals( "Password should be at least 4 letters" ) );

        errMsg = new StringBuilder();

        // Password and confirm password must match
        assertTrue( PasswordValidator.isValidPassword( "Password", "", errMsg) == false );
        assertTrue( errMsg.toString().equals( "Password does not match with the confirm password" ) );

        errMsg = new StringBuilder();

        // Valid password example
        assertTrue( PasswordValidator.isValidPassword("Password", "Password", errMsg) == true);
        assertTrue( errMsg.toString().isEmpty() );
    }
}