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
	public enum MostRecentAction {	ADDCON("Add Connection"), 
									DELETECON("Delete Conection"), 
									ADDSKILL("Add Skill"), 
									DELETESKILL("Delete Skill"), 
									ADDUSER("Add New User"),
									DELETEUSER("Delete User");

									private String displayName;

									MostRecentAction(String displayName) {
										this.displayName = displayName;
									}
									
									public String display() {
										return this.displayName;
									}
	};

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		if (!history.empty()) {

		String tempName;

		if(history.peek().getAction() == MostRecentAction.DELETESKILL || history.peek().getAction() == MostRecentAction.ADDSKILL){
			tempName = loggedInUser.getUsername() + " skills";
		} else {
			tempName = history.peek().getUser().getUsername();
		}
		
		System.out.println("Do you wish to undo the " + history.peek().getAction().display() + " action?");
		System.out.println("This action involves " + tempName + "?");
		String answer = scanner.nextLine();
		
		
		if (answer.equalsIgnoreCase("y")) {
			switch (history.peek().getAction()) {
				case DELETECON://add a connection back
					try {
						loggedInUser.addConnection(history.pop().getUser());
						System.out.println("Undo Complete");
					} catch (LinkedInException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            break;
				case ADDCON: //remove a new connection
					try {
						loggedInUser.removeConnection(history.pop().getUser());
						System.out.println("Undo Complete");
					} catch (LinkedInException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            break; 
				case ADDSKILL: //remove a new skill
					LinkedInUser tempA = (history.pop().getUser());
					Iterator<String> itrA = loggedInUser.getSkillsets().iterator();
					while (itrA.hasNext()) {
						if (!tempA.getSkillsets().contains(itrA.toString())){
							loggedInUser.removeSkillset(itrA.toString());
							ApplicationHelper.decrementSkillsetCount(itrA.toString());
							System.out.println("Undo Complete");
							break;
						}
					}
		            break; 
				case DELETESKILL://add a skill back
					LinkedInUser tempD = (history.pop().getUser());
					Iterator<String> itrD = tempD.getSkillsets().iterator();
						while (itrD.hasNext()) {
							if (!loggedInUser.getSkillsets().contains(itrD.toString())){
								loggedInUser.addSkillset(itrD.toString());
								ApplicationHelper.incrementSkillsetCount(itrD.toString());
								System.out.println("Undo Complete");
								break;
							}
						}
			            break;
					case ADDUSER: //remove a new user
						try {
							userRepository.add(history.pop().getUser());
							System.out.println("Undo Complete");
						} catch (LinkedInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
			            break;
					case DELETEUSER: //add a user back
						userRepository.delete(history.pop().getUser());
						System.out.println("Undo Complete");
			            break;
				
					}
				}
			}else {
				System.out.println("There are no actions to undo.");
			}
		return true;
	}
}
