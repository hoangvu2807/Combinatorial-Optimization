import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args){
		try{
			FileReader f = new FileReader("io.txt");
			BufferedReader br = new BufferedReader(f);
			Graph g = new Graph(Integer.parseInt(br.readLine()));
			String line;
			int i = 0; 
			while ((line = br.readLine()) != null) {
				String [] value = line.split(" ");
				for(int j = 0 ; j < value.length ; j++){ 
					if(value[j].equals("M") == false  && value[j].equals("0") == false ){
						g.addEdge(i,j);
					}
				}
				i++;
			}
			System.out.println(g.toString());
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
	
	