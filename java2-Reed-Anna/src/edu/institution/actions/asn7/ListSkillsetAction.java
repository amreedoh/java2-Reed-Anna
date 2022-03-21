package edu.institution.actions.asn7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
			
		Iterator<String> tempSkills = loggedInUser.getSkillsets().iterator();
		List<String> orderSkills = new ArrayList<>();

		while(tempSkills.hasNext()) {
			orderSkills.add(tempSkills.next());
		}
		
		Collections.sort(orderSkills);
		//System.out.println(orderSkills);
		int count = 0;
		System.out.println("Here are your skill sets:");
		for (int loop = 0; loop < orderSkills.size(); loop++) {
			count = ApplicationHelper.retrieveSkillsetCount(orderSkills.get(loop));
			System.out.println(orderSkills.get(loop) + " has " + count + " user(s)");
		}
		
		
		return true;
	}

	
}
