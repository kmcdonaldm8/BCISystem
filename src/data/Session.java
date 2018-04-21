package data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;;

public class Session {
	
	public int largestValue;
	public int smallestValue;
	public int averageValue;
	int sessionNo;
	int[] sesh;
	User sessionUser;
	public int height,width,value,nix,nwy;
	
	Session(int sess, User u){
		sessionNo = sess;
		sessionUser = u;
	
	}
	sessionGraph getGraph() {
		sessionGraph sg = new sessionGraph(this);
		return sg;
	}
	public void setLV(int l) {
		largestValue = l;
	}
	public int getLV() {
		return largestValue;
	}
	public void setSV(int s) {
		smallestValue = s;
	}
	public int getSV() {
		return smallestValue;
	}
	public void setUserAverage() {
		sessionUser.addAverages(averageValue);
	}
	public int getAV() {
		averageValue = (smallestValue + largestValue)/2 ;
		return averageValue;
	}
}
