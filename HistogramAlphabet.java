package sample;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistogramAlphabet {
    String read_file_text;

    public HistogramAlphabet() {
        ReadFile text_name = new ReadFile();
        text_name.openFile();
        read_file_text = text_name.readFile();
        text_name.closeFile();
    }

    public void count_chars(MyPieChart P) {
        String replace_chars = read_file_text.replaceAll("[^A-Za-z]","").toLowerCase();
        //System.out.println(replace_chars);
        Map<String, Double> histogram = new LinkedHashMap<>();
        for (int i = 0; i < replace_chars.length(); i++) {
            Character c = replace_chars.charAt(i);
            histogram.putIfAbsent(c.toString(),0.0); //puts 0
            histogram.put(c.toString(), histogram.get(c.toString())+1); //increments the key by one
        }
        P.setProbabilites(histogram);
    }
}
