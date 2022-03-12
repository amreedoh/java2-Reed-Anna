package edu.institution.midterm;

import org.junit.Test;

import edu.institution.midterm.PartManagerImpl;
import edu.institution.midterm.PartManager;

public class PartManagerTest {

	@Test
	public void UploadFileTest() {
		
		PartManagerImpl PMI = new PartManagerImpl();
		
		int importNumber = PMI.importPartStore("C:\\Users\\amree\\Desktop\\Java II\\Midterm\\bom.json.zip\\bom.json"); //file path
	}
}
