package data;
/**
 * 
 */

/**
 * @author kenestamcdonald
 *
 */
//processing imports
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.Component;
//java imports
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//sound imports
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;



import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
//gif imports
import gifAnimation.*;
//neurosky imports
import neurosky.*;


public class ThinkGearLoad extends PApplet{
	//breathing setup
	float grosor_air = 20;
	float grosor_noAir = 0;
	float vel_respiracion = (float) 1.8;
	float sentido_air = 1;
	float sentido_noAir = 0;
	float color_air = 255;
	float alpha_air = 255;
	float alpha_noAir = 0;
	int inhalar = 0;
	int exhalar = 0;


	//rain drops
	Rain r1;
	int numDrops = 200;
	Rain[] drops = new Rain[numDrops]; 
	
	//image locations
	float xpos;
	float ypos;
	float drag = (float) 30.0;
	int progressBarValue;

	
	//array for meditation values
	int[] medis = {};
	
	Gif myAnimation;
	//array for meditation values of user
	int[] currentArray;
	//array of all users
	int[] userArray;
	//current size of current array 
	int currentSize;
	int state = 0;
	
	//audio context
	static PApplet parent;// The parent PApplet that we will render ourselves onto
	static PApplet m = parent;


	//thinkGear data
	public ThinkGearSocket neuroSocket;

	public int attention=10;
	public int meditation=10;
	

	public int length=1200;
	public int height=900;
 
	boolean init;
	
	//create recording value
	int[] y;
	
	public PFont font;
	//sign up page
	SignUp su;
	
	//save user details in a list
	UserList userList;
	
	//is there a user?
	User u = null;
	/**
	 * @param args
	 */
	//
	public void setup() 
	{ 
		//size of pop-up and animation
		size(length, height);
		  smooth();
		  noFill();
		
		
		int a = 3;
		int fragNumber =  a;
		
		String path  = "file:/Users/kenestamcdonald/eclipse-workspace/ThinkGear/src/data/gifs/breathe/breathe";
		String realPath = path + a + ".gif";
		
		
		
			imageMode(CENTER);
			fill(0, 0, 0);
			smooth();

		    
		    //Loop through array to create each object
			  for (int i = 0; i < drops.length; i++) {

			    drops[i] = new Rain(); // Create each object
			    r1 = new Rain();
			  }
	
	
		
		//animation setup
		myAnimation = new Gif(this, realPath);
	    myAnimation.play();

		//connect to thinkgear
		  ThinkGearSocket neuroSocket = new ThinkGearSocket(this);
		  try {
		    neuroSocket.start();
		 
		  } 
		  catch (Exception e) {
		    println("Check to see if the Thinkgear is running!");
		  }
		  
		
		  smooth();
		  //noFill();
		  font = createFont("Verdana",20);
		  textFont(font);
			//rain drops
	  
	}
	
	public void draw(){
	
		background(165, 230, 231);
	
		displayPlayScreen();
	
		
		
		 fill(255,80);
		  rect(0,0,1200,900);
		  //Loop through array to use objects.
		  for (int i = 0; i < drops.length; i++) {
		    drops[i].fall();
		  }

		  stroke(255);
		  
			  //movimiento 'NO AIR', color 'AIR', alpha 'AIR'  
		  if (grosor_air <= 75) {
			    sentido_air = 1;
			  }
			  //reducirse
			  if (grosor_air >= 150) {
			    sentido_air = -1;
			    inhalar += 1;
			  }
			  
			  //alpha 'NO AIR'
			  if ( grosor_air <= 15 && inhalar >= 1) {
			    alpha_noAir = 40;
			  }

			  //movimiento 'NO AIR', color 'AIR', alpha 'AIR'  
			  if (grosor_air <= 90) {
			    sentido_noAir += 0.1;
			    grosor_noAir += sentido_noAir * vel_respiracion;
			    color_air -= 1;
			    alpha_air -= 3;
			  }


			  grosor_air += sentido_air * vel_respiracion;

			  strokeWeight(grosor_air);
		
			  // 'A'
			  line(400, 500, 470, 350);
			  line(470, 350, 540, 500);
			  line(433, 430, 507, 430);
			  // 'I'
			  line(580, 500, 580, 350);
			  // 'R'
			  line(640, 500, 640, 350);
			  line(640, 350, 720, 350); 
			  line(640, 430, 705, 430);
			  
			  line(705, 430, 750, 500); 

		  //reset stroke
		  stroke(209, 24, 117, 100);
		  strokeWeight(2);
	}


