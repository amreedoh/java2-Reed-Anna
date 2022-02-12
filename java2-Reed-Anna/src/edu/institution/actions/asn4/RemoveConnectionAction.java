package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class RemoveConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		//prompt user for user to remove
		System.out.print("User to remove: ");
		String badFriend = scanner.nextLine();
		
		//if user does not exist display "There is no user with that user name."
		if(userRepository.retrieve(badFriend) == null) {
			System.out.println("There is no user with that user name.");
			return true;
		}
		
		try {
			loggedInUser.removeConnection(userRepository.retrieve(badFriend));
		}catch(LinkedInException e){
			System.out.println(e);
			return true;
		}
		try {//will also remove you from your friends list
			userRepository.retrieve(badFriend).removeConnection(loggedInUser);
		}catch(LinkedInException e){
			System.out.println(e);
			return true;
		}
		System.out.println("The connection was removed successfully.");
		
		return true;
	}

}
