package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListConnectionAction implements MenuAction {
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		//if the logged in user has no connections, display the message "You have no connections" to the console.
		if(loggedInUser.getConnections().isEmpty()) {
			System.out.println("You have no connections.");
		}
		
		//will loop through the logged in user's connections and display the user name of each linkedIn user in the connections list
		for (int count = 0; count < loggedInUser.getConnections().size(); count ++) {
			System.out.println(loggedInUser.getConnections().get(count).getUsername());
		}
			
		return true;//keep user logged in
	}

}
