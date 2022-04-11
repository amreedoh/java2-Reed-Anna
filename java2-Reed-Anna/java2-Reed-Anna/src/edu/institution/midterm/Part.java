package edu.institution.midterm;

import java.util.List;
import java.util.Objects;

public class Part implements Comparable<Part> {
	private String partNumber;
	private String name;
	//must be ASSEMBLY, PURCHASE or COMPONENT
	private String partType;
	private float price;
	private List<BomEntry> billOfMaterial;
	
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartType() {
		return partType;
	}
	
	//Valid values are: "ASSEMBLY", "PURCHASE", or "COMPONENT"
	// "ASSEMBLY" parts are the final assemblies that are sold to the customers.
	// "COMPONENT" parts are the sub component parts that make up the final assemblies.
	// "PURCHASE" parts are the external components purchased from a vendor.
		//Purchased parts cannot have sub parts as they are bought from somewhere else as is
	public void setPartType(String partType) {
		if ((partType.equalsIgnoreCase("ASSEMBLY")) ||(partType.equalsIgnoreCase("PURCHASE")) ||(partType.equalsIgnoreCase("COMPONENT")) ) {
			this.partType = partType;
		}
		else {
			System.out.println(this.getName() + ": Part Type must be ASSEMBLY, PURCHASE, or COMPONENT");
		}
		
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		if (price < 0) {
			price = 0;
		}
		this.price = price;
	}
	public List<BomEntry> getBillOfMaterial() {
		return billOfMaterial;
	}
	public void setBillOfMaterial(List<BomEntry> billOfMaterial) {
		if (this.partType.equalsIgnoreCase("ASSEMBLY") || this.partType.equalsIgnoreCase("COMPONENT")) {
			this.billOfMaterial = billOfMaterial;
		}
		else {
			System.out.println(this.getName() + ": Purchased part cannot have a BOM");
		}
	}
	
	@Override
	public String toString() {
		return "Part [partNumber=" + partNumber + ", name=" + name + ", partType=" + partType + ", price=" + price
				+ ", billOfMaterial=" + billOfMaterial + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(partNumber);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		return Objects.equals(partNumber, other.partNumber);
	}
	
	/*i misunderstood requirements but will keep here for now
	public int compareByType(Part compare) { //compares by type
		if (this.getPartType().isEmpty()) {
			return -1;
		}
		if (compare.getPartType().isEmpty()) {
			return 1;
		}
		
		return this.getPartType().compareToIgnoreCase(compare.getPartType());
	}*/
	
	public int compareByPrice(Part compare) { //compares by price
		if (this.getPrice() <= 0) {
			return -1;
		}
		if (compare.getPrice() <= 0) {
			return 1;
		}
		if (this.getPrice() > compare.getPrice()) {
			return 1;
		}
		else if (this.getPrice() < compare.getPrice()){
			return -1;
		}
		else
			return 0;

	}
	@Override
	public int compareTo(Part compare) { //compares by number
		if (this.getPartNumber().isEmpty()) {
			return -1;
		}
		if (compare.getPartNumber().isEmpty()) {
			return 1;
		}
		
		return this.getPartNumber().compareToIgnoreCase(compare.getPartNumber());
	}
}
