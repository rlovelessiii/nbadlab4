/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

/**
 *
 * @author bobbysmedley
 */
public class UserInfo implements java.io.Serializable {
    
    private String name, username, password, address, country, zip, email, sex, languages, about;
    
    //constuctor
    public UserInfo()
    {
        
    }
    
    //name
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    
    //username
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
    
    //password
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return password;
    }
    
    //address
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return address;
    }
    
    //country
    public void setCountry(String country)
    {
        this.country = country;
    }
    public String getCountry()
    {
        return country;
    }
    
    //zip
    public void setZip(String zip)
    {
        this.zip = zip;
    }
    public String getZip()
    {
        return zip;
    }
    
    //email
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }
    
    //sex
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getSex()
    {
        return sex;
    }
    
    //language
    public void setLanguages(String languages)
    {
        this.languages = languages;
    }
    public String getLanguages()
    {
        return languages;
    }
    
    //about
    public void setAbout(String about)
    {
        this.about = about;
    }
    public String getAbout()
    {
        return about;
    }
    
    
          
    
}
