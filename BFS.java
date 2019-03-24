import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private static final String pathIn = "graph.inp";
    private static final String pathOut = "graph.out";
    private static int n, m, start, end;
    private static int[][] arrays;
    private static  PrintWriter printWriter;
    public static int[] free;
    public static int[] back;
    public static Queue queues = new LinkedList<>();
    public static StringBuffer str = new StringBuffer();

    public static void BFS(int s) {
        queues.add(s);
        free[s] = 1;
        while (!queues.isEmpty()) {
            int u = queues.poll();
            for (int v = 0; v < n; v++) {
                if(free[v] == 0 && arrays[u][v] != 0) {
                    free[v] = 1;
                    back[v] = u;
                    queues.add(v);
                    str.append(", "+v);
                }
            }
        }
}
}