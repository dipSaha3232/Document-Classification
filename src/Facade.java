import java.util.ArrayList;
import java.util.List;

public class Facade {
	
	private DocumentCollection documentCollection=new DocumentCollection();
	
	public void buildDocumentList(Document document) {
		documentCollection.addToList(document);
	}
	
	private List<float[]> vectors = new ArrayList<float[]>();
	
	public float[][] getAllDocumentVectors() {
		TF_IDF_Generator tf_IDF_Generator = new TF_IDF_Generator(this.documentCollection);
		
		for(Document document : this.documentCollection.getDocumentList()) {
			VectorGenerator vectorGenerator = new VectorGenerator(tf_IDF_Generator,this.documentCollection);

			float [] vector = vectorGenerator.doc2vec(document);
			vectors.add(vector);
		}
		
		float[][] temp = new float[vectors.size()][];
		
		for(int i=0;i<vectors.size();i++) {
			temp[i]=vectors.get(i);
		}
		
		return temp;
	}

}
