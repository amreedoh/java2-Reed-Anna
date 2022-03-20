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

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class SerializedUserRepository implements UserRepository, Serializable {
	private static final long serialVersionUID = 4498439148211156781L;
	private String filePath;
	private String fileName;
	private List<LinkedInUser> users = new ArrayList<>();
	
	@Override //working
	public void init(String filePath, String fileName) {
		//reads data from file and loads it
		this.filePath = filePath;
		this.fileName = fileName;
 
		File userFile = new File(filePath + fileName);
		if(userFile.exists()) {
			try(FileInputStream fis = new FileInputStream(userFile);
					ObjectInputStream ois = new ObjectInputStream (fis)){
				this.users = (List<LinkedInUser>) ois.readObject();
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}else {
			//makes sure path exists 
			new File(filePath).mkdirs();
			File file = new File(filePath + fileName);
			try {
				file.createNewFile();
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}
		ApplicationHelper.initSkillsetUsages(users);
	}

	
	@SuppressWarnings("unused")
	@Override
	public void add(LinkedInUser user) throws LinkedInException {
		
		if(user.getUsername().isBlank() || user.getType().isBlank()) {
			throw new LinkedInException("The username and type are required to add a new user.");
		}
		if (!(user.getType().equalsIgnoreCase("p") || user.getType().equalsIgnoreCase("s"))) {
			throw new LinkedInException("Invalid user type. Valid user types are P or S");
		}
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).equals(user)) {
				throw new LinkedInException("A user already exists eith that user name");
			}
		}
		
		//if(true) {throw new LinkedInException("did you catch me??");}
		users.add(user); //adds a user from your list if no exception is thrown
		saveAll();
	}

	@Override //working
	public void saveAll() {
		//should either create a new file or delete existing file and save what you have, easier than an update
		File file = new File(filePath + fileName);
		if(file.exists()) {
			file.delete();
		}
		
		//makes sure path exists
		new File(filePath).mkdirs();
		
		try(FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fout);){
			oos.writeObject(this.users);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		
	}

	@Override //working
	public void delete(LinkedInUser user) {
		this.users.remove(user); //removes a user from your list
	}

	@Override //working
	public LinkedInUser retrieve(String username) {
		for(int i = 0; i < this.users.size(); i++) { //will go through all users looking for a match 
			if (username.equalsIgnoreCase(this.users.get(i).getUsername())) {
				return this.users.get(i);
			}
		}
		return null; //if none is found will return null
	}

	@Override //working
	public List<LinkedInUser> retrieveAll() {
		List<LinkedInUser> newList = new ArrayList<>();
		if (this.users == null) {
			return newList; 
		}
		List<LinkedInUser> listAll = new ArrayList<>(this.users);
		return listAll;
	}

}
