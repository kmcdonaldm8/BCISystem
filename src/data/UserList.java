package data;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PFont;

public class UserList extends PApplet{
	
	User[] users = {};
	
	
	public UserList(){
		users = new User[1];		
	}

	
	
	public User[] addUser(User u) {
		
		User[] newArray = new User[users.length+1];
		
		for (int i = 0;i<users.length;i++) {
			newArray[i] = users[i];
		}
		
		newArray[users.length]= u;
		
		users = newArray;
		
		return users;
		
	}
	
	public User[] deleteUser(User u) {
		
		String Fn = u.FName;
		String em = u.email;
		String pa = u.pass;
		
		for (int i=0;i < users.length;i++) {
			if(users[i] != null && users[i].email.equals(em)) {
				users[i] = null;
				break;
			}
			if (i == users.length-1) {
				 System.out.println("That requested person is not employed at this firm.");
			}
		}
		return users;
		
	}
}
