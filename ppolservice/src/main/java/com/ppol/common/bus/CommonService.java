/**
 * CommonService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.ppol.common.bus;

import java.util.List;

import com.ppol.common.lib.model.Contact;

/**
 * CommonService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface CommonService
{
    Contact getContact(Long id);
    Contact saveContact(Contact contact);
    Contact updateContact(Contact contact);
    void deleteContact(Long id);
    void deleteContacts(Contact contact);
    List<Contact> findContacts();
}
