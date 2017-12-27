package com.iInnovations.groupchat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 27-12-2017.
 */
public class UserNameValidatorTest {
    @Test
    public void isValidUserNameTest() throws Exception
    {
        StringBuilder errMsg = new StringBuilder();

        // User name must not be empty
        assertFalse( UserNameValidator.isValidUserName("", errMsg));
        assertTrue( errMsg.toString().equals( "This is a mandatory field" ));

        errMsg = new StringBuilder();

        // User name must be the valid email address
        assertFalse( UserNameValidator.isValidUserName("user", errMsg ));
        assertTrue( errMsg.toString().equals( "Please provide a valid email address" ));

        errMsg = new StringBuilder();

        // Valid user name example
        assertTrue( UserNameValidator.isValidUserName( "user@domain.com" , errMsg ));
        assertTrue( errMsg.toString().isEmpty() );
    }

}