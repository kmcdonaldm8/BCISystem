package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class AccountPageTest {
	private static AccountPage ap;
	static User u = new User("Kenesta", "sdfsd","pass");
	static  UserList ul;

	AccountPageTest(){
		this.setupClass();
		ap.init();
		ap.setup();
		ap.draw();
	}
	static public void setupClass(){
		ap = new AccountPage(u,ul);
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ap.destroy();
	}

	@Test
	void testSetup() {

		assertNotNull(ap.consolas);
	}
	@Test
	void testDraw() {
		ap.success = true;
		ap.draw();
		ap.successDelete = true;
		ap.draw();
	}
	@Test
	void testFirstMousePressed() {
		ap.mousePressed();
		ap.keyTyped();
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mouseX = 901 ;
		ap.mouseY = 51;
		ap.mousePressed();
		
	}
	@Test
	void testSecondMousePressed() {
		ap.mousePressed();
		ap.keyTyped();
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mouseX = 351 ;
		ap.mouseY = 151;
		ap.mousePressed();
		assertFalse(ap.normal);
	}
	@Test
	void testThirdMousePressed() {
		ap.mousePressed();
		ap.keyTyped();
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mouseX = 351 ;
		ap.mouseY = 251;
		ap.mousePressed();
		assertTrue(ap.changeEmail);
		
	}
	@Test
	void testFourthMousePressed() {
		ap.mousePressed();
		ap.keyTyped();
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mouseX = 351 ;
		ap.mouseY = 151;
		ap.mousePressed();
		ap.mouseX = 351 ;
		ap.mouseY = 151;

		
	}
	@Test
	void testFifthMousePressed() {
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mousePressed();
		ap.keyTyped();
		ap.mouseX = 900 ;
		ap.mouseY = 551;
		ap.mousePressed();
		assertFalse(ap.successDelete);

		
	}
	@Test
	void testSixthMousePressed() {
		ap.mousePressed();
		ap.keyTyped();
		ap.user = new User("Kenesta","kmcd","goose");	
		ap.mouseX = 103 ;
		ap.mouseY = 491;
		ap.mousePressed();
		
	}
	@Test
	void deletUserTest() {
		
		ap.user = new User("Kenesta","kmcd","goose");
		ap.DeleteUser();
		assertNull(ap.user);
	}


}
