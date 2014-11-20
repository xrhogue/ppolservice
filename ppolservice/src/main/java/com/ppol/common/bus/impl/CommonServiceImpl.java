/**
 * CommonServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.ppol.common.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ppol.common.bus.CommonService;
import com.ppol.common.dao.GenericDao;
import com.ppol.common.lib.model.Contact;

/**
 * CommonServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class CommonServiceImpl implements CommonService
{
    @Autowired
    private GenericDao<Long, Contact>  contactDao;
    
    /**
     * Default constructor
     */
    public CommonServiceImpl()
    {
    }

    /**
     * @see com.ppol.common.bus.CommonService#getContact(java.lang.Long)
     */
    public Contact getContact(Long id)
    {
        return contactDao.get(Contact.class, id);
    }

    /**
     * @see com.ppol.common.bus.CommonService#deleteContact(java.lang.Long)
     */
    public void deleteContact(Long id)
    {
        contactDao.delete(getContact(id));
    }

    /**
     * @see com.ppol.common.bus.CommonService#deleteContact(com.ppol.common.lib.Contact.Contact)
     */
    public void deleteContacts(Contact contact)
    {
        contactDao.delete(contact);
    }

    /**
     * @see com.ppol.common.bus.CommonService#findContacts()
     */
    public List<Contact> findContacts()
    {
        return contactDao.find("from Contact");
    }

    /**
     * @see com.ppol.common.bus.CommonService#saveContact(com.ppol.common.lib.Contact.Contact)
     */
    public Contact saveContact(Contact contact)
    {
        contactDao.save(contact);
        
        return contact;
    }

    /**
     * @see com.ppol.common.bus.CommonService#updateContact(com.ppol.common.lib.Contact.Contact)
     */
    public Contact updateContact(Contact contact)
    {
        contactDao.update(contact, contact.getId());
        
        return contact;
    }
}
