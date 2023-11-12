package christmas.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static List<String[]> splitStringsToList(String[] inputs) {
        List<String[]> splitedInputs = new ArrayList<>();
        for (String input : inputs) {
            String[] keyValue = input.split("-");
            splitedInputs.add(keyValue);
        }
        return splitedInputs;
    }

}
