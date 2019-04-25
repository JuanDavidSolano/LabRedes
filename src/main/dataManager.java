package main;

import java.util.ArrayList;

/*
    El dataManager contiene y se encarga de todas las operaciones con datos y se las envia
    a quien requiera.
 */
public class dataManager {

    ArrayList<String> dataWords;
    ArrayList<String> codeWords;

    public ArrayList<String> getDataWords() {
        return dataWords;
    }

    public void setDataWords(ArrayList<String> dataWords) {
        this.dataWords = dataWords;
    }

    public ArrayList<String> getCodeWords() {
        return codeWords;
    }

    public void setCodeWords(ArrayList<String> codeWords) {
        this.codeWords = codeWords;
    }

    /*
        Este metodo recibe las DataWords y las pasa a el contador de 1s (unos)
        segun el resultado evalua si se debe agregar un (1) o un (0).
     */
    public ArrayList<String> generateCodeWords(ArrayList<String> dataWords) {
        ArrayList<String> codeWords = new ArrayList();
        for (String dataWord : dataWords) {
            StringBuilder codeWord = new StringBuilder();
            int count = count1s(dataWord);
            if (count % 2 == 0) {
                codeWord.append(dataWord).append("0");
            } else {
                codeWord.append(dataWord).append("1");
            }
            codeWords.add(codeWord.toString());
        }
        codeWords = changeBits(codeWords);
        System.out.println(codeWords);
        return codeWords;
    }

    public ArrayList<String> changeBits(ArrayList<String> codeWords) {
        ArrayList<String> code = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int numerocambios = 0;
        numerocambios = Math.toIntExact(Math.round(Math.random() * (codeWords.size() - 0 + 1)));
        System.out.println(numerocambios);
        for (String codeWord : codeWords) {
            char[] data = codeWord.toCharArray();
            if (numerocambios > 0) {
                int sw = 0;
                sw = Math.toIntExact(Math.round(Math.random() * (1 - 0 + 1)));
                if (sw == 1) {
                    numerocambios--;
                    int bit = 0;
                    bit = Math.toIntExact(Math.round(Math.random() * codeWord.length() + 1));

                    if (data[bit] == '1') {
                        data[bit] = '0';
                    } else {
                        data[bit] = '1';
                    }

                }
            }
            for (int i = 0; i < data.length; i++) {
                sb.append(data[i]);
            }
            code.add(sb.toString());
        }
        return code;
    }

    boolean verificar(ArrayList<String> codeWord) {
        boolean sw = false;
        for (String string : codeWord) {
            char[] data = string.toCharArray();
            int cont = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    cont++;
                }
            }
            if (cont % 2 == 0) {
                sw = true;
            } else {
                sw = false;
                break;
            }
        }
        return sw;
    }

    /*
        Este metodo cuenta la cantidad de 1s (unos) en la palabra de datos,
        esto lo hace dividiendo la palabra en caracteres y revisando cada uno
        dentro del vector.
     */
    public int count1s(String dataWord) {
        int cont = 0;
        char[] chars = dataWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                cont++;
            }
        }
        return cont;
    }

    /*
        Este metodo genera las DataWords con base en un mensaje recibido,
        esto lo hace pasando cada mensaje a el decodificador en ASCII y luego 
        separando los bits para obtener las palabras de datos de 128 bits.
     */
    public ArrayList<String> generateDataWords(ArrayList<String> data, int longitud) {
        ArrayList<String> DataWords = new ArrayList();
        for (String Data : data) {
            DataWords.add(this.string2bin(Data));
        }

        DataWords = separarBits(DataWords, longitud);
        return DataWords;
    }

    /*
        Este metodo convierte el String recibido a su respectivo codigo ASCII.
     */
    public String string2bin(String data) {
        StringBuilder bin = new StringBuilder();
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            StringBuilder zeros = new StringBuilder();
            int size = Integer.toBinaryString((int) chars[i]).length();
            for (int j = 0; j < 8 - size; j++) {
                /*
                    Debido a que los (0) ceros a la izquierda son eliminados, 
                    este ciclo agrega los ceros (0) faltantes para que la 
                    longitud de cada binario sea de 8 bits
                 */
                zeros.append("0");
            }
            bin.append(zeros).append(Integer.toBinaryString((int) chars[i]));
        }
        return bin.toString();
    }

    /*
        Este metodo separa los bits en grupos de 128 bits cada uno.
     */
    public ArrayList<String> separarBits(ArrayList<String> dataWords, int longitud) {
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
        int caracteresRestantes = separateWords.size() % longitud;
        /*
            Esta variable me dice cual es el maximo indice al cual debo llegar 
            para tener todos mis grupos de 16 caracteres completos
         */
        int limiteSup = separateWords.size() - caracteresRestantes;

        /*
            Este primer ciclo saca todos los grupos completos de 16 caracteres
         */
        for (int i = 0; i < limiteSup; i = i + longitud) {
            StringBuilder dataWord = new StringBuilder();
            for (int j = i; j < i + longitud; j++) {
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
