package Metoda_Iteracji_Prostych;

import java.util.Scanner;

public class MetodaIteracjiProstych {

    static double E = 0.00001; //epsilon
    static int max_iter = 150; //maksymalna liczba iteracji

    static int IlePoPrzecinku(double E) {
        int ile = 0;
        while(E < 1){
            ile++;
            E *= 10;
        }
        return ile;
    }

    static void tabs1(double[] tab,int l_wierszy) {
        for(int i = 0; i < l_wierszy; i++) {
            System.out.println(tab[i]);
        }
    }

    public static void main(String[] args) {
        int liczba_rownan; //liczba równań w układzie
        double[][] row; // cały układ równań(ostatnia kolumna to wyniki)
        double[][] hij; //oznaczenie hij
        double[] gi; //oznaczenie gi
        double[] x; //przybliżenia początkowe równań
        double x1, x0;// zmienne do warunku stopu
        int iter = 0; //liczba iteracji
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj liczbe rownan w układzie: ");
        liczba_rownan = sc.nextInt();
        row = new double[liczba_rownan][liczba_rownan+1];
        hij = new double[liczba_rownan][liczba_rownan];
        gi = new double[liczba_rownan];
        x = new double[liczba_rownan];

        //wczytanie wszytkich równań
        for(int i = 0; i < liczba_rownan; i++) {
            System.out.println("rownanie " + (i + 1));
            for(int j = 0; j < liczba_rownan+1; j++) {
                if(j == liczba_rownan) {
                    System.out.print("wynik rownania: " );
                    row[i][j] = sc.nextDouble();
                }else {
                    System.out.print("a" + (j+1) + ": " );
                    row[i][j] = sc.nextDouble();
                }
            }
        }
        //wczytanie przybliżeń początkowych dla wszystkich równań
        System.out.print("Podaj przyblizenie poczatkowe (x0): ");
        System.out.println("");
        for(int i = 0; i < liczba_rownan; i++) {
            System.out.print("p" + (i+1) + ": ");
            x[i] = sc.nextDouble();
        }
        //wprowadzam dodatkowe oznaczenia do równań (Gi oraz Hij)
        for(int i = 0; i < liczba_rownan; i++) {
            for(int j = 0; j < liczba_rownan; j++) {
                if(i != j) {
                    hij[i][j] = (-1) * row[i][j]/row[i][i];
                }else {
                    hij[i][j] = 0;
                }
            }
            gi[i] = row[i][liczba_rownan]/row[i][i];
        }

        x0 = x[0];
        x1 = x0 + E*10;

        //wyznaczam kolejne przybliżenia
        while(Math.abs(x0 - x1) > E || iter > max_iter) { //dopuki nie jest spełniony warunek stopu
            x1 = x0;
            for(int i = 0; i < liczba_rownan; i++) {
                x[i] = wynx(hij[i], x, gi[i], liczba_rownan);
            }
            System.out.println("----------------------------"); //wypisuje kolejne przybliżenia
            tabs1(x, liczba_rownan);
            x0 = x[0];
            iter++;
        }
        System.out.println("----------------------------"); // wypisuje rozwiązanie
        System.out.println("WYNIK: ");
        for(int i = 0; i < liczba_rownan; i++) {
           // System.out.println("x" + (i + 1) + " = " + x[i]);
            System.out.print("x" + (i + 1) + " = ");
            System.out.format("%."+IlePoPrzecinku(E)+"f%n", x[i]);
            System.lineSeparator();
        }
        System.out.println("Liczba iteracji = " + iter);
    }


    static double wynx(double[] tab_a, double[] tab_b, double w, int liczba_rownan) {
        double wynik = 0;
        for(int i = 0; i < liczba_rownan; i++) {
            wynik += (tab_a[i] * tab_b[i]);
        }
        wynik += w;
        return wynik;
    }
}