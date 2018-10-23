/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1c.lecture;

import edu.ktu.ds.lab1c.util.Ks;
import edu.ktu.ds.lab1c.util.LinkedList;

public class Player implements Comparable<Player> {

    private String pavardė;
    private int mesta;
    private int pataikyta;

    public Player(String pavardė, int mesta, int pataikyta) {
        this.pavardė = pavardė;
        this.mesta = mesta;
        this.pataikyta = pataikyta;
    }

    // ... geteriai ir seteriai 
    public double tikslumas() {
        return mesta == 0 ? 0.0 : 100.0 * pataikyta / mesta;
    }

    @Override
    public int compareTo(Player o) {
        return 1;
    }

    @Override
    public String toString() {
        return "pavardė=" + pavardė + ", mesta=" + mesta + ", pataikyta=" + pataikyta + "; ";
    }

    //=============================================
    static LinkedList<Player> komanda = new LinkedList<>();

    static void žaidėjųSąrašoTestas() {
        Player p1 = new Player("Seibutis", 9, 9);
        Player p2 = new Player("Mačiulis", 7, 8);
        Player p3 = new Player("Jankūnas", 6, 4);
        komanda.add(p1);
        komanda.add(p2);
        komanda.add(p3);
        Ks.oun("Komandos žaidėjų skaičius=" + komanda.size());
        Ks.oun("Žaidėjai:\n" + komanda.get(0) + komanda.get(1) + komanda.get(2));
        //-------------------
        Ks.oun("Žaidėjas(0)=" + komanda.get(0));
        Ks.oun("Žaidėjas(1)=" + komanda.get(1));
        Ks.oun("Žaidėjas(2)=" + komanda.get(2));
        komanda.add(new Player("Gailius", 0, 0));
        komanda.add(new Player("Javtokas", 3, 0));
        Ks.oun("Žaidėjas(3)=" + komanda.get(3));
        Ks.oun("Žaidėjas(4)=" + komanda.get(4));
        Ks.oun("Komandos žaidėjų skaičius=" + komanda.size());
        //------------------
        p1 = komanda.get(3);  // Gailius ...
        p2 = komanda.get(4);  // Javtokas ...
        p3 = komanda.get(5);  // planuojame gauti null
        Ks.oun("Rinkinys:\n" + p1 + "\n" + p2 + "\n" + p3);
        Ks.oun(komanda.isEmpty() ? "nieko nėra" : "šiek tiek yra");
        komanda.clear();
        Ks.oun(komanda.isEmpty() ? "nieko nėra" : "šiek tiek yra");
    }  // žaidėjųSąrašoTesto pabaiga

    static void printListKTU(LinkedList<Player> sąr) {
        for (Player z = sąr.get(0); z != null; z = sąr.getNext()) {
            Ks.oun("Žaidėjas-> " + z);
        }
    } // ---------------------------------------------------------------

    static void antiŠablonas(LinkedList<Player> sąr) {
        for (int i = 0; i < sąr.size(); i++) {
            Ks.oun("Žaidėjas-> " + sąr.get(i));
        }
    } // ---------------------------------------------------------------

    public static void main(String[] args) {
        žaidėjųSąrašoTestas();
        printListKTU(komanda);
        antiŠablonas(komanda);
    }
}
