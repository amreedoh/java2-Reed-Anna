package edu.institution.actions.asn10;

import java.util.Objects;

import edu.institution.actions.asn10.UndoAction.MostRecentAction;

public class LinkedInAction {
	// TODO Anna, consider creating a new class to hold the undo-able data and place that class in your stack.
			// Example:
			// public class LinkedInAction {
			//   private MostRecentAction action;
			//   private Object data; // could hold the LinkedInUser or the Skillset (string)
			//   ... provide getter/setters, equals, and hashCode methods.
			// }
			// ...
			// UndoAction.history.push(new LinkedInAction(userRepository.retrieve(badFriend), MostRecentAction.DELETECON);
			// ...
			// then in the UndoAction, you would pop the stack, which would return the LinkedInAction class. You could then
			// call the getter methods to get the data needed to undo the action.
	private MostRecentAction action;
	private Object data;
	
	public LinkedInAction(MostRecentAction action, Object data) {
		super();
		this.action = action;
		this.data = data;
	}
	public MostRecentAction getAction() {
		return action;
	}
	public void setAction(MostRecentAction action) {
		this.action = action;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public int hashCode() {
		return Objects.hash(action, data);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedInAction other = (LinkedInAction) obj;
		return action == other.action && Objects.equals(data, other.data);
	}
	@Override
	public String toString() {
		return "LinkedInAction [action=" + action + ", data=" + data + "]";
	}
	
	
	
	
	
}
