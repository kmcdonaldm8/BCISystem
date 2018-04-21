package data;

import processing.core.PApplet;
import processing.core.PFont;

public class sessionGraph extends PApplet{
	int high;
	int low;
	int sessionNumber;
	//user preserved
	User ussy;
	PFont consolas;
	UserList usser;
	
public sessionGraph(Session s){
	//high = 100;
	//low=10;
	high = s.getLV();
	sessionNumber = s.sessionNo;
	low = s.getSV();
	ussy = s.sessionUser;
 }

public void setup() {
	size(1200, 900);
	
	consolas = createFont("Ariel", 100 );

	textFont(consolas, 100);
	
}
	public void draw(){
		  background(255);
		  createBackToGameButton();
		
			
					  fill(90);
		  noStroke();
		  rectMode(CORNERS);
		  rect(80, 100, width - 200, height - 100);
		  
		  rectMode(CORNER); // rect default
		  
		  //graph lines and text
		
		  stroke(255);
		  fill(255);
		  textSize(20);
		  //200 line
		  line(80,290,114,290);
		  text("200",100,278);
		  //100 line
		  line(80,540,114,540);
		  text("100",100,528);
		  
		  
		  fill(90);
		  
		  // rect 1
		  textSize(32);
		  textAlign(CENTER);
		  text("Your session results", 600, 50);
		  int value = (high + low )/2;
		  
		  float x = width-2;
		  float y = (float) ((value*10)*0.7);
		  float p = (float)(value*5)/2;

		  line(80,y,width-2,y);
		
		  fill(0, 102, 153);
		  if ((mouseX > 130) && (mouseX <= 130 + 119) && (mouseY > height - 100 - (high*10) * 0.7) && (mouseY <= height - 100)) {
			    fill(55, 31, 34);
			  } else {
			    fill(177, 211, 254);
			  }
		  
			  rect(130, height - 100, 119, (float)-(high*5)/2);
			  
		
		 // rect 2
		if ((mouseX > 200+120) && (mouseX <= 200 + 120 + 119) && (mouseY > height - 100 - (low*10) * 0.7) && (mouseY <= height - 100)) {
			    fill(55, 31, 34);
			  } else {
			    fill(177, 211, 254);
			  }
			  rect(200 + 120, height - 100, 119, (float)-(low*5)/2);
			  stroke(255);
			  //(float)(- (low*10) * 0.45)
		//average line
		line(80, p*4 , width-200, p*4);
		
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
		    text(label, x + 90, y + 25);

	}
	
	public void mousePressed(){
		  float x=900;    // top left corner x position
		  float y=50;    // top left corner y position
		  float w=200;    // width of button
		  float h=40; //height of the button
		    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h )) {
		    	  ThinkGearLoad tgl = new ThinkGearLoad();
		    	  //return to screen, with same user, and session number updated
				tgl.u = ussy;
				tgl.u.sessionsDone++;
				System.out.println("session:" + tgl.u.sessionsDone);
				 	noLoop();
				   runSketch( new String[] { "--display=0",
			                "--location=0,0",
			                "--sketch-path=" + sketchPath,
			                "" },
				    		tgl );

		    }
		
	}
	
	
}
