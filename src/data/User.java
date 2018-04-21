package data;

public class User {
	
	int uid;
	String FName;
	String pass;
	Session session;
	Session[] sessions;
	String email;
	int sessionsDone = 0;
	int high = 0;
	int low = 0;
	int[] averages = {};
	UserList userList;
	
	User( String fame,String e, String passw ){
	FName = fame;
	pass = passw;
	email = e;
	createSession();
	
	}
	User( String fame,String e, String passw,int[] x ){
		FName = fame;
		pass = passw;
		email = e;
		averages = x;
		createSession();
		
		}
	
	public Session createSession() {
		
		Session s = new Session(sessionsDone+1,this);
		setSessionNo();
		
		
		return s;
	}

	//get user email
	String getEmail() {
		return email;
	}

	//get highest value
	int getHigh() {
		return high;
	}
	//set high value
	void setHigh(int h) {
		high = h;
	}
	int getLow() {
		return low;
	}
	int[] getAverages() {
	
		return averages;
	}
	 public void addAverages(int newAverage) {
		 int[] tempArray = new int[averages.length+1];
		 for(int i = averages.length;i>0;i--) {
			 tempArray[i]=averages[i-1];
		 }
		 tempArray[0] = newAverage;
		 averages = tempArray;
	 }
	
	void setLow(int l) {
		low = l;
	}
	String getFName(){
		return FName;
	}
	void setFName(String n) {
		FName = n;
	}
	int getuid() {
		return uid;
	}
	String getPass() {
		return pass;
	}
	void setPass(String p) {
		pass = p;
	}
	int getSessionNo() {
	
		return sessionsDone;
	}
	public void setSessionNo(){
		sessionsDone++;
	}
	
	
}
