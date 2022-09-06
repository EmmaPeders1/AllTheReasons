package src.main.java.Project;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class SaveHandler {

	//TODO: add support for special characters to readFromFile

	public void writeToFile(String text) {
		try {
			String path = String.valueOf(Paths.get("src", "main", "java", "Project", "savefile.txt"));
			FileWriter myWriter = new FileWriter(path, Charset.forName("UTF8"));
			myWriter.write(text);
			myWriter.close();
			System.out.println("Successfully wrote to file.");
		}
		catch(IOException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}

	public String readFromFile(String filePath) {
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(filePath)));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
