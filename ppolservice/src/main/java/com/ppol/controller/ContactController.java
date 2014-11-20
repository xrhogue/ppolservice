package com.ppol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ppol.common.bus.CommonService;
import com.ppol.common.lib.model.Contact;

@RestController
public class ContactController
{
    @Autowired
    private CommonService   commonService;

    public ContactController()
    {
    }
    
    @RequestMapping("/contact")
    @ResponseBody 
    public Contact getContact(@RequestParam(value="id", required=true) Long id)
    {
        return commonService.getContact(id);
    }
    
    @RequestMapping("/contact/update")
    @ResponseBody
    public Contact updateContact(@RequestBody(required=true) Contact contact)
    {
    	System.out.println("Got contact add/update request");
    	
    	if (contact.getId() == null)
    	{
    		return commonService.saveContact(contact);
    	}
    	
    	return commonService.updateContact(contact);
    }
    
    @RequestMapping("/contact/delete")
    public void deleteContact(@RequestParam(value="id", required=true) Long id)
    {
        commonService.deleteContact(id);
    }
    
    @RequestMapping(value="/contact/list", method=RequestMethod.GET)
    @ResponseBody 
    public List<Contact> getContacts()
    {
        return commonService.findContacts();
    }
}
