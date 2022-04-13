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
	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
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
		D.setPrice((float) -1);//covereage test
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
		
		List<BomEntry> yBOM = new ArrayList<>();
		yBOM.add(cBOMEntry);
		
		Part A = new Part();
		A.setName("Part A");
		A.setPartNumber("a0001");
		A.setPartType("ASSEMBLY");
		A.setBillOfMaterial(aBOM);
		B.setBillOfMaterial(aBOM);//coverage test
		partList.put(A.getPartNumber(), A);
		partList.put(A.getPartNumber(), A); //Repeat test
		
		List<BomEntry> xBOM = new ArrayList<>();
		xBOM.add(eBOMEntry);
		xBOM.add(bBOMEntry);
		
		Part X = new Part();
		X.setName("Part X");
		X.setPartNumber("a0002");
		X.setPartType("HELLO");//test for coverage
		X.setPartType("ASSEMBLY");
		X.setBillOfMaterial(xBOM);  
		partList.put(X.getPartNumber(), X);
		
		List<BomEntry> zBOM = new ArrayList<>();
		BomEntry aBOMEntry = new BomEntry();
		aBOMEntry.setPartNumber(A.getPartNumber());
		aBOMEntry.setQuantity(4);
		BomEntry xBOMEntry = new BomEntry();
		xBOMEntry.setPartNumber(X.getPartNumber());
		xBOMEntry.setQuantity(4);
		
		Part Z = new Part();
		Z.setName("Part Z");
		Z.setPartNumber("a0003");
		Z.setPartType("ASSEMBLY");
		Z.setBillOfMaterial(zBOM);
		partList.put(Z.getPartNumber(), Z);
		
		Part Y = new Part();
		Y.setName("Part Y");
		Y.setPartNumber("a0004");
		Y.setPartType("ASSEMBLY");
		Y.setBillOfMaterial(zBOM);
		partList.put(Y.getPartNumber(), Y);
		
		PartManagerImpl PMI = new PartManagerImpl();
		PMI.setParts(partList);

		//System.out.println(PMI.getFinalAssemblies());
		//System.out.println(PMI.getPurchasePartsByPrice());
		
		//System.out.println(PMI.retrievePart(A.getPartNumber()));
		Assert.assertEquals("a0001", PMI.retrievePart(A.getPartNumber()).getPartNumber());
		Assert.assertEquals(0.0, PMI.retrievePart(A.getPartNumber()).getPrice(), 0.001);
		Assert.assertEquals("ASSEMBLY", PMI.retrievePart(A.getPartNumber()).getPartType());
		
		//System.out.println(PMI.retrievePart(B.getPartNumber()));
		Assert.assertEquals("p0004", PMI.retrievePart(B.getPartNumber()).getPartNumber());
		double BPrice = PMI.retrievePart(B.getPartNumber()).getPrice();
		Assert.assertEquals(9.0, BPrice, 0.001);
		Assert.assertEquals("PURCHASE", PMI.retrievePart(B.getPartNumber()).getPartType());
		
		//System.out.println(PMI.retrievePart(C.getPartNumber()));
		Assert.assertEquals("c0001", PMI.retrievePart(C.getPartNumber()).getPartNumber());
		Assert.assertEquals(0.0, PMI.retrievePart(C.getPartNumber()).getPrice(), 0.001);
		Assert.assertEquals("COMPONENT", PMI.retrievePart(C.getPartNumber()).getPartType());
		
		//System.out.println(PMI.retrievePart(D.getPartNumber()));
		//System.out.println(PMI.retrievePart(E.getPartNumber())); 
		//System.out.println(PMI.retrievePart(X.getPartNumber()));

		
		
		PMI.costPart(A.getPartNumber());
		Assert.assertEquals(58.0, PMI.retrievePart(A.getPartNumber()).getPrice(), 0.001);
		PMI.costPart(X.getPartNumber());
		Assert.assertEquals(17.0, PMI.retrievePart(X.getPartNumber()).getPrice(), 0.001);
		
		Assert.assertTrue(!PMI.getFinalAssemblies().isEmpty());
		Assert.assertEquals(4, PMI.getFinalAssemblies().size());
		Assert.assertEquals(0, PMI.getPurchasePartsByPrice().indexOf(B));
		//System.out.println(PMI.retrievePart(A.getPartNumber()));
		//System.out.println(PMI.retrievePart(X.getPartNumber()));
		
		Assert.assertTrue(PMI.retrievePart(A.getPartNumber()).equals(A));
		Assert.assertTrue(!PMI.retrievePart(A.getPartNumber()).equals(B));
		Assert.assertTrue(!PMI.retrievePart(A.getPartNumber()).equals(1));
		Assert.assertEquals(("Part [partNumber=" + A.getPartNumber() + ", name=" + A.getName() + ", partType=" + A.getPartType() + ", price=" + A.getPrice()
				+ ", billOfMaterial=" + A.getBillOfMaterial() + "]"), A.toString());
		Assert.assertTrue(!cBOMEntry.equals(bBOMEntry));
		Assert.assertTrue(cBOMEntry.equals(cBOMEntry));
		Assert.assertTrue(!cBOMEntry.equals("Hi"));
		Assert.assertTrue(!cBOMEntry.equals(null));
		
		
		
	}
} 