	public void displayPlayScreen() {
		
		 //set up animation
		//image(myAnimation, width/2, height/2, 600, 600);
		

		displayProgressBar();
	

		//attention data
		textSize(14);
		//text("Attention: "+ attention, 10, 30);
		noFill();
	//ellipse(20,95,attention*3,attention*3);
		
		//meditation data
		 text("Meditation: "+ meditation, 10, 50);
		 stroke(209, 24, 117, 100);
		 noFill();
		 // ellipse(50,95,meditation*3,meditation*3);

		 //User information
		 if (u == null) {
			textSize(20);
			String welcometext = "Welcome Back ";
			String total = welcometext ;
			text(total, 480, 110);
			//button signup setup
			createSignInButton();
		 }else {
			 	textSize(20);
				String welcometext = "Welcome Back, ";
				String name = u.FName;
				String total = welcometext + name + " !";
				text(total, 480, 110);
				//button session setup
				createUserDetailsButton();
				userList = new UserList();
				userList.addUser(u);
				
				createLogOutButton();
				createAccountButton();
				
		 }
	
		//music setup
		 try {
			playSong();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			 
		
	}
	
	
	
	public void mousePressed() {
		//log out button
		float x=900;    // top left corner x position
		float y=100;    // top left corner y position
		float w=200;    // width of button
		float h=40;
		float xxx=900;    // top left corner x position
		float yyy=50;    // top left corner y position
		float www=200;    // width of button
		float hhh=40; //height of the button
		float xx=900;    // top left corner x position
		float yy=0;    // top left corner y position
		float ww=200;    // width of button
		float hh=40; //height of the button
		float xxxx=900;    // top left corner x position
		float yyyy=50;    // top left corner y position
		float wwww=200;    // width of button
		float hhhh=40; //height of the button
		
		if (u != null) {
			//log out
			 cursor(HAND);
			if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h )) {
	    		
	    		  ThinkGearLoad tgl = new ThinkGearLoad();
				 	noLoop();
				   runSketch( new String[] { "--display=0",
			                "--location=0,0",
			                "--sketch-path=" + sketchPath,
			                "" },
				    		tgl );
				    
			} else if (mouseX > xx && mouseX < (xx + ww) && mouseY > yy && mouseY < (yy + hh )) {
		     	//Account details
		    		noLoop();
		        runSketch( new String[] { "--display=0",
		                "--location=0,0",
		                "--sketch-path=" + sketchPath,
		                "" },
			    		new AccountPage(u,userList) );
		        
		    } else   if (mouseX > xxxx && mouseX < (xxxx + wwww) && mouseY > yyyy && mouseY < (yyyy + hhhh )) {
			       //session details
	    		   Session ss =	u.createSession();
		       ss.smallestValue = getMin(medis);
		       ss.largestValue = getMax(medis);
		       
		       sessionGraph sessGraph = new sessionGraph(ss);
		       sessGraph.usser = userList;
		       
		     	noLoop();
		        runSketch( new String[] { "--display=0",
		                "--location=0,0",
		                "--sketch-path=" + sketchPath,
		                "" },
			    		sessGraph );
		    }
		} else {
			 //sign up
			 cursor(HAND);
			if (mouseX > xxx && mouseX < (xxx + www) && mouseY > yyy && mouseY < (yyy + hhh )) {
				SignUp signUp = new SignUp();
				signUp.ul = userList;
				noLoop();
				runSketch( new String[] { "--display=0",
	                "--location=0,0",
	                "--sketch-path=" + sketchPath,
	                "" },
		    		signUp );
		 }

	    } 
		   
	}
	public void createLogOutButton() {
		String label = "Log out";
		  float x=900;    // top left corner x position
		  float y=100;    // top left corner y position
		  float w=200;    // width of button
		  float h=40; 
		  rect(x, y, w, h, 10);
		  text(label, x + (w / 9), y + 25);
		 
	}
	
	public void createSignInButton() {
		String label = "Sign Up";
		  float xxx=900;    // top left corner x position
		  float yyy=50;    // top left corner y position
		  float www=200;    // width of button
		  float hhh=40; //height of the button

		    rect(xxx, yyy, www, hhh, 10);
		    text(label, xxx + (www / 9), yyy + 25);
		    
	}
	public void createAccountButton() {
		cursor(HAND);
		String label = "Account Details";
		float xx=900;    // top left corner x position
		float yy=0;    // top left corner y position
		float ww=200;    // width of button
		float hh=40; //height of the button
		rect(xx, yy, ww, hh, 10);
	    text(label, xx + (ww / 9), yy + 25);

		
	}
	
	
	//session details
	public void createUserDetailsButton() {
		 String label = "Session Details";
		  float xxxx=900;    // top left corner x position
		  float yyyy=50;    // top left corner y position
		  float wwww=200;    // width of button
		  float hhhh=40; //height of the button


		    rect(xxxx, yyyy, wwww, hhhh, 10);
		    text(label, xxxx + (wwww / 9), yyyy + 25);
		
	}
	public User createUser(String f, String e, String p) {
		
		u = new User(f,e,p);
		return u;
	}
	public User getUser() {
		return u;
	}
	public void poorSignalEvent(int sig) {
			//test method
			sig = 780;
		
		 	//real method to collect signal event
		  	println("SignalEvent "+sig);
		  	
		}

		public void attentionEvent(int attentionLevel) {
			//real method to collect attention level
			println("Attention Level: " + attentionLevel);
			attention = attentionLevel;
		}
 
