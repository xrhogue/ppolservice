package com.ppol.common.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ppol.AppConfig;
import com.ppol.common.dao.GenericDao;
import com.ppol.common.lib.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class GenericDaoImplTest
{
    @Autowired
    GenericDao<Long, Contact>   contactDao;

    @Test
    public void testGet()
    {
        Contact    contact = contactDao.get(Contact.class, 1L);
        
        assertNotNull(contact);
    }

    @Test
    public void testSaveOrUpdate()
    {
        Contact    contact = new Contact();
        
        contact.setEmailAddress("test@ppol.com");
        contact.setFirstName("FirstName");
        contact.setLastName("LastName");
        
        contactDao.save(contact);
    }

    @Test
    public void testDelete()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindString()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindStringObject()
    {
        fail("Not yet implemented");
    }

}
