package edu.institution.actions.asn10;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class UndoAction implements MenuAction {

	//linkedInUser than most recent action enum
	public static Stack<LinkedInAction> history = new Stack<>();
	public enum MostRecentAction {	ADDCON, 
									DELETECON, 
									ADDSKILL, 
									DELETESKILL, 
									ADDUSER,
									DELETEUSER};

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		switch (history.peek().getAction()) {
			case DELETECON://add a connection back
				try {
					loggedInUser.addConnection((LinkedInUser) history.pop().getData());
				} catch (LinkedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            break;
			case ADDCON: //remove a new connection
				try {
					loggedInUser.removeConnection(((LinkedInUser) history.pop().getData()));;
				} catch (LinkedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            break; 
			case ADDSKILL: //remove a new skill
				LinkedInUser tempA = ((LinkedInUser) history.pop().getData());
				Iterator<String> itrA = loggedInUser.getSkillsets().iterator();
				while (itrA.hasNext()) {
					if (!tempA.getSkillsets().contains(itrA.toString())){
						loggedInUser.removeSkillset(itrA.toString());
						ApplicationHelper.decrementSkillsetCount(itrA.toString());
					}
				}
	            break; 
			case DELETESKILL://add a skill back
				LinkedInUser tempD = ((LinkedInUser) history.pop().getData());
				Iterator<String> itrD = tempD.getSkillsets().iterator();
				while (itrD.hasNext()) {
					if (!loggedInUser.getSkillsets().contains(itrD.toString())){
						loggedInUser.addSkillset(itrD.toString());
						ApplicationHelper.incrementSkillsetCount(itrD.toString());
					}
				}
	            break;
			case ADDUSER: //remove a new user
				try {
					userRepository.add((LinkedInUser) history.pop().getData());
				} catch (LinkedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	            break;
			case DELETEUSER: //add a user back
				userRepository.delete((LinkedInUser) history.pop().getData());
	            break;
		
		}

		return false;
	}

}
