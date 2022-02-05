package edu.institution.actions.asn3;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.institution.UserRepository;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class SerializedUserRepository implements UserRepository, Serializable {
	private String filePath;
	private String fileName;
	private List<LinkedInUser> users;
	
	@Override
	public void init(String filePath, String fileName) {
		//reads data from file and loads it
		this.filePath = filePath;
		this.fileName = fileName;

		try{
			File userFile = new File(filePath + fileName);
			if (!userFile.exists()) {	//if files does not exist create a new one
				userFile.createNewFile();
			}
			FileInputStream fis = new FileInputStream(userFile);
			
			if (userFile.length() > 0) { //if file is not empty load it into the array
				ObjectInputStream input = new ObjectInputStream(fis);
				LinkedInUser temp = new LinkedInUser();
				temp = (LinkedInUser) input.readObject();
				while(temp != null) {
					users.add(temp); //puts user in array
					temp = (LinkedInUser) input.readObject(); //reads in next user or null
				}
			input.close(); //closing streams
			fis.close();
			}
			
		} catch (FileNotFoundException e) { //Opening file has failed
			e.printStackTrace();
		} catch (IOException e) { //I/O operation was interrupted
			e.printStackTrace();
		} catch (ClassNotFoundException e) { //Object in file not compatible with object stated
			e.printStackTrace();
		}
	}

	
	@Override
	public void add(LinkedInUser user) throws LinkedInException {
		
		if(user.getUsername().isBlank() || user.getType().isBlank()) {
			throw new LinkedInException("The username and type are required to add a new user.");
		}
		else if (user.getType().equalsIgnoreCase("p") || user.getType().equalsIgnoreCase("s")) {
			throw new LinkedInException("Invalid user type. Valid user types are P or S");
		}
		else if (users.contains(user.getUsername())) {
			throw new LinkedInException("A user already exists eith that user name");
		}
		else {
			this.users.add(user); //adds a user from your list if no exception is thrown
			saveAll();
		} 
	}

	@Override
	public void saveAll() {
		//should either create a new file or delete existing file and save what you have, easier than an update
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath + fileName));){
			//this should cycle through users array list and add them to the file
			for(int i = 0; i < users.size(); i++) {
				output.writeObject(users.get(i));
			}
			
			output.close(); //closing streams
		} catch (FileNotFoundException e) {
			//Opening file has failed               
			e.printStackTrace();
		} catch (IOException e) {
			//I/O operation was interrupted
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(LinkedInUser user) {
		this.users.remove(user); //removes a user from your list
	}

	@Override
	public LinkedInUser retrieve(String username) {
		for(int i = 0; i < this.users.size(); i++) { //will go through all users looking for a match 
			if (username == this.users.get(i).getUsername()) {
				return this.users.get(i);
			}
		}
		return null; //if none is found will return null
	}

	@Override
	public List<LinkedInUser> retrieveAll() {
		if (this.users.isEmpty()) {
			return null;
		}
		List<LinkedInUser> listAll = new ArrayList<>(this.users);
		return listAll;
	}

}
