/**
 * Contact.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.ppol.common.lib.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="contact")
public class Contact implements Serializable
{
	private static final long serialVersionUID = -3308122051355560773L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Column(name="email_address")
    private String  emailAddress;
    
    @Column(name="first_name")
    private String  firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    /**
     * Default constructor
     */
    public Contact()
    {
    }

    /**
     * Default constructor
     * @param contact the contact to update from
     */
    public Contact(Contact contact)
    {
        update(contact);
    }
    
    /**
     * Updates the contact with new info
     * @param contact the update info
     * @return itself
     */
    public Contact update(Contact contact)
    {
        emailAddress = contact.getEmailAddress();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        
        return this;
    }

	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

}
