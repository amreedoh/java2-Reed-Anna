package edu.institution.actions.asn3;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> users = userRepository.retrieveAll();
		
		//loops through the user list and prints each user.
		for (int i =0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
		}
		return true; //keeps user logged in  
	}

}
