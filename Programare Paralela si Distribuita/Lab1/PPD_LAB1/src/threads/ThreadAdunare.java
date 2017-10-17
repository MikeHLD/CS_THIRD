package threads;

public class ThreadAdunare extends Thread {

    public int[][] mat1;
    public int[][] mat2;
    public int[][] matRezultat;
    public int linieInceput;
    public int linieSfarsit;

    public ThreadAdunare(int[][] a, int[][] b, int[][] c, int linieInceput, int linieSfarsit){
        mat1 = a;
        mat2 = b;
        matRezultat = c;
        this.linieInceput = linieInceput;
        this.linieSfarsit = linieSfarsit;
    }

    @Override
    public void run(){
        System.out.println("Thread-ul " + getId() + " calculeaza SUMA pt " + (linieSfarsit - linieInceput) + " linii");
        for (int i = linieInceput; i < linieSfarsit; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                matRezultat[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        System.out.println("Thread-ul " + getId() + " a terminat.");
    }

}
