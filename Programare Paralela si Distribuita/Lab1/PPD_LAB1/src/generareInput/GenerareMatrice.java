package generareInput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class GenerareMatrice {

    private static PrintWriter writeToFile1;
    private static PrintWriter writeToFile2;
    public static int coloane;
    public static int linii;

    public static void generareMatrice(PrintWriter fisier, int linii, int coloane){

        Random rand = new Random();
        fisier.print(linii+" ");
        fisier.print(coloane+"\n");
        for(int i=0; i<linii; i++) {
            for (int j = 0; j < coloane; j++) {
                int n = rand.nextInt(5000) + 0;
                fisier.print(n+" ");
            }
            fisier.print("\n");
        }
        fisier.close();
    }

    public static void main(String args[]){
        try {
            writeToFile1 = new PrintWriter("matrice1.txt", "UTF-8");
            writeToFile2 = new PrintWriter("matrice2.txt","UTF-8");
            Scanner in = new Scanner(System.in);
            System.out.println("Introduceti numarul de coloane: ");
            coloane = in.nextInt();
            System.out.println("Introduceti numarul de linii: ");
            linii = in.nextInt();
            generareMatrice(writeToFile1, linii, coloane);
            generareMatrice(writeToFile2, linii, coloane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
