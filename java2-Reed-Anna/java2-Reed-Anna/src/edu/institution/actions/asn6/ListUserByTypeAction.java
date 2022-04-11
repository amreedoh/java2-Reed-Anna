package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByTypeAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		List<LinkedInUser> users = userRepository.retrieveAll();
		List<LinkedInUser> ignore = new ArrayList<>();
		Collections.sort(users);
		
		for (int i =0; i < users.size(); i++) {
			if (users.get(i).getType().equalsIgnoreCase("p")){
					System.out.println(users.get(i).getUsername() + " is type " + users.get(i).getType().toUpperCase());
					users.remove(i);
					i--;
			}
		}
		for (int i =0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername() + " is type " + users.get(i).getType().toUpperCase());
		}
		
		return true;
	}

}
