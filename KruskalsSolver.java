import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class KruskalsSolver {

	
	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\Miles\\workspace\\COP3503 - Computer Science II\\src\\homework2Kruskals\\input.txt"));
		

		input.mark(1);
		

		while (input.read() != '0') {

			input.reset();
	
	
			int numOfPoints = 0;
			numOfPoints = Integer.parseInt(input.readLine());
			
	
			DisjointSet points = new DisjointSet(numOfPoints);
			
			int i;
	
			for (i = 0; i < numOfPoints; i++) {
				//read in the next line of the input file as a string
				String line = input.readLine();
		
				String[] tokens = line.split(" ");
		
				Point p = new Point(i, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
				points.insert(p, i);

			}
			
			double edgeWeightNeeded = 0;

			while (allPointsConnected(points, numOfPoints) == false) {
				edgeWeightNeeded = edgeWeightNeeded + findBestEdgeWeight(points, numOfPoints);
			}
			

			
			double answer = 0;
			answer = roundToTwoPlaces (edgeWeightNeeded);
			System.out.println(answer);
			
			try {
				input.mark(1); //mark the start of the next test case.
			} catch (IOException e) {
				System.out.println("Caught IOException");
			}
			
		}
		
	}

	
	public static double roundToTwoPlaces (double number) {
		DecimalFormat df = new DecimalFormat("##.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return Double.parseDouble(df.format(number));
	}
	
	public static boolean allPointsConnected (DisjointSet points, int numOfPoints) {
		int setTest = -1; //used to test to see whether all points are part of the same set.
		int shareASet = 0; //represents the amount of points that share a set.
		boolean allPointsConnected = false;
		

		for (int i = 0; i < numOfPoints; i++) {

			if (setTest == -1) {
				setTest = points.find(points.getPointAtIndex(i));
				shareASet++;
			} else {

				if (setTest == points.find(points.getPointAtIndex(i)))
					shareASet++;
			}
		}
		
		if (shareASet == numOfPoints)
			allPointsConnected = true;
		
		return allPointsConnected;
	}
	
	public static double findBestEdgeWeight (DisjointSet points, int numOfPoints) {
		boolean setsJoined = false;
		int edgeStart = -1;
		int edgeEnd = -1;
		double bestEdgeWeight = -1;
		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfPoints; j++) {
				
		
				if (i != j && points.find(points.getPointAtIndex(i)) != points.find(points.getPointAtIndex(j))) {
		
					if (bestEdgeWeight == -1 || bestEdgeWeight > computeDistance(points.getPointAtIndex(i), points.getPointAtIndex(j))) {
						bestEdgeWeight = computeDistance(points.getPointAtIndex(i), points.getPointAtIndex(j));
						edgeStart = i;
						edgeEnd = j;
					} 
				}
				
			}
		}
		
		setsJoined = points.union(points.getPointAtIndex(edgeStart), points.getPointAtIndex(edgeEnd));
		return bestEdgeWeight;
	}
	
	public static double computeDistance (Point a, Point b) {
		double part1 = 0;
		part1 = ((a.getXCoordinate() - b.getXCoordinate())*(a.getXCoordinate() - b.getXCoordinate())) + ((a.getYCoordinate() - b.getYCoordinate())*(a.getYCoordinate() - b.getYCoordinate()));
		return Math.sqrt(part1);
	}
	
}
