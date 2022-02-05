package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		System.out.print("Enter Username: ");
		String userName = scanner.nextLine();
		
		System.out.print("Enter Pasword: ");
		String userPass = scanner.nextLine();
		
		System.out.print("Enter User Type: ");
		String userType = scanner.nextLine();
		
		LinkedInUser newUser = new LinkedInUser(userName, userPass, userType);
		
		try {
			userRepository.add(newUser);
		} catch (LinkedInException e) {
			e.getMessage();
		}
		
		
		return true;
	}

}
