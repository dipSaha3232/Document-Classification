public class DocumentCluster {
	
	Facade facade = new Facade();
	float [][] vectors;
	int [] documentCluster,bestDocumentCluster;
	double minVarience;
	
	private int noOfCluster=3;
	
	public void createNewDocument(String text) {
		Document document = new Document(text);
		facade.buildDocumentList(document);
	}
	
	public void setNoOfCluster(int noOfCluster) {
		this.noOfCluster = noOfCluster;
	}
	
	private void getAllDocumentVectors() {
		vectors = facade.getAllDocumentVectors();
	}

	public int [] cluster() {
		getAllDocumentVectors();
		KmeansCluster kmeansCluster = new KmeansCluster(noOfCluster, vectors);
		VarienceTest varienceTest;
		
		minVarience = 1000000000;
		
		for(int i=0;i<3;i++) {
			documentCluster=kmeansCluster.clustering();
			varienceTest=new VarienceTest(noOfCluster,documentCluster);
			if(varienceTest.getVarience()<minVarience) {
				minVarience = varienceTest.getVarience();
				bestDocumentCluster = documentCluster;
			}
		}
		return bestDocumentCluster;
	}
	
	public double getVarience() {
		return this.minVarience;
	}

}
