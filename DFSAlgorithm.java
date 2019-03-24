import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DFSAlgorithm {
 static final int MAX = 100;
 int G[][] = new int[MAX][MAX];
 int n = 0; 
 boolean chuaxet[] = new boolean[MAX];
  void Init() {
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
 

 void DFS(int v) {
  int u;
  System.out.print(v + " ");
  chuaxet[v] = false;
  for (u = 1; u <= n; u++) {
   if (G[v][u] == 1 && chuaxet[u])
    DFS(u);
  }
 }

 public DFSAlgorithm() {
  Init();
  for (int i = 1; i <= n; i++){
   chuaxet[i] = true;
  }
  System.out.print("\n");
  for (int i = 1; i <= n; i++)
   if (chuaxet[i]){
    DFS(i);
   }
 }

 public static void main(String[] args) {
  System.out.println("DFS");
  new DFSAlgorithm();
 }
}