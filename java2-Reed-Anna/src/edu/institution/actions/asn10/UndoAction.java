package edu.institution.actions.asn10;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class UndoAction implements MenuAction {

	//linkedInUser than most recent action enum
	public static Stack<?> history = new Stack<>();
	public enum MostRecentAction {	ADDCON, 
									DELETECON, 
									ADDSKILL, 
									DELETESKILL, 
									ADDUSER,
									DELETEUSER};

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		if (!history.peek().getClass().equals(loggedInUser.getClass())){
			MostRecentAction MRA = (MostRecentAction) history.pop();
			switch (MRA) {
				case DELETECON://add a connection back
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
						try {
							loggedInUser.addConnection((LinkedInUser) history.pop());
						} catch (LinkedInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
	                break;
				case ADDCON: //remove a new connection
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
						try {
							loggedInUser.removeConnection(((LinkedInUser) history.pop()));;
						} catch (LinkedInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
	                break; 
				case ADDSKILL: //remove a new skill
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
						LinkedInUser temp = ((LinkedInUser) history.pop());
						Iterator<String> itr = loggedInUser.getSkillsets().iterator();
						while (itr.hasNext()) {
							if (!temp.getSkillsets().contains(itr.toString())){
								loggedInUser.removeSkillset(itr.toString());
							}
						}
					};
	                break; 
				case DELETESKILL://add a skill back
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
						LinkedInUser temp = ((LinkedInUser) history.pop());
						Iterator<String> itr = loggedInUser.getSkillsets().iterator();
						while (itr.hasNext()) {
							if (!temp.getSkillsets().contains(itr.toString())){
								loggedInUser.addSkillset(itr.toString());
							}
						}
						
					};
	                break;
				case ADDUSER: //remove a new user
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
						try {
							userRepository.add((LinkedInUser) history.pop());
						} catch (LinkedInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
	                break;
				case DELETEUSER: //add a user back
					if (history.peek().getClass().equals(loggedInUser.getClass())) {
							userRepository.delete((LinkedInUser) history.pop());
					};
	                break;
			
			}
		}
		return false;
	}

}
