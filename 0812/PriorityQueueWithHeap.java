import java.util.*;

class Task {
    String name;
    int priority;
    long timestamp; // 用來確保相同優先級的 FIFO 順序

    Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> queue;
    private long timeCounter;

    public PriorityQueueWithHeap() {
        timeCounter = 0;
        queue = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) return b.priority - a.priority; // 高優先級在前
            return Long.compare(a.timestamp, b.timestamp); // 相同優先級依時間排序
        });
    }

    public void addTask(String name, int priority) {
        queue.offer(new Task(name, priority, timeCounter++));
    }

    public Task executeNext() {
        return queue.poll();
    }

    public Task peek() {
        return queue.peek();
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        Task target = null;
        while (!queue.isEmpty()) {
            Task t = queue.poll();
            if (t.name.equals(name)) {
                target = t;
                break;
            }
            temp.add(t);
        }
        if (target != null) {
            target.priority = newPriority;
            target.timestamp = timeCounter++;
            temp.add(target);
        }
        queue.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("執行順序：");
        while (pq.peek() != null) {
            System.out.println(pq.executeNext().name);
        }
    }
}
