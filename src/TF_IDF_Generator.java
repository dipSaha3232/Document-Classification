import java.util.Arrays;

public class TF_IDF_Generator {
	
	private DocumentCollection documentCollection;
	
	public TF_IDF_Generator(DocumentCollection documentCollection) {
		this.documentCollection = documentCollection;
	}

	private float countOccurance(String[] words, String term)
	{
		float count = 0;
		for (String word : words) {
			if (word.equals(term))
				count++;
		}
		
		return count;
	}
	
	private float findTF(String[] words, String term)
	{
		return (float)(countOccurance(words, term)/(float)words.length);
	}
	
	private float countDocument(String term)
	{
		float count = 0;
		
		for (Document document : documentCollection.getDocumentList()) {
			if (Arrays.asList(document.getWords()).contains(term))
				count++;
		}
		
		return count;
	}
	
	private float findIDF(String term)
	{
		return (float)Math.log((float)documentCollection.getDocumentList().size()/countDocument(term));
	}
	
	public float findTFIDF(String[] words, String term)
	{
		return findTF(words, term)*findIDF(term);
	}
}
