package edu.institution.midterm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



import edu.institution.asn2.LinkedInUser;


public class PartManagerImpl implements PartManager {

	HashMap<String, Part> parts = new HashMap<>(); //HashMap<Value = String, Key = Part>
	
	public HashMap<String, Part> getParts() {
		return parts;
	}

	public void setParts(HashMap<String, Part> parts) {
		this.parts = parts; 
	}

	@Override
	public int importPartStore(String filePath) {
		
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("File not found");
			return -1;
		}
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder lines = new StringBuilder();
		
		while (scanner.hasNext()) {	

			lines.append(scanner.nextLine());
			lines.append("\n");
		}
		
		Gson gson = new Gson();
		// TODO Anna, you're close here. Since the lines StringBuilder already is a String-representation of the JSON data
		//  which was read from the supplied file, all that need to do is convert the StringBuilder to a String and pass that
		//  to the gson.fromJson method.
		//myJsonString = gson.toJson(lines);
		String myJsonString = lines.toString();
		Part[] parts = gson.fromJson(myJsonString,  Part[].class);
		
		// TODO Now...all you need to do is loop through your Parts array and add each instance to your parts map. The key to 
		//   the map is the part number and the value is the Part instance.
		for(Part part: parts) {
			this.parts.put(part.getPartNumber(), part);
		}
		// TODO Afterwards, return the size of the parts map.


	return this.parts.size();
	}

	@Override
	public Part costPart(String partNumber) {
		/*
		This method computes the cost of manufacturing the part associated with the supplied part number. Along
		with computing the manufacturing cost of the supplied part, this method also computes the cost of each sub
		component part, and their sub component parts, until all parts under the supplied part have been costed
		
		
		1. look up associated part
		2. check if there are more BOMEntries associated with the part (Recursion)
		3. multiply price by quantity
		4. set tempPrice to largerPart price
		5. return the larger part
		*/
		
		Part largerPart = retrievePart(partNumber);
		float tempPrice = 0;
		
		//two if statements maybe redundant but there is a chance that it might pop up
		if (largerPart.getPrice() <= 0) { //will check to see if this subpart already has a price, if it does it is just returned
			if (!largerPart.getBillOfMaterial().isEmpty()) { //will check to see if the BOM is empty if it is not it will iterate through it
				for(int loop = 0; loop < largerPart.getBillOfMaterial().size(); loop++) {
					//List<BomEntry> tempBOM = largerPart.getBillOfMaterial();
					//String tempPartNo = tempBOM.get(loop).getPartNumber();
					//Part smallerPart = costPart(tempPartNo);
					Part smallerPart = costPart(largerPart.getBillOfMaterial().get(loop).getPartNumber()); 
						//finds the smallerPart and will set the smallerPart's price
					tempPrice = tempPrice+ (smallerPart.getPrice() * largerPart.getBillOfMaterial().get(loop).getQuantity()); 
						//will calculate the price of the largerPart by adding the price of the smallerPart times quantity
				}
				largerPart.setPrice(tempPrice); //once all BOM entries have been gone through the tempPrice is then set as the actual price of the largerPart
		
			}
		}	
		
		return largerPart;
	}

	@Override
	public Part retrievePart(String partNumber) {
		/*
		Return the Part instance from the part store that is related to the supplied part number.
		Return null if no Part instance is found for the supplied part number
		*/
		if (parts.containsKey(partNumber)) {
			return parts.get(partNumber);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Part> getFinalAssemblies() {
		/*Scan all parts imported in the part store and return only the final assembly parts. Final assembly parts have a
		part type of “ASSEMBLY”. The returned list of final assembly parts should be sorted in ascending order by
		their part number.
		
		https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
		
	    hm.entrySet() 
	    	is used to retrieve all the key-value pairs called Map.Entries and stores internally into a set.
	    hm.entrySet().iterator() 
	    	returns an iterator that acts as a cursor and points at the first element of the set and moves on till the end.
	    hmIterator.hasNext() 
	    	checks for the next element in the set and returns a boolean
	    hmIterator.next() 
	    	returns the next element(Map.Entry) from the set.
	    mapElement.getKey() 
	    	returns the key of the associated Map.Entry
	    mapElement.getValue() 
	    	return the value of the associated Map.Entry
    
	    HashMap<String, Integer> hm = new HashMap<String, Integer>();
	    Iterator hmIterator = hm.entrySet().iterator();
	    
	    while (hmIterator.hasNext()) {
 
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            int marks = ((int)mapElement.getValue() + 10);
 
            // Printing mark corresponding to string entries
            System.out.println(mapElement.getKey() + " : " + marks);
        }
		*/

		List<Part> assemble = new ArrayList<>();
		Iterator partsIterator = parts.entrySet().iterator();
		
		while (partsIterator.hasNext()) {
			Entry partsMap = (Map.Entry) partsIterator.next();
			Part temp = (Part) partsMap.getValue();
			if (temp.getPartType().equals("ASSEMBLY")) {
				assemble.add(temp);
			}
		}
		
		Collections.sort(assemble);//Should return alphabetized by Part number
		return assemble;
	} 

	@Override
	public List<Part> getPurchasePartsByPrice() {
		/*Scan all parts imported in the part store and return only the purchased parts. Purchased parts have a part
			type of “PURCHASE”. The returned list of purchased parts should be sorted in descending order by their price
			(highest price to lowest price).
		 */
		List<Part> purchase = new ArrayList<>();
		Iterator partsIterator = parts.entrySet().iterator();
		
		while (partsIterator.hasNext()) {
			Entry partsMap = (Map.Entry) partsIterator.next();
			Part temp = (Part) partsMap.getValue();
			if (temp.getPartType().equals("PURCHASE")) {
				purchase.add(temp);
			}
		}
		
		Collections.sort(purchase, Part::compareByPrice);
		Collections.reverse(purchase); //should return high to low
		return purchase;
	}
	
	
}
