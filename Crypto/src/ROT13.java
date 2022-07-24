import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;

import static java.lang.Character.isUpperCase;

public class ROT13 {


    String alph = "abcdefghijklmnopqrstuvwxyz";
    int shift;

    ROT13(Character cs, Character cf) {
        shift = cf - cs;
    }

    ROT13() {
        shift = 13;
    }


    public String crypt(String text) throws UnsupportedOperationException {
        StringBuilder tempText = new StringBuilder();
        String lowerText = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {tempText.append(text.charAt(i));}
            else {
                int index = alph.indexOf(lowerText.charAt(i));
                index += shift;
                index = index % 26;
                if (isUpperCase(text.charAt(i))){
                    tempText.append(Character.toUpperCase(alph.charAt(index)));
                } else tempText.append(alph.charAt(index));
            }
        }
        return String.valueOf(tempText);
    }

    public String encrypt(String text) {
        text = crypt(text);
        return text;
    }

    public String decrypt(String text) {
        text = crypt(text);
        return text;
    }

    public static String rotate(String s, Character c) {
        int shift = s.indexOf(c);
        return s.substring(shift) + s.substring(0, shift);
    }

    public void Encryptor(String input, String output) throws IOException {
        StringBuilder holder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        String red;
        while ((red = reader.readLine()) != null) {
            holder.append(red + "\n");
        }
        String encrypted = encrypt(String.valueOf(holder));

        writer.write(encrypted);
        reader.close();
        writer.close();
    }
}
