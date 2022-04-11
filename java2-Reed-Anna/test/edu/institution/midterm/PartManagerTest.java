package edu.institution.midterm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.midterm.PartManagerImpl;
import edu.institution.midterm.PartManager;

public class PartManagerTest {

	private static final String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static final String FILE_NAME = "bom.json";
	/*@Test
	public void UploadFileTest() {
		
		PartManagerImpl PMI = new PartManagerImpl();
		
		int importNumber = PMI.importPartStore("/java2-Reed-Anna/MidtermTestData/bom.json"); //file path
		//I have tried puting the file on the desktop and copping the file path
		//I have also put it in the same location that the LinkedInUser file is stored
		//I have just added it to the project itseld if another folder
		//All file paths have been coppied and pasted and I have tried adding extra "/" and switching to "\" just in case
	}
	*/
	
	// TODO Anna, I added this unit test for you. It imports your parts and assert that the number of parts imported was 79.
		//   It expects you to place your bom.json file the Java2 folder off of your home directly (the same location as the 
		//   LinkedInUsers.dat file).
		@Test
		public void testImportPartStore() {
			PartManager sut = new PartManagerImpl();
			try {
				int count = sut.importPartStore(PATH + FILE_NAME);
				Assert.assertEquals(79, count);
			} catch (Throwable exception) {
				Assert.fail(exception.getMessage());
			}
		}
	
	
	
	
	
	
	
	
	@Test
	public void newParts() {
		
		HashMap<String, Part> partList = new HashMap<>();
		
		List<BomEntry> cBOM = new ArrayList<>();
		//"ASSEMBLY"  "COMPONENT"  "PURCHASE"
		Part E = new Part();
		E.setName("Part E");
		E.setPartNumber("p0001");
		E.setPartType("PURCHASE");
		E.setPrice((float) 2);
		partList.put(E.getPartNumber(), E);
		BomEntry eBOMEntry = new BomEntry();
		eBOMEntry.setPartNumber(E.getPartNumber());
		eBOMEntry.setQuantity(4);
		cBOM.add(eBOMEntry);
		
		Part D = new Part();
		D.setName("Part D");
		D.setPartNumber("p0002");
		D.setPartType("COMPONENT");
		D.setPrice((float) 1);
		partList.put(D.getPartNumber(), D);
		BomEntry dBOMEntry = new BomEntry();
		dBOMEntry.setPartNumber(D.getPartNumber());
		dBOMEntry.setQuantity(2);
		cBOM.add(dBOMEntry);
		
		Part C = new Part();
		C.setName("Part C");
		C.setPartType("COMPONENT");
		C.setPartNumber("c0001");
		C.setBillOfMaterial(cBOM);
		partList.put(C.getPartNumber(), C);
		
		Part B = new Part();
		B.setName("Part B");
		B.setPartNumber("p0004");
		B.setPartType("PURCHASE");
		B.setPrice((float) 9);
		partList.put(B.getPartNumber(), B);
		
		List<BomEntry> aBOM = new ArrayList<>();
		BomEntry cBOMEntry = new BomEntry();
		cBOMEntry.setPartNumber(C.getPartNumber());
		cBOMEntry.setQuantity(4);
		aBOM.add(cBOMEntry);
		BomEntry bBOMEntry = new BomEntry();
		bBOMEntry.setPartNumber(B.getPartNumber());
		bBOMEntry.setQuantity(1);
		aBOM.add(bBOMEntry);
		aBOM.add(bBOMEntry); //repeat test
		
		Part A = new Part();
		A.setName("Part A");
		A.setPartNumber("a0001");
		A.setPartType("ASSEMBLY");
		A.setBillOfMaterial(aBOM);
		partList.put(A.getPartNumber(), A);
		partList.put(A.getPartNumber(), A); //Repeat test
		
		List<BomEntry> xBOM = new ArrayList<>();
		xBOM.add(eBOMEntry);
		xBOM.add(bBOMEntry);
		
		Part X = new Part();
		X.setName("Part X");
		X.setPartNumber("a0002");
		X.setPartType("ASSEMBLY");
		X.setBillOfMaterial(xBOM);  
		partList.put(X.getPartNumber(), X);
		
		PartManagerImpl PMI = new PartManagerImpl();
		PMI.setParts(partList);

		System.out.println(PMI.getFinalAssemblies());
		System.out.println(PMI.getPurchasePartsByPrice());
		
		System.out.println(PMI.retrievePart(A.getPartNumber()));
		System.out.println(PMI.retrievePart(B.getPartNumber()));
		System.out.println(PMI.retrievePart(C.getPartNumber()));
		System.out.println(PMI.retrievePart(D.getPartNumber()));
		System.out.println(PMI.retrievePart(E.getPartNumber())); 
		System.out.println(PMI.retrievePart(X.getPartNumber()));
		System.out.println(PMI.retrievePart("nope")); //test null
		
		PMI.costPart(A.getPartNumber());
		PMI.costPart(X.getPartNumber());
		
		System.out.println(PMI.retrievePart(A.getPartNumber()));
		System.out.println(PMI.retrievePart(X.getPartNumber()));
		
	}
} 
