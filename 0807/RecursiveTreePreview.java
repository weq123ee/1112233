import java.util.*;

public class RecursiveTreePreview {

    // 模擬資料夾結構
    static class Folder {
        String name;
        List<Folder> subFolders;
        int fileCount;

        Folder(String name, int fileCount) {
            this.name = name;
            this.fileCount = fileCount;
            this.subFolders = new ArrayList<>();
        }

        void addSubFolder(Folder folder) {
            subFolders.add(folder);
        }
    }

    public static void main(String[] args) {
        // 建立範例資料夾結構
        Folder root = new Folder("root", 2);
        Folder sub1 = new Folder("sub1", 3);
        Folder sub2 = new Folder("sub2", 0);
        Folder sub11 = new Folder("sub11", 1);

        sub2.addSubFolder(new Folder("sub21", 4));
        sub2.addSubFolder(new Folder("sub22", 1));
        sub1.addSubFolder(sub11);
        root.addSubFolder(sub1);
        root.addSubFolder(sub2);

        System.out.println("總檔案數: " + totalFiles(root));

        System.out.println("多層選單列印:");
        printMenu(root, 0);

        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3,4)), 5);
        System.out.println("展平巢狀陣列: " + flattenNestedList(nestedList));

        List<Object> deepList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4,5))));
        System.out.println("巢狀清單最大深度: " + maxDepth(deepList));
    }

    // 計算資料夾的總檔案數
    public static int totalFiles(Folder folder) {
        int total = folder.fileCount;
        for (Folder sub : folder.subFolders) {
            total += totalFiles(sub);
        }
        return total;
    }

    // 列印多層選單結構
    public static void printMenu(Folder folder, int indent) {
        for (int i=0; i<indent; i++) System.out.print("  ");
        System.out.println(folder.name);
        for (Folder sub : folder.subFolders) {
            printMenu(sub, indent + 1);
        }
    }

    // 展平巢狀陣列
    public static List<Object> flattenNestedList(List<?> nestedList) {
        List<Object> result = new ArrayList<>();
        for (Object obj : nestedList) {
            if (obj instanceof List<?>) {
                result.addAll(flattenNestedList((List<?>)obj));
            } else {
                result.add(obj);
            }
        }
        return result;
    }

    // 計算巢狀清單最大深度
    public static int maxDepth(List<?> nestedList) {
        int max = 1;
        for (Object obj : nestedList) {
            if (obj instanceof List<?>) {
                max = Math.max(max, 1 + maxDepth((List<?>)obj));
            }
        }
        return max;
    }
}
