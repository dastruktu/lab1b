/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.ktu.ds.lab1c.benchmark;

import edu.ktu.ds.lab1c.demo.Car;
import edu.ktu.ds.lab1c.util.Ks;
import edu.ktu.ds.lab1c.util.LinkedList;
import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class SpeedBenchmark {

    @State(Scope.Benchmark)
    public static class GeneratedCars {

        @Param({"2000", "4000", "8000", "16000"})
        public int count;

        volatile LinkedList<Car> carList = new LinkedList<>();

        @Setup(Level.Iteration)
        public void generate() {
            carList = generateRandomCars(count);
        }
    }

    @State(Scope.Thread)
    public static class ClonedCars {

        volatile LinkedList<Car> carList = new LinkedList<>();

        @Setup(Level.Invocation)
        public void copy(GeneratedCars original) {
            this.carList = original.carList.clone();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void defaultSort(ClonedCars list) throws InterruptedException {
        list.carList.sortSystem();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void defaultsortWithComparator(ClonedCars list) throws InterruptedException {
        list.carList.sortSystem(Car.byPrice);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void bubbleSort(ClonedCars list) throws InterruptedException {
        list.carList.sortBuble();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void bubbleSortWithComparator(ClonedCars list) throws InterruptedException {
        list.carList.sortBuble(Car.byPrice);
    }

    private static LinkedList<Car> generateRandomCars(int count) {
            String[][] makesAndModels = {
                {"Mazda", "121", "323", "626", "MX6"},
                {"Ford", "Fiesta", "Escort", "Focus", "Sierra", "Mondeo"},
                {"Saab", "92", "96"},
                {"Honda", "Accord", "Civic", "Jazz"},
                {"Renault", "Laguna", "Megane", "Twingo", "Scenic"},
                {"Peugeot", "206", "207", "307"}
            };
            Random rnd = new Random();
            rnd.setSeed(2017);
            LinkedList<Car> cars = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                int makeIndex = rnd.nextInt(makesAndModels.length);
                int modelIndex = rnd.nextInt(makesAndModels[makeIndex].length - 1) + 1;
                cars.add(new Car(makesAndModels[makeIndex][0], makesAndModels[makeIndex][modelIndex],
                        1994 + rnd.nextInt(20),
                        6000 + rnd.nextInt(222_000),
                        1000 + rnd.nextDouble() * 100_000));
            }
            return cars;
    }
    
    private static void testCarGeneration(int count) {
        for (Car car : generateRandomCars(count)) {
            Ks.oun(car);
        }
    }
    
    public static void main(String[] args) throws RunnerException {
        System.out.println("Randomly generated cars:");
        testCarGeneration(20);
        System.out.println("");
        
        Options opt = new OptionsBuilder()
                .include(SpeedBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
