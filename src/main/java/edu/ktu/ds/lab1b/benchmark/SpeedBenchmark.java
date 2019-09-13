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
package edu.ktu.ds.lab1b.benchmark;

import edu.ktu.ds.lab1b.demo.Car;
import edu.ktu.ds.lab1b.demo.CarList;
import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;
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

/**
 * Greitaveikos testų įrankis:
 * https://openjdk.java.net/projects/code-tools/jmh/
 * 
 * JMH naudojimo pavyzdžiai ir jų aprašymai:
 * https://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
 */
public class SpeedBenchmark {

    @State(Scope.Benchmark)
    public static class GeneratedCars {

        @Param({"2000", "4000", "8000", "16000"})
        public int count;

        volatile LinkedList<Car> list = new LinkedList<>();

        @Setup(Level.Iteration)
        public void generate() {
            list = new CarList(count);
        }
    }

    @State(Scope.Thread)
    public static class ClonedCars {

        volatile LinkedList<Car> list = new LinkedList<>();

        @Setup(Level.Invocation)
        public void copy(GeneratedCars original) {
            this.list = original.list.clone();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void defaultSort(ClonedCars cars) throws InterruptedException {
        cars.list.sortSystem();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void defaultsortWithComparator(ClonedCars cars) throws InterruptedException {
        cars.list.sortSystem(Car.byPrice);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void bubbleSort(ClonedCars cars) throws InterruptedException {
        cars.list.sortBuble();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void bubbleSortWithComparator(ClonedCars cars) throws InterruptedException {
        cars.list.sortBuble(Car.byPrice);
    }

    private static void testCarGeneration(int count) {
        for (Car car : new CarList(count)) {
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
