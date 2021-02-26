package objects;

import java.util.UUID;

public class Credentials {
	
	
	public static final String USER1_NAME = "Milivoje Milivojevic";

	
	public static final String USER2_NAME = "Milanka Milankovic";
	
	
	public static final String LOGINUSERNAME = "danilodanilovic@gmail.com";
	public static final String LOGINPASSWORD = "0637423";
	



public static String randomEmail() {
    return "random-" + UUID.randomUUID().toString() + "@gmail.com";
}

public static String randomName() {
	return UUID.randomUUID().toString();
}
}