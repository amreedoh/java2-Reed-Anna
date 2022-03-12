package edu.institution.midterm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import edu.institution.asn2.LinkedInUser;


public class PartManagerImpl implements PartManager {

	HashMap<String, Part> parts = new HashMap<>(); //HashMap<Value = String, Key = Part>
	
	@Override
	public int importPartStore(String filePath) {
		File bomFile = new File(filePath);
		if(bomFile.exists()) {
			try(FileInputStream fis = new FileInputStream(bomFile);
					ObjectInputStream ois = new ObjectInputStream (fis)){
						String jsonData = (String) ois.readObject();
						Gson gson = new Gson();
						Part[] tempParts = gson.fromJson(jsonData, Part[].class);
						for (int loop = 0; loop < tempParts.length; loop++) {
							parts.put(tempParts[loop].getPartNumber(), tempParts[loop]);
						}
						return parts.size();
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}else {
			//makes sure path exists
			new File(filePath).mkdirs();
			File file = new File(filePath);
			try {
				file.createNewFile();
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

	return 0;
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
					Part smallerPart = costPart(largerPart.getBillOfMaterial().get(loop).getPartNumber()); //finds the smallerPart and will set the smallerPart's price
					tempPrice =+ (smallerPart.getPrice() * largerPart.getBillOfMaterial().get(loop).getQuantity()); //will calculate the price of the largerPart by adding the price of the smallerPart times quantity
				}
				largerPart.setPrice(tempPrice); //once all BOM entries have been gone through the tempPrice is then set as the actual price of the largerPart
			}else {
				largerPart.setPrice(0); //will set the price to 0 just in case there is a negative number in there for some reason
			}
		}
		
		return largerPart;
	}

	@Override
	public Part retrievePart(String partNumber) {
		/*
		Return the Part instance from the part store that is related to the supplied part number. Return null if no
		Part instance is found for the supplied part number
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
			if (temp.getPartType().equals("ASSEMBLY")) {
				purchase.add(temp);
			}
		}
		
		Collections.sort(purchase, Part::compareByPrice);
		Collections.reverse(purchase); //should return high to low
		return purchase;
	}
	
	
}
