package rurales;

public class Room {
	
	private String type; // Single, double, triple
	private boolean hasBathroom;
	private float price;
	
	public Room(String type, boolean hasBathroom, float price) {
		this.type = type;
		this.hasBathroom = hasBathroom;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasBathroom() {
		return hasBathroom;
	}

	public void setHasBathroom(boolean hasBathroom) {
		this.hasBathroom = hasBathroom;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room [type=" + type + ", hasBathroom=" + hasBathroom + ", price=" + price + "]";
	}
	
}
