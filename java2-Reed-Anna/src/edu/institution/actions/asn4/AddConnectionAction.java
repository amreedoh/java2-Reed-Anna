package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddConnectionAction implements MenuAction {
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		//Prompt for the user name of the user to connect with
		System.out.print("Username: ");
		String newFriend = scanner.nextLine();
		
		//if the user does not exist display "There is no user with that user name"		
		if(userRepository.retrieve(newFriend) == null) {
			System.out.println("There is no user with that user name.");
			return true;
		}
		
		
		//if the user is already in the logged in user's connection list then catch the LinkedInException that was thrown in the second assignment and display the message within the exception
		//if the user does exist, add the user to the logged in user's connection list and display "The connection was added successfully"
		try {
			loggedInUser.addConnection(userRepository.retrieve(newFriend));
		}catch(LinkedInException e){
			System.out.println(e);
			return true;
		}
		try { //will also add you to your friends connection list
			userRepository.retrieve(newFriend).addConnection(loggedInUser);
		}catch(LinkedInException e){
			System.out.println(e);
			return true;
		}
		System.out.println("The connection was added successfully.");
		
		
		return true;//Retrun true to keep user logged in
	}

}
