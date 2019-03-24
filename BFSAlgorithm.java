import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class BFSAlgorithm {
 static final int MAX = 100;
 int G[][] = new int[MAX][MAX];
 boolean chuaxet[] = new boolean[MAX];
 int QUEUE[] = new int[MAX]; 
 int n;
 
 void init() {
  File file = new File(getClass().getResource("io.txt").getPath());
  BufferedReader reader = null;
  try {
   reader = new BufferedReader(new FileReader(file));
   n = Integer.parseInt(reader.readLine());
   for (int i = 1; i <= n; i++) {
    String[] aLineOfMatrix = reader.readLine().split("\\s+");
    for (int j = 1; j <= n; j++) {
     G[i][j] = Integer.parseInt(aLineOfMatrix[j - 1]);
    }
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   if (reader != null)
    try {
     reader.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
  }
 }
 
 void BFS(int v) {
  int u, dauQ, cuoiQ, j;
  dauQ = 1;
  cuoiQ = 1;
  QUEUE[cuoiQ] = v;
  chuaxet[v] = false;
  
  while (dauQ <= cuoiQ) {
   u = QUEUE[dauQ];
   System.out.print(u + " ");
   dauQ = dauQ + 1; 
   for (j = 1; j <= n; j++) {
    if (G[u][j] == 1 && chuaxet[j]) {
     cuoiQ = cuoiQ + 1;
     QUEUE[cuoiQ] = j;
     chuaxet[j] = false;
    }
   }
  }
 }
 
 public BFSAlgorithm() {
  init();
  for (int i = 1; i <= n; i++) {
   chuaxet[i] = true;
  }
  System.out.print("\n");
  for (int i = 1; i <= n; i++)
   if (chuaxet[i]) {
    BFS(i);
   }
 }
 
 public static void main(String[] args) {
  BFSAlgorithm bfs = new BFSAlgorithm();
 }
}