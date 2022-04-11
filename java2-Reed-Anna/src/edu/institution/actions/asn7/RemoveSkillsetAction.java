package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.LinkedInAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.actions.asn10.UndoAction.MostRecentAction;
import edu.institution.asn2.LinkedInUser;

public class RemoveSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		System.out.println("What skill would you like to remove?");
		String removeSkill = scanner.nextLine();
		
		UndoAction.history.push(new LinkedInAction(MostRecentAction.DELETESKILL, loggedInUser));
		if (loggedInUser.getSkillsets().contains(removeSkill)) {
			loggedInUser.removeSkillset(removeSkill);
			ApplicationHelper.decrementSkillsetCount(removeSkill);
			System.out.println(removeSkill + "has been deleted from your skill set");
		};
		
		return true;
	}

}
