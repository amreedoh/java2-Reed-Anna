package edu.institution.asn5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;

public class UtilitiesTest {

	@Test
	public void stringTest() {
		String animal = "bird";
		
		List<String> animals = new ArrayList();
		animals.add("cat");
		animals.add("dog");
		animals.add("sheep");
		animals.add("cow");
		animals.add("dog");
		animals.add("penguin");
		animals.add("cat");
		animals.add("mouse");
		animals.add(animal);
		
		Utilities utilities = new Utilities();
		
		System.out.println(animals);
		utilities.removeDuplicates(animals);
		System.out.println(animals);
		System.out.println("Found: " + utilities.linearSearch(animals, animal));
		System.out.println("Found: " + utilities.linearSearch(animals, "sheep"));
		System.out.println("Found: " + utilities.linearSearch(animals, "rat"));
	}
	
	@Test
	public void intTest() {
		Integer highest = 1000;
		
		List<Integer> numbers = new ArrayList();
		numbers.add(6);
		numbers.add(89);
		numbers.add(50);
		numbers.add(0);
		numbers.add(highest);
		numbers.add(0);
		numbers.add(0);
		numbers.add(9);
		numbers.add(1000);
		
		Utilities utilities = new Utilities();
		
		System.out.println(numbers);
		utilities.removeDuplicates(numbers);
		System.out.println(numbers);
		System.out.println("Found: " + utilities.linearSearch(numbers, highest));
		System.out.println("Found: " + utilities.linearSearch(numbers, 0));
		System.out.println("Found: " + utilities.linearSearch(numbers, 70));
	}
	
	@Test
	public void userTest() {
		LinkedInUser IronMan = new LinkedInUser("IronMan", "TonyStark","S" );
		LinkedInUser CptAmerica = new LinkedInUser("CptAmerica", "SteveRogers","S" );
		LinkedInUser Hulk = new LinkedInUser("Hulk", "BruceBanner","S" );
		LinkedInUser BlkWidow = new LinkedInUser("BlkWidow", "NatashaRomanoff","S" );
		LinkedInUser Thor = new LinkedInUser("Thor", "ThorOdison","S" );
		LinkedInUser Hawkeye = new LinkedInUser("Hawkeye", "ClintBarton","S" );
		LinkedInUser ScarletWitch = new LinkedInUser("ScarletWitch", "WandaMaximoff","S" );
		LinkedInUser Thanos = new LinkedInUser("Thanos", "Rasin", "P");
		
		List<LinkedInUser> superheroes = new ArrayList();
		superheroes.add(IronMan);
		superheroes.add(CptAmerica);
		superheroes.add(Hulk);
		superheroes.add(BlkWidow);
		superheroes.add(Thor);
		superheroes.add(Hawkeye);
		superheroes.add(ScarletWitch);
		superheroes.add(new LinkedInUser("Spiderman","PeterParker","S"));
		superheroes.add(new LinkedInUser("BlkPanther","Tchalla","S"));
		superheroes.add(new LinkedInUser("Vision","Vision","S"));
		superheroes.add(Thor);
		superheroes.add(Hawkeye);
		superheroes.add(ScarletWitch);
		
		Utilities utilities = new Utilities();
		
		System.out.println(superheroes);
		utilities.removeDuplicates(superheroes);
		System.out.println(superheroes);
		System.out.println("Found: " + utilities.linearSearch(superheroes, IronMan));
		System.out.println("Found: " + utilities.linearSearch(superheroes, Thanos));
		System.out.println("Found: " + utilities.linearSearch(superheroes, new LinkedInUser("Spiderman","PeterParker","S")));
		
		List<LinkedInUser> retiredHeroes = new ArrayList();
		retiredHeroes.add(superheroes.get(0));
		utilities.removeDuplicates(retiredHeroes);
		System.out.println("Found: " + utilities.linearSearch(superheroes, IronMan));
		System.out.println("Found: " + utilities.linearSearch(superheroes, new LinkedInUser("Spiderman","PeterParker","S")));
		
		List<LinkedInUser> aliveBadGuys = new ArrayList();
		utilities.removeDuplicates(aliveBadGuys);
		System.out.println("Found: " + utilities.linearSearch(aliveBadGuys, IronMan));
		
		List<LinkedInUser> multiverse = new ArrayList();
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		multiverse.add(new LinkedInUser("Spiderman","PeterParker","S"));
		utilities.removeDuplicates(multiverse);
		System.out.println(multiverse);
		System.out.println("Found: " + utilities.linearSearch(multiverse, IronMan));
		
		
	}
}
