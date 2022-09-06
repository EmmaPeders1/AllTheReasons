package src.main.java.Project;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class rema {

	/*private int number;
  private String reason;
	private static Map<Integer,String> map;*/

	public static void addReason(int number, String reason, Map<Integer,String> map) {
    map.put(number, reason);
	}


  public static String printReasons(int maplength, Map<Integer, String> map){
    String result = "";
    for(int i = 1; i < maplength + 1; i++){
      if(map.get(i) != null){
        result += (i + " : " + map.get(i) + "\n");
        //System.out.println("Grunn nummer " + i + ": " + map.get(i));
      }
    }
    return result;
  }

  //get the reasons from the file
  public static Map<Integer, String> getReasonsFromFile(){
    Map<Integer, String> results = new HashMap<>();
    SaveHandler sh = new SaveHandler();
    String path = String.valueOf(Paths.get("src", "main", "java", "Project", "savefile.txt"));
    String textFromFile = sh.readFromFile(path);
    String[] lines = textFromFile.split("\n");
    String[][] words = new String[lines.length][lines.length];
    for(int i = 0; i < lines.length; i++){
      words[i] = lines[i].split(" : ");
    }
    for(int i = 0; i < words.length; i++){
      results.put(Integer.parseInt(words[i][0]), words[i][1]);
    }
    return results;
  }

  public static int getMaxKey(Map<Integer, String> map){
    Map.Entry<Integer, String> entryWithMaxKey = null;
    for(Map.Entry<Integer,String> currentEntry : map.entrySet()){
      if(entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0){
        entryWithMaxKey = currentEntry;
      }
    }
    return entryWithMaxKey.getKey();
  }

  public static Map<Integer, String> search(String input, Map<Integer, String> map){
    Map<Integer, String> results = new HashMap<>();
    for(int i = 1; i < getMaxKey(map) + 1; i++){
      //System.out.println(map.get(i));
      if(map.get(i) != null && map.get(i).toLowerCase().contains(input.toLowerCase())){
        results.put(i, map.get(i));
      }
    }
    System.out.println("Resultat av søk: " + results);
    return results;
}

	public static void main(String[] args) {
    //rema rema = new rema();
    Map<Integer,String> map = new HashMap<>();
    src.main.java.Project.rema.search("norske", map);
    int maxKey = src.main.java.Project.rema.getMaxKey(map);
    src.main.java.Project.rema.printReasons(maxKey, map);
    src.main.java.Project.rema.getReasonsFromFile();
	}
}