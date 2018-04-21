package data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ThinkGearLoadTest {
	private static ThinkGearLoad tgl;
	//User testUser = new User("Kenesta","goosess","Goose");
	
	 ThinkGearLoadTest(){
			this.setupClass();
			tgl.init();
			tgl.setup();
			tgl.draw();
	 }
		@BeforeAll
		static public void setupClass(){
			tgl = new ThinkGearLoad();
			
		}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		tgl.destroy();
	}
	@Test
	void exceptionError() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		//fill in
	}
	
	@Test
	void withUserTest() {
			tgl.u = new User("Kenesta","kmcd","goose");
			tgl.draw();
			tgl.grosor_air = 75;
			tgl.draw();
			assertEquals(tgl.sentido_air,1);
			tgl.grosor_air = 150;
			tgl.draw();
			assertEquals(tgl.sentido_air,-1);
			tgl.grosor_air = 15;
			tgl.inhalar = 1;
			tgl.draw();
			assertEquals(tgl.alpha_noAir,40);

		
	}
	@Test
	void createUserTest() {
		tgl.createUser("kenny", "SDfsdfs", "pass");
		tgl.getUser();
		assertNotNull(tgl.u);

	}
	@Test
	void ThinkGearTest() {
		tgl.poorSignalEvent(780);
		tgl.attentionEvent(100);
		assertEquals(tgl.attention,100);
		tgl.meditationEvent(20);
		assertEquals(tgl.meditation,20);
		assertNotNull(tgl.medis);
		tgl.blinkEvent(4);
		tgl.eegEvent(1,2,3,4,5,6,7,8);
		int[] t = {1,2,3};
		tgl.rawEvent(t);
		
		
	}
	@Test
	void mousePressedFirstTest() {
	

		tgl.u = new User("Kenesta","kmcd","goose");	
		tgl.mouseX = 901 ;
		tgl.mouseY = 101;
		tgl.mousePressed();
		
		
		
	}
	@Test
	void mousePressedSecondTest() {

		tgl.u = new User("Kenesta","kmcd","goose");	
		tgl.mouseX = 901 ;
		tgl.mouseY = 1;
		tgl.mousePressed();
		
	}
	@Test
	void mousePressedThirdTest() {

		tgl.u = new User("Kenesta","kmcd","goose");	
		tgl.mouseX = 901 ;
		tgl.mouseY = 51;
		tgl.mousePressed();
		
	}

}
