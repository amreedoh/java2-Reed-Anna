package edu.institution.actions.asn5;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class Utilities {

	public <T> void removeDuplicates(List<T> items) {
	
		if (items.size() == 0) {//no items in the list
			System.out.println("List has no items.");
			return;
		}
		if (items.size() == 1) {//list with one item
			System.out.println("This list only contains one item.");
			return;
		}
		
		int removed = 0;
		for (int outer = 0; outer < (items.size() - 1); outer++) {
			for (int inner = 1 + outer; inner < items.size(); inner++) {
				if (items.get(outer).equals(items.get(inner))){
					items.remove(inner);
					inner--; //will make sure the next loop through will hit the next item in the list instead of skipping it
					removed ++;
				}
			}
		}
		System.out.println(removed + " duplicates removed from list.");
		return;
	}
	
	public <E> E linearSearch(List<E> list, E key){
		
		if (list.size() == 0) {//no items in the list
			System.out.println("List has no items.");
			return null;
		}
		
		for (int loop = 0; loop < list.size(); loop++) {
			if (list.get(loop).equals(key)) {
				return list.get(loop);
			}
		}                            
		return null;
	}
	
}
