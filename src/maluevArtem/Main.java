package maluevArtem;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  По результатам выполнения программы при маленьком массиве задача с SynchronizedMap и задача с ConcurrentHashMap
 *  выполняются практически одновременно. Но при увиличении массива задача с ConcurrentHashMap выполняется быстрее
 *  чем задача с SynchronizedMap. Разрыв во времени увеличивается с ростом количества элементов массива.
 *  Так же время выполнение задачи с SynchronizedMap на большом массиве сильно больше чем на маленьком.
 *  А время выполнения задачи с ConcurrentHashMap на большом массиве увеличивается незначительно по сравнению с
 *  задачей на маленьком массивом.
 */

public class Main {

    public static final int SIZE_SMALL_ARRAY = 1000;
    public static final int SIZE_LARGE_ARRAY = 10_000_000;

    public static final int DELAY_TIME = 3000;

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        long[] smallArrayNumbers = new long[SIZE_SMALL_ARRAY];
        Arrays.fill(smallArrayNumbers, 5);
        long[] largeArrayNumbers = new long[SIZE_LARGE_ARRAY];
        Arrays.fill(largeArrayNumbers, 5);

        // с маленьким массивом
        WriteReadSynchronizedMap sync1 = new WriteReadSynchronizedMap(synchronizedMap, smallArrayNumbers);
        WriteReadConcurrentHashMap concur1 = new WriteReadConcurrentHashMap(concurrentHashMap, smallArrayNumbers);

        firstExperiment(sync1, concur1);
        System.out.println();

        // с большим массивом
        WriteReadSynchronizedMap sync2 = new WriteReadSynchronizedMap(synchronizedMap, largeArrayNumbers);
        WriteReadConcurrentHashMap concur2 = new WriteReadConcurrentHashMap(concurrentHashMap, largeArrayNumbers);

        secondExperiment(sync2, concur2);
    }

    public static void firstExperiment(WriteReadSynchronizedMap sync, WriteReadConcurrentHashMap concur)
            throws InterruptedException {
        System.out.println("При малом массиве:");
        Instant startTime1 = Instant.now();

        Thread thread1 = new Thread(null, sync::writeThreadSynchronizedMap, "Поток на запись");
        Thread thread2 = new Thread(null, sync::writeThreadSynchronizedMap, "Поток на запись");
        Thread thread3 = new Thread(null, sync::readThreadSynchronizedMap, "Поток на чтение");
        Thread thread4 = new Thread(null, sync::readThreadSynchronizedMap, "Поток на чтение");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        Instant finishTime1 = Instant.now();
        long executionTime1 = Duration.between(startTime1, finishTime1).toMillis();
        System.out.println("Время выполнения задачи с SynchronizedMap: " + executionTime1 + " мс");

        Instant startTime2 = Instant.now();

        Thread thread5 = new Thread(null, concur::writeThreadConcurrentHashMap, "Поток на запись");
        Thread thread6 = new Thread(null, concur::writeThreadConcurrentHashMap, "Поток на запись");
        Thread thread7 = new Thread(null, concur::readThreadConcurrentHashMap, "Поток на чтение");
        Thread thread8 = new Thread(null, concur::readThreadConcurrentHashMap, "Поток на чтение");

        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();

        Instant finishTime2 = Instant.now();
        long executionTime2 = Duration.between(startTime2, finishTime2).toMillis();
        System.out.println("Время выполнения задачи с ConcurrentHashMap: " + executionTime2 + " мс");
    }

    public static void secondExperiment(WriteReadSynchronizedMap sync, WriteReadConcurrentHashMap concur)
            throws InterruptedException {
        System.out.println("При большом массиве:");
        Instant startTime1 = Instant.now();

        Thread thread1 = new Thread(null, sync::writeThreadSynchronizedMap, "Поток на запись");
        Thread thread2 = new Thread(null, sync::writeThreadSynchronizedMap, "Поток на запись");
        Thread thread3 = new Thread(null, sync::readThreadSynchronizedMap, "Поток на чтение");
        Thread thread4 = new Thread(null, sync::readThreadSynchronizedMap, "Поток на чтение");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        Instant finishTime1 = Instant.now();
        long executionTime1 = Duration.between(startTime1, finishTime1).toMillis();
        System.out.println("Время выполнения задачи с SynchronizedMap: " + executionTime1 + " мс");

        Instant startTime2 = Instant.now();

        Thread thread5 = new Thread(null, concur::writeThreadConcurrentHashMap, "Поток на запись");
        Thread thread6 = new Thread(null, concur::writeThreadConcurrentHashMap, "Поток на запись");
        Thread thread7 = new Thread(null, concur::readThreadConcurrentHashMap, "Поток на чтение");
        Thread thread8 = new Thread(null, concur::readThreadConcurrentHashMap, "Поток на чтение");

        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();

        Instant finishTime2 = Instant.now();
        long executionTime2 = Duration.between(startTime2, finishTime2).toMillis();
        System.out.println("Время выполнения задачи с ConcurrentHashMap: " + executionTime2 + " мс");
    }
}
