import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
	
	private int noOfCluster, vectorDimension;
	private List<float[]> points = new ArrayList<float[]>();
	
	private ArrayList<float[]> newClusterMiddlePoints = new ArrayList<float[]>();
	private ArrayList<float[]> previousClusterMiddlePoints = new ArrayList<float[]>();
	private ArrayList<float[]> sumInsideIndividualCluster = new ArrayList<float[]>();
	
	int [] count;
	
	public KMeans(int noOfCluster, List<float[]> vectors) {
		this.noOfCluster = noOfCluster;
		this.points = vectors;
		this.vectorDimension = this.points.get(0).length;
	}
	
	private void initSum() {
		float [] temp = new float[this.vectorDimension];
		for(int i=0;i<this.noOfCluster;i++) {
			this.sumInsideIndividualCluster.add(temp);
		}
	}
	
	private void initNew() {
		float [] temp = new float[this.vectorDimension];
		for(int i=0;i<this.noOfCluster;i++) {
			this.newClusterMiddlePoints.add(temp);
		}
	}
	
	
	private void iniPre() {
		float [] temp = new float[this.vectorDimension];
		for(int i=0;i<this.noOfCluster;i++) {
			this.previousClusterMiddlePoints.add(temp);
		}
	}
	
	private void initializeArraylist() {
		
		initNew();
		iniPre();
		initSum();
	}
	
	private void initializeClusterMiddlePoints() {
		Random rand = new Random();
		
		for(int i=0;i<this.noOfCluster;i++) {
			int r = rand.nextInt(this.points.size());
			//System.out.println(r);
			this.newClusterMiddlePoints.add(this.points.get(r));
		}
	}
	
	private double calculateDistance(float[] point1,float[] point2) {
		double distance = 0;
		
		for(int i=0;i<point1.length;i++) {
			distance+=(point1[i]-point2[i])*(point1[i]-point2[i]);
		}
		
		return Math.sqrt(distance);
	}
	
	private int getNearestClusterIndex(float[] point) {
		int nearestIndex = 0;
		double minDistance = calculateDistance(point, newClusterMiddlePoints.get(0));
		
		for(int i=1;i<this.noOfCluster;i++) {
			double dist = calculateDistance(point, newClusterMiddlePoints.get(i));
			
			if(dist < minDistance) {
				minDistance = dist;
				nearestIndex = i;
			}
		}
		return nearestIndex;
	}
	
	public void clustering() {
		
		initializeArraylist();
		initializeClusterMiddlePoints();
		
		for(int i=0;i<noOfCluster;i++) {
			for(int j=0;j<vectorDimension;j++)
				System.out.println(newClusterMiddlePoints.get(i)[j]);
			System.out.println();
			System.out.println();
		}
		
		while(true) {
			initSum();
			count = new int [noOfCluster];
			
			this.previousClusterMiddlePoints = this.newClusterMiddlePoints;
			
			for(float[] point : this.points) {
				int nearestClusterIndex = getNearestClusterIndex(point);
				float [] tempArray=this.sumInsideIndividualCluster.get(nearestClusterIndex);
				for(int i=0;i<point.length;i++)
					tempArray[i]+=point[i];
				this.sumInsideIndividualCluster.set(nearestClusterIndex, tempArray);
				count[nearestClusterIndex]++;
			}
			
			for(int i=0;i<noOfCluster;i++) {
				if(count[i]!=0) {
					float [] tempArray = new float [this.vectorDimension];
					for(int j=0;j<this.vectorDimension;j++)
						tempArray[j]=this.sumInsideIndividualCluster.get(i)[j]/count[i];
					this.newClusterMiddlePoints.set(i, tempArray);
				}
			}
			
			boolean flag = true;
			
			for(int i=0; i<noOfCluster;i++) {
				if(calculateDistance(this.previousClusterMiddlePoints.get(i), this.newClusterMiddlePoints.get(i))>0.000000001) {
					flag=false;
					break;
				}
			}
			
			if(flag) break;
		}
		
		for(float [] point : this.points) {
			System.out.println("cluster : "+Integer.toString(getNearestClusterIndex(point)));
		}
	}

}
