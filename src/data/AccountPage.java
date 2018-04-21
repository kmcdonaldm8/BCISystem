package data;

import processing.core.PApplet;
import processing.core.PFont;


public class AccountPage extends PApplet {
	String first;
	String mail;
	String password;
	String hidden = "";
	UserList userList;
	PFont consolas;
	User user;
	
	//change info details
	boolean changeName = false;
	boolean changeEmail = false;
	boolean changePass = false;
	boolean success = false;
	boolean successDelete = false;
	boolean normal = true;
	String currentValue;

	AccountPage(User u, UserList ul){
		
		user = u;
		first = u.FName;
		mail = u.email;
		userList = ul;
		password = u.pass;
 
		  for ( int t=0; t < password.length(); t++) {
		      hidden+="*";
		    }

	}
	
	public void setup(){
		
		smooth();
		size (1200, 700); 
		consolas = createFont("Ariel", 100 );
		textFont(consolas, 100);
		currentValue = "";
		
	}
	
	
	public void draw() {
		
		//background colour white
		background (255, 255, 255);
		
		//page heading
		
		textSize(30);
		
		if (success) {
			textSize(20);
			text ("Saved your new details, click enter to continue !",410, 100);
			
			
		} else if (successDelete) {
			textSize(20);
			text ("Click enter to go back to the sessions page! Your account is gone!", 310, 100);
			
		} else if (changeName) {
			
			text ("Type your first name and press enter", 350, 100); 
			text( currentValue, 500, 200);
			
			
		} else if (changePass) {
			
			text ("Type your new password and press enter", 350, 100); 
			text( currentValue, 500, 200);
			
		} else if (changeEmail) {
			
			text ("Type your new username address and press enter", 350, 100); 
			text( currentValue, 500, 200);
			
		}else if (normal) {
			String welcomeText = "Welcome to your account page, " + first +  " !" ; 
			fill (23, 85, 167);
			textSize(30);
			text(welcomeText,102, 71);
			// text box fields
			fill (250);
			//First name box
			rect (350, 150, 600, 50);
			//Email box
			rect (350, 250, 600, 50);
			//Password box
			rect (350, 350, 600, 50);
			
			//Text fields
			fill (23, 85, 167);
			text("First Name: ", 102,190);
			text("Username: ", 102,290);
			text("Password: ", 102,390);
			
			//User details 
			fill (0);
			text(first,370,190);
			text(mail,370,290);
			text(hidden,370,390);
			
			//create Back to game button
			createBackToGameButton();
			//delete button
			createDeleteButton();
			//user graph button
			createUserGraph();
		}
		

		
	}
	public void keyTyped() {

		  if ( (key >= 'a' && key <= 'z') || (key >= 'A' && key <= 'Z') || (key == ' ' ) ) {

				  currentValue += key;
	
		    return;
		  }
		  if ( key == DELETE || key == BACKSPACE ) {
			  if ( currentValue.length() > 0 ) {
			    	currentValue = currentValue.substring(0, currentValue.length()-1);
			    }
		    return;
		  } 
		  if ( key == ENTER || key == RETURN ) { 
			  if (changeName) {
				  user.FName = currentValue;
				  first = currentValue;
			    	  currentValue = "";
			      changeName = false;
			      success = true;

			  } else if (changePass) { 
				  user.pass = currentValue;
				  password = currentValue;
				  currentValue = "";
				  for ( int t=0; t < password.length(); t++) {
				      hidden+="*";
				    }
			      currentValue = "";
			      changePass = false;
			      success = true;
			      
			  }else if (changeEmail) {
				  user.email = currentValue;
				  mail = currentValue;
			      currentValue = "";
			      changeEmail = false;
			      success = true;
			  } 
			  else if (success) {
				  normal = true;
				  success = false;
			  }
			  else if (successDelete) {
				  
				  //re-open first window
				  ThinkGearLoad tgl = new ThinkGearLoad();
				 	noLoop();
				   runSketch( new String[] { "--display=0",
			                "--location=0,0",
			                "--sketch-path=" + sketchPath,
			                "" },
				    		tgl );
				 
			  }
		  }
		}
	
	public void mousePressed() {
		//back to game button
		  float x=900;    // top left corner x position
		  float y=50;    // top left corner y position
		  float w=200;    // width of button
		  float h=40; //height of the button
		 // delete button 
		  float xx=900;    // top left corner x position
		  float yy=600-50;    // top left corner y position
		  float ww=200;    // width of button
		  float hh=40; //height of the button
		  
		 if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h )) {
	    	  ThinkGearLoad tgl = new ThinkGearLoad();
	    	  //return to screen, with same user, and session number updated
			tgl.u = user;
			tgl.userList = userList;
			System.out.println("session:" + tgl.u.sessionsDone);
			 	noLoop();
			   runSketch( new String[] { "--display=0",
		                "--location=0,0",
		                "--sketch-path=" + sketchPath,
		                "" },
			    		tgl );

	    } else  if (mouseX > 350 && mouseX < (350 + 600) && mouseY > 150 && mouseY < (150 + 50 )) {
	    		 normal = false;
	    		 changeName = true;
	    	
		 
	
	    }  else  if (mouseX > 350 && mouseX < (350 + 600) && mouseY > 250 && mouseY < (250 + 50 )) {
	    		normal = false;
	    		changeEmail = true;
	   
	    	
	    } else  if (mouseX > 350 && mouseX < (350 + 600) && mouseY > 150 && mouseY < (350 + 50 )) {
    			normal = false;
    			changePass = true;
	    } else if  (mouseX > 900 && mouseX < (900 + 200) && mouseY > 550 && mouseY < (550 + 40 )) {
			normal = false;
	    		DeleteUser();
	    } else if (mouseX > 102 && mouseX<(102 + 200)&& mouseY>490 && mouseY < (490 + 40)) {
	  	  	userGraph u = new userGraph(user);

		
		 	noLoop();
		   runSketch( new String[] { "--display=0",
	                "--location=0,0",
	                "--sketch-path=" + sketchPath,
	                "" },
		    		u );
	    	
	    	
	    }

		 
		 
	}
	
	public void createBackToGameButton() {
		 cursor(HAND);
		String label = "Back to game screen";
		  float x=900;    // top left corner x position
		  float y=50;    // top left corner y position
		  float w=200;    // width of button
		  float h=40; //height of the button
		  textSize(14);
		  fill(250);
		  stroke(209, 24, 117, 100);
		
		    rect(x, y, w, h, 10);
		    fill(0);
		    text(label, x + 30, y + 25);
		   
	}
	public void createUserGraph() {
		String label = "Review Sessions";
		  float xx=102;    // top left corner x position
		  float yy=490;    // top left corner y position
		  float ww=200;    // width of button
		  float hh=40; //height of the button
	
		  textSize(14);
		  fill(250);
		  stroke(209, 24, 117, 100);
		
		    rect(xx, yy, ww, hh, 10);
		    fill(0);
		    text(label, xx + 30, yy + 25);
	}
	
	
public void createDeleteButton() {
	String label = "Delete Account";
	  float xx=900;    // top left corner x position
	  float yy=550;    // top left corner y position
	  float ww=200;    // width of button
	  float hh=40; //height of the button
	  
	  textSize(14);
	  fill(250);
	  stroke(209, 24, 117, 100);
	
	    rect(xx, yy, ww, hh, 10);
	    fill(0);
	    text(label, xx + 30, yy + 25);
}

	
	public void DeleteUser() {
		 user = null;
		 
	}

	
}
