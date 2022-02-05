package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class DeleteUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		System.out.print("User name to delete: ");
		String deleteUser = scanner.nextLine();
		
		if (userRepository.retrieve(deleteUser).getUsername().equalsIgnoreCase(deleteUser)) {//checks if user is in repo
			System.out.print("Password: ");
			String deletePass = scanner.nextLine();
			
			if(userRepository.retrieve(deleteUser).isPasswordCorrect(deletePass)) {
				userRepository.delete(userRepository.retrieve(deleteUser)); //will delete user
				if (deleteUser.equalsIgnoreCase(loggedInUser.getUsername())) {
					return false;//will log off user if the logged in user deletes thier account
				}
			}
			else {
				System.out.println("User names do not match.");
			}
		}
		else {
			System.out.println("No user by that name exists.");
		}
		
		
		return true; //keeps user signed in
	}

}
