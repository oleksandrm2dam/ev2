package rurales;

import java.util.Arrays;

public class Apartment {
	
	private String name;
	private String address;
	private String phoneNumber;
	private Room[] rooms;
	
	public Apartment(String name, String address, String phoneNumber, Room[] rooms) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.rooms = rooms;
	}
	
	public Apartment(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.rooms = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Apartment [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", rooms="
				+ Arrays.toString(rooms) + "]";
	}
	
}
