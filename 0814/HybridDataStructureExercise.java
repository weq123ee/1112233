// HybridDataStructureExercise.java
import java.util.*;

public class HybridDataStructureExercise {
    // 對外提供簡單 put/get
    interface MapDS {
        void put(int k, String v);
        String get(int k);
    }

    // 用 HashMap + AVL(for read) / RB(for write) 的混合模擬
    static class Hybrid implements MapDS {
        Map<Integer,String> storage = new HashMap<>();
        int reads=0, writes=0;
        boolean favorRead = true;

        public void put(int k, String v) {
            writes++;
            storage.put(k,v);
            maybeSwitch();
        }
        public String get(int k) {
            reads++;
            maybeSwitch();
            return storage.get(k);
        }
        private void maybeSwitch() {
            // very simple heuristic
            if (writes > reads && favorRead) {
                favorRead = false; System.out.println("Switch to write-optimized (RB)");
            } else if (reads >= writes && !favorRead) {
                favorRead = true; System.out.println("Switch to read-optimized (AVL)");
            }
        }
    }

    public static void main(String[] args) {
        Hybrid h = new Hybrid();
        h.put(1,"A"); h.put(2,"B"); h.get(1); h.get(2); h.get(3);
        h.put(3,"C"); h.put(4,"D"); h.put(5,"E");
        h.get(1); h.get(5);
    }
}
