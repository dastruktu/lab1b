/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1c.demo;

import edu.ktu.ds.lab1c.util.ParsableList;

/**
 *
 * @author giepaul
 */
public class CarList extends ParsableList<Car> {

    @Override
    protected Car createElement(String data) {
        return new Car(data);
    }

}
