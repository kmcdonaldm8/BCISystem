package data;

import processing.core.PImage;
import gifAnimation.Gif;
import processing.core.PApplet;



public class Animation extends ThinkGearLoad {

	PImage[] images;
	int imageCount;
	int frame;
	
	Gif myAnimation;
	Animation anmtn1;
	
	public void setup() {
		myAnimation = new Gif(this, "file:/Users/kenestamcdonald/eclipse-workspace/ThinkGear/src/data/gifs/breathe/breathe.gif");
	    myAnimation.play();
	}
	public void draw() {
		noStroke();
		image(myAnimation, 0, 0);
		
	}
	Animation() {

	   // float xpos = width/2;
	    float ypos = -130;
	    
	    for (int i = 0; i < imageCount; i++) {
	

	     

	   
	    }
	}
	


}
