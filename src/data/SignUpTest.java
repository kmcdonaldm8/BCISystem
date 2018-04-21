package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import processing.core.PApplet;

class SignUpTest extends PApplet{
	
	private static SignUp su;
	
	

	SignUpTest(){
		this.setupClass();
		su.init();
		su.setup();
		su.draw();
	}
	
	
	@BeforeAll
	static public void setupClass(){
		su = new SignUp();
		
	}

	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		su.destroy();
	}
	@Test
	void testSetup() {
		String[] test = new String[4];
		assertEquals(su.user.length,test.length);
	}
	@Test
	void testfirst(){
		assertEquals(su.firstMode,true);
	}
	@Test
	void testSaveMode() {
		su.firstMode = false; 
		su.saveMode = true;
		su.draw();
	}
	@Test
	void testEndMode() {
		su.firstMode = false; 
		su.endMode = true;
		//nothing to test
	}
	@Test
	void testEmailMode() {
		su.firstMode = false; 
		su.emailMode = true;
		su.draw();
		//nothing to test
	}
	@Test
	void testPassMode() {
		su.firstMode = false; 
		su.passMode = true;
		su.draw();
		//nothing to test
	}
	@Test
	void testPass(){
		su.firstMode = false;
		su.passMode = true;
		su.draw();
		assertEquals(su.Hpass, "");
		su.currentValue = "test";
		su.draw();
		assertNotEquals(su.Hpass, "");
	}
	@Test
	void testEmail(){
		su.passMode = false;
		su.emailMode = true;
		su.draw();

	}
	@Test void testKeyTyped() {
		su.keyTyped();
	}




}
