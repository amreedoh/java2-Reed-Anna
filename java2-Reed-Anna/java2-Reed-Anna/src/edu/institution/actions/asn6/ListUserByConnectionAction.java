package edu.institution.actions.asn6;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		List<LinkedInUser> users = userRepository.retrieveAll();
		Collections.sort(users);
		Collections.reverse(users);
		Collections.sort(users, LinkedInUser::compareByConnections);
		for (int i = users.size() - 1; i >= 0; i--) {
			System.out.println(users.get(i).getUsername() + " has " + users.get(i).getConnections().size() + " connections.");
		}
		
		return true;
	}

}
