package data;

import processing.core.PApplet;

public class userGraph extends ThinkGearLoad{
	int[] averageList;
	
	User uss ;
	
public userGraph(User us){
	int[]temp = {100,44,200};
	averageList = us.averages;
	uss = us;
	uss.averages = temp;

	averageList = uss.averages ;

 }
	public void draw(){
		  background(255);
 
	
		for (int i = 0; i<averageList.length;i++) {
		
				  

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
				  
				  fill(0, 102, 153);
				  // rect 1
				  textSize(32);
				  textAlign(CENTER);
				  text("Your Average results (last 5 sessions)", 600, 50);
				  int value = averageList[1];
				  
				  float x = width-2;
				  float y = (float) ((value*10)*0.7);
				  float p = (float) ((value*23) * 0.45);
					
				  fill(0, 102, 153);
				  rect((150)*i+170, height - 100, 119, (float)-(value*5)/2);
		
			  		 

	
		}
		
		  createBackToGameButton(); 
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
				tgl.u = uss;
				tgl.u.sessionsDone++;
				
				 	noLoop();
				   runSketch( new String[] { "--display=0",
			                "--location=0,0",
			                "--sketch-path=" + sketchPath,
			                "" },
				    		tgl );
		    }
		   }
}
