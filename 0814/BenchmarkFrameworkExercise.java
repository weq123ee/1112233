// BenchmarkFrameworkExercise.java
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BenchmarkFrameworkExercise {
    interface TreeImpl {
        void insert(int k);
        boolean contains(int k);
        void delete(int k);
    }

    // simple impl using TreeSet
    static class TreeSetImpl implements TreeImpl {
        TreeSet<Integer> s = new TreeSet<>();
        public void insert(int k){ s.add(k); }
        public boolean contains(int k){ return s.contains(k); }
        public void delete(int k){ s.remove(k); }
    }

    public static void runBenchmark(TreeImpl impl, int n, int tests) {
        Random r = new Random(42);
        long insertTime=0, containsTime=0, deleteTime=0;
        for (int t=0;t<tests;t++) {
            int[] data = r.ints(n,0,n*10).toArray();
            long st = System.nanoTime();
            for (int v: data) impl.insert(v);
            insertTime += System.nanoTime()-st;

            st = System.nanoTime();
            for (int v: data) impl.contains(v);
            containsTime += System.nanoTime()-st;

            st = System.nanoTime();
            for (int v: data) impl.delete(v);
            deleteTime += System.nanoTime()-st;
        }
        System.out.println("avg insert(ms): " + TimeUnit.NANOSECONDS.toMillis(insertTime/tests));
        System.out.println("avg contains(ms): " + TimeUnit.NANOSECONDS.toMillis(containsTime/tests));
        System.out.println("avg delete(ms): " + TimeUnit.NANOSECONDS.toMillis(deleteTime/tests));
    }

    public static void main(String[] args) {
        TreeImpl impl = new TreeSetImpl();
        runBenchmark(impl, 10000, 5);
    }
}
