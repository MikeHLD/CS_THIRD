package threads;

public class ThreadInmultire extends Thread {


    public int[][] mat1;
    public int[][] mat2;
    public int[][] matRezultat;
    public int linieInceput;
    public int linieSfarsit;

    public ThreadInmultire(int[][] a, int[][] b, int[][] c, int linieInceput, int linieSfarsit){
        mat1 = a;
        mat2 = b;
        matRezultat = c;
        this.linieInceput = linieInceput;
        this.linieSfarsit = linieSfarsit;
    }

    @Override
    public void run(){
        System.out.println("Thread-ul " + getId() + " calculeaza INMULTIREA pt " + (linieSfarsit - linieInceput) + " linii");
        for(int linie = linieInceput; linie < linieSfarsit; linie++){
            for(int coloana = 0; coloana < mat1[linie].length; coloana++){
                for(int aux=0; aux <mat1.length; aux++){
                    matRezultat[linie][coloana] += mat1[linie][aux] * mat2[aux][coloana];
                }
            }
        }
    }
}
