import citireMatrice.CitireMatrice;
import generareInput.GenerareMatrice;
import threads.ThreadAdunare;
import threads.ThreadInmultire;

import java.util.Scanner;


public class StartApp {

    public static int LINII;
    public static int COLOANE;
    public static int[][] matRezultatAdunare;
    public static int[][] matRezultatInmultire;
    public static int[][] mat1;
    public static int[][] mat2;


    public static void inmultire(int threadsNum){

        if(mat1[0].length != mat2.length){
            System.out.println("Dimensiunile matricilor nu permit INMULTIREA");
            return;
        }

        ThreadInmultire[] threaduri = new ThreadInmultire[threadsNum];
        int nrLiniiPerThread = LINII / threadsNum;
        int restLinii = LINII % threadsNum;
        int nrLiniiAux = nrLiniiPerThread;
        int linieInceput = 0;
        int linieSfarsit = 0;
        int indexThread = 0;
        matRezultatInmultire = new int[LINII][COLOANE];

        double timpInceput = System.nanoTime();
        for (int i=0; i<threadsNum; i++) {
            if (restLinii != 0) {
                nrLiniiPerThread = nrLiniiAux + 1;
                restLinii--;
            } else {
                nrLiniiPerThread = nrLiniiAux;
            }
            linieSfarsit += nrLiniiPerThread;
            threaduri[indexThread] = new ThreadInmultire(mat1, mat2, matRezultatInmultire, linieInceput, linieSfarsit);
            threaduri[indexThread].start();
            indexThread++;
            linieInceput += nrLiniiPerThread;
        }
        for (int i = 0; i < threadsNum; i++){
            try {
                threaduri[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Timpul de executie pentru INMULTIRE a fost: "+(System.nanoTime() - timpInceput));
        System.out.println("----------------------INMULTIRE----------------------");
        for(int q =0; q<LINII;q++) {
            for (int j = 0; j < COLOANE; j++) {
                System.out.print(matRezultatInmultire[q][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static void adunare(int threadsNum){


        int nrLiniiPerThread = LINII / threadsNum;
        int restLinii = LINII % threadsNum;
        int nrLiniiAux = nrLiniiPerThread;
        int linieInceput = 0;
        int linieSfarsit = 0;
        matRezultatAdunare = new int[LINII][COLOANE];

        double timpInceput = System.nanoTime();
        for (int i=0; i<threadsNum; i++){
            if(restLinii != 0){
                nrLiniiPerThread = nrLiniiAux + 1;
                restLinii--;
            }
            else{
                nrLiniiPerThread = nrLiniiAux;
            }
            linieSfarsit += nrLiniiPerThread;
            new ThreadAdunare(mat1, mat2, matRezultatAdunare, linieInceput, linieSfarsit).run();
            linieInceput += nrLiniiPerThread;
        }

        System.out.println("Timpul de executie pentru ADUNARE a fost: "+(System.nanoTime() - timpInceput));
        System.out.println("----------------------ADUNARE----------------------");
        for(int q =0; q<LINII;q++) {
            for (int j = 0; j < COLOANE; j++) {
                System.out.print(matRezultatAdunare[q][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static void main(String args[]){

        GenerareMatrice.main(args);

        LINII = GenerareMatrice.linii;
        COLOANE = GenerareMatrice.coloane;

        CitireMatrice read = new CitireMatrice();
        mat1 = read.citesteMatrice("matrice1.txt");
//        for(int i =0; i<LINII;i++) {
//            for (int j = 0; j < COLOANE; j++) {
//                System.out.print(mat1[i][j] + " ");
//            }
//            System.out.println("\n");
//        }

        System.out.println("---------------------------------------");

        mat2 = read.citesteMatrice("matrice2.txt");
//        for(int i =0; i<LINII;i++) {
//            for (int j = 0; j < COLOANE; j++) {
//                System.out.print(mat2[i][j] + " ");
//            }
//            System.out.println("\n");
//        }


        Scanner in = new Scanner(System.in);
        int threadsNum = 1;
        boolean ok = true;
        while(ok){
            System.out.println("Introduceti numarul de threaduri: ");
            threadsNum = in.nextInt();
            if(threadsNum <= LINII) {
                ok = false;
            }
        }
        adunare(threadsNum);
        inmultire(threadsNum);
    }
}
