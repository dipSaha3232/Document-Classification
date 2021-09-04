public class VarienceTest {
	
	private int noOfCluster;
	private int[] documentCluster;
	private int[] documentCountInsideCluster;
	
	public VarienceTest(int noOfCluster, int [] documentCluster) {
		this.noOfCluster = noOfCluster;
		this.documentCluster = documentCluster;
		this.documentCountInsideCluster = new int [this.noOfCluster];
	}
	
	private void countDocumentInsideCluster() {
		for(int i=0;i<documentCluster.length;i++) 
			documentCountInsideCluster[documentCluster[i]]++;
	}
	
	private double getAverage() {
		return (double)((double)documentCluster.length/(double)noOfCluster);
	}
	
	private double calculateVarience() {
		countDocumentInsideCluster();
		double average = getAverage();
		double varience = 0;
		
		for(int i=0;i<noOfCluster;i++)
			varience+=((documentCountInsideCluster[i]-average)*(documentCountInsideCluster[i]-average));
		
		return varience/noOfCluster;
	}
	
	public double getVarience() {
		return calculateVarience();
	}

}
