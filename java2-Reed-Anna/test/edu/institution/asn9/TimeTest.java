package edu.institution.asn9;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class TimeTest {

	private static final String EXPECTED = null;
	private static String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator + "asn9-numbers.txt";
	
	@Test public void testing() {
		
		SortingAlgorithmMetrics SAM =  new SortingAlgorithmMetrics();
		List<MetricData> sortInfo = SAM.retrieveMetrics(PATH);
		System.out.print(sortInfo); 
		
	} 
}
