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
 *
 * @author giepaul
 */
public class MemoryUsage {
    public static void main(String[] args) {
        CarList cars = new CarList(100);
        System.out.println(ObjectGraphMeasurer.measure(cars));
        System.out.println(MemoryMeasurer.measureBytes(cars));
    }
}
