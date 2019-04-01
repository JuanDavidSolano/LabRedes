package main;

import java.util.ArrayList;

public class dataManager {

    public ArrayList<String> generateDataWords(ArrayList<String> data) {
        ArrayList<String> dataWords = new ArrayList();
        for (String Data : data) {
            dataWords.add(this.string2bin(Data));
        }

        dataWords = separarBits(dataWords);
        return dataWords;
    }

    public String string2bin(String data) {
        StringBuilder bin = new StringBuilder();
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            StringBuilder zeros = new StringBuilder();
            int size = Integer.toBinaryString((int) chars[i]).length();
            for (int j = 0; j < 8 - size; j++) {
                zeros.append("0");
            }
            bin.append(zeros).append(Integer.toBinaryString((int) chars[i]));
        }
        return bin.toString();
    }

    public ArrayList<String> separarBits(ArrayList<String> dataWords) {
        ArrayList<String> separateWords = new ArrayList();
        ArrayList<String> DataWords = new ArrayList();
        for (String DataWord : dataWords) {
            for (int i = 0; i < DataWord.length(); i = i + 8) {
                separateWords.add(DataWord.substring(i, i + 8));
            }
        }

        /* 
            Esta variable me dice cuantos caracteres van a sobrar, es decir que 
            se quedan por fuera de los grupos de 16 caracteres 
         */
        int caracteresRestantes = separateWords.size() % 16;
        /*
            Esta variable me dice cual es el maximo indice al cual debo llegar 
            para tener todos mis grupos de 16 caracteres completos
         */
        int limiteSup = separateWords.size() - caracteresRestantes;

        /*
            Este primer ciclo saca todos los grupos completos de 16 caracteres
         */
        for (int i = 0; i < limiteSup; i = i + 16) {
            StringBuilder dataWord = new StringBuilder();
            for (int j = i; j < i + 16; j++) {
                dataWord.append(separateWords.get(j));
            }
            DataWords.add(dataWord.toString());
        }
        StringBuilder dataWord = new StringBuilder();

        /*
            Este segundo ciclo saca la palabra de datos restante
         */
        for (int j = limiteSup; j < separateWords.size(); j++) {
            dataWord.append(separateWords.get(j));
        }
        DataWords.add(dataWord.toString());

        return DataWords;
    }
}
