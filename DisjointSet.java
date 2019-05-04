
public class DisjointSet {

	private Point[] sets;
	
	public DisjointSet (int size) {
		this.sets = new Point[size];
	}//constructor
	
	

	public boolean union (Point a, Point b) {
		boolean pointsJoined = false;
		

		if (a.getSetNumber() == b.getSetNumber())
			pointsJoined = false;
		else if (a.getSetNumber() < b.getSetNumber()) {
			b.changeSetNumber(a.getSetNumber());
			pointsJoined = true;
		} else if (a.getSetNumber() > b.getSetNumber()) {
			a.changeSetNumber(b.getSetNumber());
			pointsJoined = true;
		}
			
		return pointsJoined;
	}
	
	public int find (Point a) {
		return a.getSetNumber();
	}
	
	public void insert (Point a, int i) {
		this.sets[i] = a;
	}
	
	public Point getPointAtIndex (int i) {
		return this.sets[i];
	}
	
}

