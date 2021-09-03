public class VectorGenerator {
	
	private float[] vectorSpace;
	
	private TF_IDF_Generator tf_IDF_Generator;
	private DocumentCollection documentCollection;
	
	public VectorGenerator(TF_IDF_Generator tf_IDF_Generator, DocumentCollection documentCollection) {
		this.tf_IDF_Generator = tf_IDF_Generator;
		this.documentCollection = documentCollection;
		this.vectorSpace = new float [documentCollection.getVocabulary().size()];
	}
	
	public float[] doc2vec(Document document)
	{
		for(String word : document.getWords()) {
			vectorSpace[getIndex(word)]=tf_IDF_Generator.findTFIDF(document.getWords(), word)*100;
		}
		return vectorSpace;
	}
	
	private int getIndex(String word) {
		int index = 0;
		for(String voc : documentCollection.getVocabulary()) {
			if(voc.equals(word))
				break;
			index++;
		}
		
		return index;
	}
}
