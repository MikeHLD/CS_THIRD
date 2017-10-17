package citireMatrice;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CitireMatrice {

    public int[][] citesteMatrice(String numeFisier){


        try {
            Scanner input = new Scanner(new File(numeFisier));
            int m = input.nextInt();
            int n = input.nextInt();
            int[][] a = new int[m][n];
            while (input.hasNextLine()) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        try{
                            a[i][j] = input.nextInt();
                        }
                        catch (java.util.NoSuchElementException e) {
                            //e.printStackTrace();
                        }
                    }
                }
            }
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
