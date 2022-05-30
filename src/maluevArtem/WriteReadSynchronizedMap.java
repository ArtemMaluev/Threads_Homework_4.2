package maluevArtem;

import java.util.Map;

public class WriteReadSynchronizedMap {

    private final Map<Integer, Integer> synchronizedMap;
    private final long[] arraySync;

    public WriteReadSynchronizedMap(Map<Integer, Integer> synchronizedMap, long[] array) {
        this.synchronizedMap = synchronizedMap;
        arraySync = array;
    }

    public void writeThreadSynchronizedMap() {
        for (int i = 0; i < arraySync.length; i++) {
            synchronizedMap.put(i, i);
        }
    }

    public void readThreadSynchronizedMap() {
        try {
            Thread.sleep(Main.DELAY_TIME);
            for (int i = 0; i < arraySync.length; i++) {
                synchronizedMap.get(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
