package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.LinkedInAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.actions.asn10.UndoAction.MostRecentAction;
import edu.institution.asn2.LinkedInUser;

public class AddSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		System.out.println("What skill would you like to add?");
		String newSkill = scanner.nextLine(); 
		
		UndoAction.history.push(new LinkedInAction(MostRecentAction.ADDSKILL, loggedInUser));
		loggedInUser.addSkillset(newSkill);
		ApplicationHelper.incrementSkillsetCount(newSkill);
		
		System.out.println(newSkill + "has been added to your skill set");
		
		return true;
	}

}
