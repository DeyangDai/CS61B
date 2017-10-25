package board;

public class Connection {
	private Square origin;
	private Square destination;
	private DirectionEnum direction;
	
	public Connection(Square origin, Square destination, DirectionEnum direction){
		this.origin = origin;
		this.destination = destination;
		this.direction = direction;
	}

	public Square getOrigin() {
		return origin;
	}

	public Square getDestination() {
		return destination;
	}

	public DirectionEnum getDirection() {
		return direction;
	}
	
	
}
