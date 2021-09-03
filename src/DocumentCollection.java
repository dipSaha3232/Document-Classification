import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DocumentCollection {
	
	private List<Document> documentList = new ArrayList<Document>();
	
	private Set<String> vocabulary = new HashSet<String>();
	
	public void addToVocabulary(String [] words) {
		for(String word : words)
			this.vocabulary.add(word);
	}
	
	public void addToList(Document document)
	{
		this.documentList.add(document);
		addToVocabulary(document.getWords());
	}
	
	public List<Document> getDocumentList(){
		return this.documentList;
	}
	
	public Set<String> getVocabulary(){
		return this.vocabulary;
	}

}
