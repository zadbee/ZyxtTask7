/** 
 * Author: 			Bo ZHANG
 * Last Edit:		01/20/2014
 * Class Name:		Cus_LoginForm
 * Annotation:		NULL
 */

package formbeans;

import java.util.ArrayList;
import org.mybeans.form.FormBean;

public class Cus_RegistrationForm extends FormBean {
	private String firstname;
	private String lastname;
	private String addrline1;
	private String addrline2;
	private String city;
	private String state;
	private int zip;
	private String username;
	private String password;
	private long cash;
	
	public String getFirstName()    { return firstname;}
	public String getLastName()     { return lastname; }
	public String getAddrline1()    { return addrline1; }
	public String getAddrline2()	{ return addrline2; }
	public String getCity()			{ return city; 	   }
	public String getState()		{ return state;	   }
	public int getZip()				{ return zip;	   }
	public String getUsername() 	{ return username; }
	public String getPassword() 	{ return password; }
	public long getCash()			{ return cash;	   }
	
	public void setUsername(String s)	{ username = trimAndConvert(s,"<>\";*"); }
	public void setPassword(String s) 	{ password = trimAndConvert(s,"<>\";*"); }
	public void setFirstname(String s)	{ firstname = trimAndConvert(s, "<>\";*");}
	public void setLastname(String s)	{ lastname = trimAndConvert(s, "<>\";*");}
	public void setAddrline1(String s)	{ addrline1 = trimAndConvert(s, "<>\";*");}
	public void setAddrline2(String s)	{ addrline2 = trimAndConvert(s, "<>\";*");}
	public void setCity(String s)	    { city = trimAndConvert(s, "<>\";*");}
	public void setState(String s)		{ state = trimAndConvert(s, "<>\";*");}
	public void setZip(int s)			{ zip = s;}
	public void setCash(long s)			{ cash = s;}
	
	
	
	// Check whether the form is valid. Returns an arraylist of error strings if the form is not valid. 
	public ArrayList<String> getValidationErrors() {
        ArrayList<String> errors = new ArrayList<String>();
        if (firstname == null || firstname.length() == 0) errors.add("First Name is required");
        if (lastname == null || lastname.length() == 0) errors.add("Last Name is required");
        if (addrline1 == null || addrline1.length() == 0) errors.add("Address Line1 is required");
        if (addrline2 == null || addrline2.length() == 0) errors.add("Address Line2 is required");
        if (city == null || city.length() == 0) errors.add("City is required");
        if (state == null || state.length() == 0) errors.add("State is required");
        if (zip < 0) errors.add("Not a valid zip");
        if (cash < 0.0) errors.add("Cash has to be a positive number");
        if (username == null || username.length() == 0) errors.add("Username is required");
        if (password == null || password.length() == 0) errors.add("Password is required");

        if (errors.size() > 0) return errors;

        if (username.matches(".*[<>\"].*")) errors.add("E-mail address may not contain angle brackets or quotes");
        if (firstname.matches(".*[<>\"].*")) errors.add("First Name may not contain angle brackets or quotes");
        if (lastname.matches(".*[<>\"].*")) errors.add("Last Name may not contain angle brackets or quotes");
        if (city.matches(".*[<>\"].*")) errors.add("City may not contain angle brackets or quotes");
        if (state.matches(".*[<>\"].*")) errors.add("State may not contain angle brackets or quotes");
        return errors;
    }
	
	public void print() {
		System.out.println("email: " + username);
		System.out.println("pswd: " + password);
	}
}
