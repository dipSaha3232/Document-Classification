import java.util.List;

public class Document {
	
	private String content;
	private String[] words;
	
	public Document(String content)
	{
		this.content=content;
		this.words = this.removePunctuation(this.content);
	}
	
	private String[] removePunctuation(String content)
	{
		String[] words = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		return words;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public String[] getWords()
	{
		return this.words;
	}	

}
