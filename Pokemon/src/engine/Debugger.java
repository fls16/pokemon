package engine;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class Debugger {

    public static int deletedObjects = 0;

    // @Before("execution(!static * finalize(..)) && this(o)")
    // public void beforeFinalize(Object o) throws Throwable {
    // deletedObjects++;
    // }

    public static void printGCStats() {
	long totalGarbageCollections = 0;
	long garbageCollectionTime = 0;

	for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {

	    long count = gc.getCollectionCount();

	    if (count >= 0) {
		totalGarbageCollections += count;
	    }

	    long time = gc.getCollectionTime();

	    if (time >= 0) {
		garbageCollectionTime += time;
	    }
	}

	System.gc();

	System.out.println("Total Garbage Collections: " + totalGarbageCollections);
	System.out.println("Total Garbage Collection Time (ms): " + garbageCollectionTime);
    }

}