// this is the function that creates a meditation value from 0 - 200
		public int meditationEvent(int meditationLevel) {
			//real method to collect meditation level
			println("Meditation Level: " + meditationLevel);
			
			meditation = meditationLevel;
			int[] newArray = new int[medis.length+1];
		
			for (int i = 0;i<medis.length;i++) {
				newArray[i]= medis[i];
				System.out.println(newArray[i]);
			}
			newArray[newArray.length-1] = meditation;
			medis = newArray; 
			getMax(medis);
			getMin(medis);
			
			return meditation;
			
		}
		public int getMax(int[] a) {
			int max = 0;
			for(int i = 0; i < a.length; i++) {
			      if(a[i] > max) {
			         max = a[i];
			      }
			}
			System.out.println("Max : " + max);
			return max;
		}
		public int getMin(int[] a) {
			int min = 200; 
			for(int i = 0; i < a.length; i++) {
			      if(a[i] < min) {
			         min = a[i];
			      }
			}
			System.out.println("Min " + min);
			return min;
		}

		public void blinkEvent(int blinkStrength) {
			//real method to collect blink strength
			println("blinkStrength: " + blinkStrength);
		}

		 public void eegEvent(int delta, int theta, int low_alpha, int high_alpha, int low_beta, int high_beta, int low_gamma, int mid_gamma) {
			//real method to collect e.e.g Event data
			 println("delta Level: " + delta);
			 println("theta Level: " + theta);
			 println("low_alpha Level: " + low_alpha);
			 println("high_alpha Level: " + high_alpha);
			 println("low_beta Level: " + low_beta);
			 println("high_beta Level: " + high_beta);
			 println("low_gamma Level: " + low_gamma);
			 println("mid_gamma Level: " + mid_gamma);
		}

		void rawEvent(int[] raw) {
			//real method to collect raw event level
		  println("rawEvent Level: " + raw);
		}	
		
		//display bar that shows movement or progress
		void displayProgressBar() {
			 
			    int progressX = 10;
			    int progressY = 670;
			    int progressHeight = 40;
			    
			    progressBarValue = meditation*10;
			    //saveSession( progressBarValue);
			   // saveSession(10);
			    rect(progressX, progressY, progressBarValue, progressHeight);
			     
			}
		

		
		// play background music, just change the music in the gif folder to which ever music you want
		void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			String filePath = "/Users/kenestamcdonald/eclipse-workspace/ThinkGear/src/data/gifs/breathe/ocean.wav";
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filePath));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f);
			clip.start();
			
}
		  class Rain {
			  
			  float r = random(1200);
			  float y = random(-height);
			  float radius = random(1,2);
			  int mx = 100;

			  void fall() {
				  
				 
				  y = y + 7 ;
				  float x2 = r;
				  float y2 = y+50;
				  
				  fill(27,36,95);
				  noStroke();
				  rect(r, y, radius, exp(radius)/2);

				  if(y>height){
					  r = random(1200);
					  y = random(-200);
				  }
			  }
		  }
		  
		
		  public AudioFormat getOutFormat(AudioFormat inFormat) {
		        final int ch = inFormat.getChannels();
		        final float rate = inFormat.getSampleRate();
		        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
		    }
		  
		  private void stream(AudioInputStream in, SourceDataLine line) 
			        throws IOException {
			        final byte[] buffer = new byte[65536];
			        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
			            line.write(buffer, 0, n);
			        }
			    }



		
}
				

		  
		  
		  

