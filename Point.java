
public class Point {
	
	private int setNumber;
	private int pointNumber;
	private int xCoordinate;
	private int yCoordinate;
	
	public Point (int pointNumber, int x, int y) {
		this.setNumber = pointNumber;
		this.pointNumber = pointNumber;
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public void changeSetNumber (int newSetNumber) {
		this.setNumber = newSetNumber;
	}
	
	public int getSetNumber () {
		return this.setNumber;
	}
	
	public int getPointNumber () {
		return this.pointNumber;
	}
	
	public int getXCoordinate () {
		return this.xCoordinate;
	}
	
	public int getYCoordinate () {
		return this.yCoordinate;
	}
}
