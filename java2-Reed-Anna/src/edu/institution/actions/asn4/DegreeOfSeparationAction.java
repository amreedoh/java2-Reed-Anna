package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class DegreeOfSeparationAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.print("User to find: ");
		String findFriend = scanner.nextLine();
		
		
		DegreeOfSeparationAction DOSA = new DegreeOfSeparationAction();
		long degrees = DOSA.getFriendPath(findFriend, loggedInUser);
		

		
		
		return true;
	}
	
	public long getFriendPath(String name, LinkedInUser user) {
		long degree = 0;
		for(int count = 0; count < user.getConnections().size(); count++) {
			DegreeOfSeparationAction temp = new DegreeOfSeparationAction();
			if (user.getConnections().get(count).getUsername().equalsIgnoreCase(name)) {//if user has friends
				return degree;
			}
			else if (!user.getConnections().get(count).getUsername().equalsIgnoreCase(name)) {
				degree =+ temp.getFriendPath(name, user.getConnections().get(count));
			}
			else if (user.getConnections().isEmpty()) {//if user has no friends
				continue;
			}
		}
		
		
		
		return degree;
	}

}
