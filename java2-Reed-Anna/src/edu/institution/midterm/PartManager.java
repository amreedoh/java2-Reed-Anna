package edu.institution.midterm;

import java.util.List;

public interface PartManager {
	
	int importPartStore(String filePath);
	
	Part costPart(String partNumber);
	
	Part retrievePart (String partNumber);
	
	List<Part> getFinalAssemblies();
	
	List<Part> getPurchasePartsByPrice();
}
