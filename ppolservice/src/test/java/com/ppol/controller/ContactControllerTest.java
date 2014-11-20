/**
 * 
 */
package com.ppol.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ppol.common.lib.model.Contact;
import com.ppol.controller.ContactController;

/**
 * @author rhogue
 *
 */
public class ContactControllerTest extends BaseControllerTest
{
    @Autowired
    private ContactController  contactController;
    
    /**
     * Test method for {@link com.ppol.controller.ContactController#ContactController()}.
     */
    @Test
    public void testContactController()
    {
        assertNotNull(contactController);
    }

    /**
     * Test method for {@link com.ppol.controller.ContactController#getContact(java.lang.Long)}.
     */
    @Test
    public void testGetContact()
    {
        Contact    Contact = contactController.updateContact(getTestContact());
        
        assertNotNull(Contact);
        assertNotNull(Contact.getId());
        
        Contact    savedContact = contactController.getContact(Contact.getId());
        
        assertNotNull(savedContact);
        assertEquals(Contact.getId(), savedContact.getId());
    }

    /**
     * Test method for {@link com.ppol.controller.ContactController#updateContact(com.ppol.common.lib.model.Contact)}.
     */
    @Test
    public void testUpdateContact()
    {
        Contact    newContact = getTestContact();
        Contact    savedContact = contactController.updateContact(newContact);
        
        assertNotNull(savedContact);
        assertNotNull(savedContact.getId());
        
        savedContact.setFirstName(savedContact.getFirstName() + "new");
        
        Contact    updatedContact = contactController.updateContact(savedContact);
        
        assertNotNull(updatedContact);
        assertEquals(savedContact.getFirstName(), updatedContact.getFirstName());
    }

    /**
     * Test method for {@link com.ppol.controller.ContactController#deleteContact(java.lang.Long)}.
     */
    @Test
    public void testDeleteContact()
    {
        Contact    newContact = getTestContact();
        Contact    savedContact = contactController.updateContact(newContact);
        
        assertNotNull(savedContact);
        assertNotNull(savedContact.getId());
        
        contactController.deleteContact(savedContact.getId());
        
        assertNull(contactController.getContact(savedContact.getId()));
    }

    /**
     * Test method for {@link com.ppol.controller.ContactController#getContacts()}.
     */
    @Test
    public void testGetContacts()
    {
        List<Contact>  Contacts = contactController.getContacts();
        
        assertNotNull(Contacts);
        assertTrue(Contacts.size() > 0);
    }
}
