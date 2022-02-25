package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class DegreeOfSeparationAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.print("User to find: ");
		String findFriend = scanner.nextLine();
		
		if(userRepository.retrieve(findFriend) == null) {
			System.out.println("There is no user with that user name.");
			return true;
		}
		
		LinkedInUser endUser = userRepository.retrieve(findFriend);
		Set<LinkedInUser> ignore = new HashSet<>();
		List<LinkedInUser> pathToEndUser = new ArrayList<>();
		ignore.add(loggedInUser);
		
		if(createPathToEndUser(loggedInUser, endUser, pathToEndUser, ignore)) {
			System.out.print(loggedInUser.getUsername());
			for (int loop = 0; loop < pathToEndUser.size(); loop++) {
				System.out.print( " -> " + pathToEndUser.get(loop).getUsername());
			}
			System.out.println();
			System.out.println("Degrees of seperation: " + (pathToEndUser.size() - 1));
		}
		else {
			System.out.println("There is no connection.");
		}	
		
		return true;
	}
	
	private boolean createPathToEndUser(LinkedInUser startUser, LinkedInUser endUser, List<LinkedInUser> pathToEndUser, Set<LinkedInUser> ignore) {
		//- get startUser's connections. 
		List<LinkedInUser> startConnections = new ArrayList<>(startUser.getConnections());
		
		//If there are no connections, then, if pathToEndUser has at least one user in it, remove the last user and return false.
		if (startConnections.isEmpty()) {
			if (!pathToEndUser.isEmpty()) {
				pathToEndUser.remove(pathToEndUser.size() - 1);
			}
			return false;
		}

		// - if the endUser is in the startUser's connections, then add the endUser to the pathToEndUser list
		//and return true to indicate you've found the end user.
		if (startConnections.contains(endUser)) {
			pathToEndUser.add(endUser);
			return true;
		}
		
		//- create a List<LinkedInUser> called, path, which will contain the degree of separations starting from this connection to the end user.
		List<LinkedInUser> path = new ArrayList<>();
			
		//- Loop through startUser's connections.
		for (int loop = 0; loop < startConnections.size(); loop++) {
			// - if the connection is in the ignore set, then continue to the next connection because we've already checked that connection.
			if (ignore.contains(startConnections.get(loop))){
				continue;
			}
			else {
				// - otherwise, add the connection to both the pathToEndUser list and the ignore set.
				pathToEndUser.add(startConnections.get(loop)); 
				ignore.add(startConnections.get(loop));
			
				// - recursively call the createPathToEndUser method passing
				//the connection as the startUser, the endUser, the path (established at the beginning of this loop), and the ignore Set.
				
				// - if the call to the createPathToEndUser method returns true, then it found a path to the end user.
				//In that case, add the entries in the path variable to the pathToEndUser list and 
				//break out of the loop (you're done).  pathToEndUser.addAll(path).
				if (createPathToEndUser(startConnections.get(loop), endUser, path, ignore)) {
					pathToEndUser.addAll(path);
					return true;
				}
			}
		// - continue looping until all connections have been processed.
		// - return true/false to indicate if a path to the end user was found.
		}
		
		return false;		
	}


}

