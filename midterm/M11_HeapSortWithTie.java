import java.util.*;

public class M11_HeapSortWithTie {
    static class Pair {
        int val, idx;
        Pair(int v,int i){val=v;idx=i;}
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Pair[] arr=new Pair[n];
        for(int i=0;i<n;i++) arr[i]=new Pair(sc.nextInt(),i);
        Arrays.sort(arr,(a,b)->{
            if(a.val!=b.val) return a.val-b.val;
            return a.idx-b.idx;
        });
        for(int i=0;i<n;i++){
            System.out.print(arr[i].val);
            if(i<n-1) System.out.print(" ");
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * - 使用排序演算法 (Heap/Quick/Merge) 都是 O(n log n)。
 * - 比較時 tie-break 為常數時間。
 */
