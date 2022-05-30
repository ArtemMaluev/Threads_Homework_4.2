package maluevArtem;

import java.util.concurrent.ConcurrentHashMap;

public class WriteReadConcurrentHashMap {

    private final ConcurrentHashMap<Integer, Integer> concurrentHashMap;

    private final long[] arrayConcur;

    public WriteReadConcurrentHashMap(ConcurrentHashMap<Integer, Integer> concurrentHashMap, long[] array) {
        this.concurrentHashMap = concurrentHashMap;
        arrayConcur = array;
    }

    public void writeThreadConcurrentHashMap() {
        for (int i = 0; i < arrayConcur.length; i++) {
            concurrentHashMap.put(i, i);
        }
    }

    public void readThreadConcurrentHashMap() {
        try {
            Thread.sleep(Main.DELAY_TIME);
            for (int i = 0; i < arrayConcur.length; i++) {
                concurrentHashMap.get(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
