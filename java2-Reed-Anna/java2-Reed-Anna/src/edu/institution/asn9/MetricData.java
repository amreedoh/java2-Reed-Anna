package edu.institution.asn9;

import java.util.Objects;

public class MetricData {

	private SortAlgorithm sortAlgorithm;
	private TimeComplexity timeComplexity;
	private long executionTime;
	
	public MetricData(SortAlgorithm sortAlgorithm) {
		super();
		this.sortAlgorithm = sortAlgorithm;
	}

	public TimeComplexity getTimeComplexity() {
		return timeComplexity;
	}

	public void setTimeComplexity(TimeComplexity timeComplexity) {
		this.timeComplexity = timeComplexity;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sortAlgorithm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricData other = (MetricData) obj;
		return sortAlgorithm == other.sortAlgorithm;
	}

	@Override
	public String toString() {
		return "MetricData [sortAlgorithm=" + sortAlgorithm + ", timeComplexity=" + timeComplexity + ", executionTime="
				+ executionTime + "]";
	}
	
	
	
}
