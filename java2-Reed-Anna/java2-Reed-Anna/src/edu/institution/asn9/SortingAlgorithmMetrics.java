package edu.institution.asn9;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingAlgorithmMetrics {


	public List<MetricData> retrieveMetrics(String filePath) {
		
		List<Integer> original = new ArrayList<>();
		//original.add(reader.nextInt());

		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("file not found");
			return null;
		}
		
		Scanner scanner = null;
	    try {
	    	scanner = new Scanner(file);
	    	while (scanner.hasNext()) {
	    		original.add(scanner.nextInt());
	    	}
	    } catch (FileNotFoundException e) {
	      System.out.println("File Error");
	      e.printStackTrace();
	    } finally {
	    	if (scanner != null) {
	    		scanner.close();
	    	}
	    }

	    //shuffle and copy array
	    Integer[] template = {};
	    Collections.shuffle(original);
	    Integer[] bubbleA = original.toArray(template);
	    Collections.shuffle(original);
		Integer[] mergeA = original.toArray(template);
		Collections.shuffle(original);
		Integer[] quickA = original.toArray(template);
		Collections.shuffle(original);
		Integer[] heapA = original.toArray(template);
		Collections.shuffle(original);
		Integer[] insertionA = original.toArray(template);

		List<MetricData> sortInfo = new ArrayList<>();
		
		
		/*
		LINEAR("Linear O(n)"),

		CONSTANT("Constant O(1)"),

		QUADRATIC("Quadratic O(n2)"),

		LOGARITHMIC("Logarithmic O(n log n)"),

		CUBIC("Cubic O(n3)"),

		EXPONENTIAL("Exponential O(2n)")
		*/
		
		/*
		 BUBBLE_SORT("Bubble Sort"),

		INSERTION_SORT("Insertion Sort"),

		MERGE_SORT("Merge Sort"),
		
		QUICK_SORT("Quick Sort"),
		
		HEAP_SORT("Heap Sort")
		 */
		
		//sorting arrays
		//bubble
		LocalTime bStart = LocalTime.now();
		BubbleSort.bubbleSort(bubbleA);
		LocalTime bEnd = LocalTime.now();
		long bElapsedMS = Duration.between(bStart, bEnd).toMillis();
		MetricData sMD = new MetricData(SortAlgorithm.BUBBLE_SORT);
		sMD.setExecutionTime(bElapsedMS);
		sMD.setTimeComplexity(TimeComplexity.QUADRATIC);
		sortInfo.add(sMD);
		
		//merge
		LocalTime mStart = LocalTime.now();
		MergeSort.mergeSort(mergeA);
		LocalTime mEnd = LocalTime.now();
		long mElapsedMS = Duration.between(mStart, mEnd).toMillis();
		MetricData mMD = new MetricData(SortAlgorithm.MERGE_SORT);
		mMD.setExecutionTime(mElapsedMS);
		mMD.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		sortInfo.add(mMD);
		
		
		LocalTime qStart = LocalTime.now();
		QuickSort.quickSort(quickA);
		LocalTime qEnd = LocalTime.now();
		long qElapsedMS = Duration.between(qStart, qEnd).toMillis();
		MetricData qMD = new MetricData(SortAlgorithm.QUICK_SORT);
		qMD.setExecutionTime(qElapsedMS);
		qMD.setTimeComplexity(TimeComplexity.QUADRATIC);
		sortInfo.add(qMD);
		
		LocalTime hStart = LocalTime.now();
		HeapSort.heapSort(heapA);
		LocalTime hEnd = LocalTime.now();
		long hElapsedMS = Duration.between(hStart, hEnd).toMillis();
		MetricData hMD = new MetricData(SortAlgorithm.HEAP_SORT);
		hMD.setExecutionTime(hElapsedMS);
		hMD.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		sortInfo.add(hMD);
		
		LocalTime iStart = LocalTime.now();
		InsertionSort.insertionSort(insertionA);
		LocalTime iEnd = LocalTime.now();
		long iElapsedMS = Duration.between(iStart, iEnd).toMillis();
		MetricData iMD = new MetricData(SortAlgorithm.MERGE_SORT);
		iMD.setExecutionTime(iElapsedMS);
		iMD.setTimeComplexity(TimeComplexity.QUADRATIC);
		sortInfo.add(iMD);
		
		return sortInfo;
	}

}
