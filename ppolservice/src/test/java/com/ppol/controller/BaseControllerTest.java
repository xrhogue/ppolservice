/**
 * 
 */
package com.ppol.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ppol.TestAppConfig;
import com.ppol.common.lib.model.Contact;

/**
 * @author Richard Hogue
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class BaseControllerTest
{
    public static final String	PROPERTY_EMAIL_ADDRESS_VALUE = "em@ppol.com";
    public static final String  PROPERTY_FIRST_NAME_VALUE = "FirstName";
    public static final String  PROPERTY_LAST_NAME_VALUE = "LastName";
    
    protected Contact getTestContact()
    {
        Contact    contact = new Contact();
        
        contact.setEmailAddress(PROPERTY_EMAIL_ADDRESS_VALUE);
        contact.setFirstName(PROPERTY_FIRST_NAME_VALUE);
        contact.setLastName(PROPERTY_LAST_NAME_VALUE);
        
        return contact;
    }
}
