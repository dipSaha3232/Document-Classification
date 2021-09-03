import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) {
		
		Document document1 = new Document("Dip is a good student. Oh!!!!!!! nice");
		Document document2 = new Document("A good student is an asset of a country");
		Document document3 = new Document("Mango is a fruit. I love mango");
		Document document4 = new Document("I love mango as well as jackfruit");
		Document document5 = new Document("I love mango as well as jackfruit");
		Document document6 = new Document("I love mango as well as jackfruit");
		Document document7 = new Document("I love mango as well as jackfruit");
		
		Facade facade = new Facade();
		
		facade.buildDocumentList(document1);
		facade.buildDocumentList(document2);
		facade.buildDocumentList(document3);
		facade.buildDocumentList(document4);
		facade.buildDocumentList(document5);
		facade.buildDocumentList(document6);
		facade.buildDocumentList(document7);
		
		float [][] vectors;
		
		vectors = facade.getAllDocumentVectors();

		
		KmeansCluster kmeansCluster = new KmeansCluster(3, vectors);
		
		kmeansCluster.clustering();
		
	}

}
