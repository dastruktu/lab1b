/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.benchmark;

import edu.ktu.ds.lab1b.demo.CarList;
import objectexplorer.MemoryMeasurer;
import objectexplorer.ObjectGraphMeasurer;

/**
 * MemoryMeasurer klasei reikalingas JVM veikiantis ObjectExplorer javaagent'as
 * (ObjectExplorer paketas automatiškai įdiegiamas kaip Lab1b projekto dependency).
 * Paleidimas:
 *   java -javaagent:path\to\object-explorer.jar -cp target\lab1b-1.0.jar edu.ktu.ds.lab1b.benchmark.MemoryUsage
 */
public class MemoryUsage {
    public static void main(String[] args) {
        CarList cars = new CarList(100);
        System.out.println(ObjectGraphMeasurer.measure(cars));
        System.out.println(MemoryMeasurer.measureBytes(cars));
    }
}
