package edu.institution.asn2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>, Serializable{
	private static final long serialVersionUID = 4498439148211156781L;
	private List<LinkedInUser> connections = new ArrayList<>();
	private Set<String> skillsets = new HashSet<String>();
	 
	//Constructors for LinkedInUser
	public LinkedInUser () {
		super(); //super calls back to the base class
	}
	public LinkedInUser (String username, String password) {
		super(username, password);
	}
	public LinkedInUser (String username, String password, String type) {
		super(username, password, type); 
	} 
	
	
	//methods 
	public void addConnection(LinkedInUser user) throws LinkedInException {
		
		if (connections.contains(user)) {
			throw new LinkedInException("You are already connected with this user.");
		}
		else
			connections.add(user);
		
	};
	
	public void removeConnection (LinkedInUser user) throws LinkedInException {
		if (connections.contains(user)) {
			connections.remove(connections.indexOf(user));
		}
		
		else
			throw new LinkedInException("You are NOT connected with this user");
	};
	
	public List<LinkedInUser> getConnections(){
		List<LinkedInUser> copyCon = new ArrayList<>(connections);
		return copyCon;
	}
 
	public Set<String> getSkillsets(){
		return skillsets;
	}
	
	public void addSkillset(String skillset) {
		skillsets.add(skillset);
	} 
	
	public void removeSkillset (String skillset) {
		skillsets.remove(skillset);
	}
	

	@Override
	public int compareTo(LinkedInUser compareUser) {
		
		if (this.getUsername().isEmpty()) {
			return -1;
		}
		if (compareUser.getUsername().isEmpty()) {
			return 1;
		}
		
		return this.getUsername().compareToIgnoreCase(compareUser.getUsername());
	}
	
	public int compareByConnections(LinkedInUser compareUser) {
		
		if (this.getUsername().isEmpty()) {
			return -1;
		}
		if (compareUser.getUsername().isEmpty()) {
			return 1;
		}
		
		return Integer.compare(this.getConnections().size(), compareUser.getConnections().size());
	}
	
}
