package main;

import java.util.ArrayList;

public class dataManager {

    public ArrayList<String> generateDataWords(ArrayList<String> data) {
        ArrayList<String> dataWords = new ArrayList();
        for (String Data : data) {
            if (Data.length() == 16) {

            }
        }
        return dataWords;
    }

    public String string2bin(String data) {
        StringBuilder bin = new StringBuilder();

        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Integer.toBinaryString((int) chars[i]).length() < 8) {
                bin.append("0" + Integer.toBinaryString((int) chars[i]));
            }else{
                bin.append(Integer.toBinaryString((int) chars[i]));
            }
            
        }
        return bin.toString();
    }
}
