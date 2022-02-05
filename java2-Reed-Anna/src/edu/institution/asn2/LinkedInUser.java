package edu.institution.asn2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>, Serializable{

	private List<LinkedInUser> connections = new ArrayList<>();
	
	//Constructors for LinkedInUser
	public LinkedInUser () {
		super(); //super calls back to the base class
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

	

	@Override
	public int compareTo(LinkedInUser compareUser) {
		
		if (this.getUsername() == null) {
			return -1;
		}
		if (compareUser.getUsername() == null) {
			return 1;
		}
		
		return this.getUsername().compareToIgnoreCase(compareUser.getUsername());
	}
}
