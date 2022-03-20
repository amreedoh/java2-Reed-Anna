/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.institution.asn2.LinkedInUser;
import edu.institution.midterm.Part;

/**
 * Contains static helper methods to aid with the command line user interface.
 */
public class ApplicationHelper {
	/* I chose the HashMap because it allows you to store a key and a value together and retrieve based upon
	 * the key. Which is perfect for data sets that are connected and one value will always be unique(the key).
	 */
	static HashMap<String, Integer> usersPerSkill = new HashMap<String,Integer>();
	
	/**
	 * Displays the supplied message to the console.
	 * 
	 * @param message the message.
	 */
	public static void showMessage(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	* Increments the number of users associated with the supplied skillset.
	* If this is the first occurrence of the supplied skillset, then add
	* the skillset to your collection and default the count to one.
	*
	* @param skillset the skillset to increment.
	*/
	public static void incrementSkillsetCount(String skillset) {
		try {
			if (usersPerSkill.containsKey(skillset)) {
				usersPerSkill.replace(skillset, (usersPerSkill.get(skillset) + 1));
			}
			else {
				usersPerSkill.put(skillset, 1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	/**
	* Decrements the number of users associated with the supplied skillset.
	* If the number of users associated with the supplied skillset is zero,
	* then remove the skillset from your collection.
	*
	* @param skillset the skillset to decrement.
	*/
	public static void decrementSkillsetCount(String skillset) {
		try {
			if (usersPerSkill.containsKey(skillset)) {
				usersPerSkill.replace(skillset, (usersPerSkill.get(skillset) - 1));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	* Retrieves the number of users associated with the supplied skillset.
	* If the skillset is not in the collection, return -1.
	*
	* @param skillset the skillset to lookup.
	*/
	public static int retrieveSkillsetCount(String skillset) {
		if (usersPerSkill.get(skillset) == 0) {
			return -1; 
		}
		return usersPerSkill.get(skillset);
	}
	
	/**
	* Loops through each user and increments the global skillset usage count for
	* each skillset associated with the user.
	*
	* @param users the list of users.
	*/
	public static void initSkillsetUsages(List<LinkedInUser> users) {
		for (int loop = 0; loop > users.size(); loop++) {
			Iterator<String> tempSkills = users.get(loop).getSkillsets().iterator();
			while(tempSkills.hasNext()) {
				incrementSkillsetCount(tempSkills.next());
			}
		}
	}
}
