package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.actions.asn10.UndoAction.MostRecentAction;
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
		
		// TODO Anna, consider creating a new class to hold the undo-able data and place that class in your stack.
		// Example:
		// public class LinkedInAction {
		//   private MostRecentAction action;
		//   private Object data; // could hold the LinkedInUser or the Skillset (string)
		//   ... provide getter/setters, equals, and hashCode methods.
		// }
		// ...
		// UndoAction.history.push(new LinkedInAction(userRepository.retrieve(badFriend), MostRecentAction.DELETECON);
		// ...
		// then in the UndoAction, you would pop the stack, which would return the LinkedInAction class. You could then
		//  call the getter methods to get the data needed to undo the action.
		
		UndoAction.history.push(userRepository.retrieve(badFriend));
		UndoAction.history.push(MostRecentAction.DELETECON);
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
