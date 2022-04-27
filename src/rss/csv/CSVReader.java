package rss.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader{
	private String filename;
	private static CSVReader single_instance = null;
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	private CSVReader(String filename)
	{
		this.setFilename(filename);
	}
	 public static CSVReader getInstance(String filename)
	    {
	        if (single_instance == null)
	            single_instance = new CSVReader(filename);
	 
	        return single_instance;
	    }
	public List<String> readData() throws IOException { 
    String file = this.filename;
    List<String> content = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line = "";
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
    } catch (FileNotFoundException e) {
      
    }
    return content;
}


}