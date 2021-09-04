import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KmeansCluster {
	
	private int noOfCluster=3, vectorDimension;
	private float [][] points;
	
	private float [][] newClusterMiddlePoints, previousClusterMiddlePoints, sumInsideIndividualCluster;

	int [] count;
	int [] documentCluster;
	
	public KmeansCluster(int noOfCluster, float[][] vectors) {
		this.noOfCluster = noOfCluster;
		this.points = vectors;
		this.vectorDimension = this.points[0].length;
		this.documentCluster = new int[this.points.length];
	}
	
	private void initSum() {
		this.sumInsideIndividualCluster = new float [this.noOfCluster][this.vectorDimension];
	}
	
	private void initNew() {
		this.newClusterMiddlePoints = new float [this.noOfCluster][this.vectorDimension];
	}
	
	
	private void iniPre() {
		this.previousClusterMiddlePoints = new float [this.noOfCluster][this.vectorDimension];
	}
	
	private void initializeArraylist() {
		
		initNew();
		iniPre();
		initSum();
	}
	
	private ArrayList<Integer> generateRandom() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<this.points.length;i++)
			list.add(i);
		
		Collections.shuffle(list);
		
		return list;
	}
	
	private void initializeClusterMiddlePoints() {
		ArrayList<Integer> rands = generateRandom();
		
		for(int i=0;i<this.noOfCluster;i++) {
			int r = rands.get(i);
			System.out.println("rand "+Integer.toString(r));
			this.newClusterMiddlePoints[i]=this.points[r];
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
		double minDistance = calculateDistance(point, newClusterMiddlePoints[0]);
		
		for(int i=1;i<this.noOfCluster;i++) {
			double dist = calculateDistance(point, newClusterMiddlePoints[i]);
			
			if(dist < minDistance) {
				minDistance = dist;
				nearestIndex = i;
			}
		}
		return nearestIndex;
	}
	
	public int[] clustering() {
		initializeArraylist();
		initializeClusterMiddlePoints();
		
		while(true) {
			initSum();
			count = new int [noOfCluster];
			
			this.previousClusterMiddlePoints = this.newClusterMiddlePoints;
			
			for(float[] point : this.points) {
				int nearestClusterIndex = getNearestClusterIndex(point);
				for(int i=0;i<point.length;i++)
					this.sumInsideIndividualCluster[nearestClusterIndex][i]+=point[i];
				count[nearestClusterIndex]++;
			}
			
			for(int i=0;i<noOfCluster;i++) {
				if(count[i]!=0) {
					for(int j=0;j<this.vectorDimension;j++)
						this.newClusterMiddlePoints[i][j]=this.sumInsideIndividualCluster[i][j]/count[i];
				}
			}
			
			boolean flag = true;
			
			for(int i=0; i<noOfCluster;i++) {
				if(calculateDistance(this.previousClusterMiddlePoints[i], this.newClusterMiddlePoints[i])>0.000000001) {
					flag=false;
					break;
				}
			}
			
			if(flag) break;
		}
		for(int i=0;i<points.length;i++) {
			documentCluster[i] = getNearestClusterIndex(points[i]);
		}
		
		return this.documentCluster;
	}
}
