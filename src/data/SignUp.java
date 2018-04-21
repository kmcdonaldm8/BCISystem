package data;


import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PFont;


public class SignUp extends PApplet {
	

	PFont consolas;
	
	String[] user;
	boolean saveMode;
	boolean emailMode;
	boolean firstMode = true; 
	boolean passMode;
	boolean endMode;
	
	boolean e = false;
	boolean p = false;
	
	String Fname;
	String email;
	String password; 
	
	String Sname;
	String Smail;
	String Spass;
	String Hpass;
	String currentValue;
	
	int currentPos;
	UserList ul;

	public void setup () {
		  smooth();
		  size (1200, 700); 
	
	

		  consolas = createFont("Ariel", 100 );

		  textFont(consolas, 100);
		  
		  user = new String[4];
		  
		  for ( int i = 0; i < user.length; i++) {
		    user[i] = "";
		  } 
		  
		  currentValue = "";
		  Sname = "";
		  Smail = "";
		  Spass = "";
		  
		  currentPos = 0;
		}
	public void draw () {
		
		  background (255, 255, 255);
		  
		  if (saveMode) {
			  
			  text ("Signed Up! Click enter to continue.", 500, 300);
			 
			  
		  }else {
			  
			  fill (250);
			  //First name box
			  rect (300, 150, 600, 50);
			//Email box
			  rect (300, 250, 600, 50);
			  //Password box
			  rect (300, 350, 600, 50);
			  
			  pushStyle();
			  textAlign( CENTER, CENTER );
			  fill(0);
			  textSize(40);
			  if(passMode) {
				  String hidden = "";
				  
				  for ( int t=0; t < currentValue.length(); t++) {
				      hidden+="*";
				    }
				  Hpass = hidden;
				  text( hidden, 600, 120);
			  }else if (endMode){
				  
			  }else {
				  
				  text( currentValue, 600, 120);
			  }
		
			  popStyle();
			  fill (23, 85, 167);
			  textSize(50);
			  text ("Sign Up", 502, 71);
			
			  fill (250);
			  fill (23, 85, 167);
			  textSize(15);
			  if (firstMode) {
				  
				  text ("Type your first name and press enter", 442, 100); 
				  
			  } else if (emailMode) {
				  text ("Type your username and press enter", 442, 100);
				  fill (0);
				  textSize(40);
				  text(Sname, 310 ,190);
			  } else if (passMode) {
				  text ("Type your password and press enter", 442, 100);
				  textSize(40);
				  fill (0);
				  text(Sname, 310 , 190);
				  text(Smail, 310 , 290);
			  } else if (endMode) {
				  text ("Press enter to create account! :)", 442, 100);
				  textSize(40);
				  fill (0); 
				  text(Sname, 310 , 190);
				  text(Smail, 310 , 290);
				  text(Hpass, 310, 390);
			  }
			  	textSize(15);
			  	fill (23, 85, 167);
			  	text("Enter First Name : ", 302 ,130);
			  	text("Enter Email Address : ", 302 ,230);
			  	text("Enter Password : ", 302 ,330);
			  
			  	fill (0);
			  	textSize(15);

			  
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
			  if (firstMode) {
				  Sname = currentValue; 
				  user[currentPos] = currentValue;
			       currentValue = "";
			       currentPos++;
			       firstMode = false;
			       emailMode = true;
			  } else if (emailMode) { 
				  Smail = currentValue;
				  user[currentPos] = currentValue;
			       currentValue = "";
			       currentPos++;
			       emailMode = false;
			       passMode = true;
			  }else if (passMode) {
				  Spass = currentValue;
				  user[currentPos] = currentValue;
			       currentValue = "";
			       currentPos++;
			       passMode = false;
			       endMode = true;
			       System.out.print(Spass + " ");
			       System.out.print(Smail + " ");
			       System.out.print(Sname );
			       
			  } else if(endMode) {
				  endMode = false;
				  saveMode = true;
			  }
			  else if (saveMode) { 
				  //re-open first window
				  User temp = new User(Sname,Smail,Spass);
				  ThinkGearLoad tgl = new ThinkGearLoad();
				  tgl.createUser(Sname,Smail,Spass);	
				  //ul.addUser(temp);
				  
				  tgl.userList = ul;
				 	noLoop();
				   runSketch( new String[] { "--display=0",
			                "--location=0,0",
			                "--sketch-path=" + sketchPath,
			                "" },
				    		tgl );
  
			 
			  }


		  }
		}

}
	
