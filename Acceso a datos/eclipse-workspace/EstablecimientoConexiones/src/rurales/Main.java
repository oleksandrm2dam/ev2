package rurales;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {
	
	private static Connection connection;
	private static Scanner scanner;
	private static ObjectContainer db;
	
	public static void main(String[] args) {
		
		try {
			scanner = new Scanner(System.in);
			
			String dbOptions = "Choose a database:\n";
			dbOptions += "1) SQLite\n";
			dbOptions += "2) Apache Derby\n";
			dbOptions += "3) H2\n";
			dbOptions += "4) HSQLDB\n";
			dbOptions += "5) DB4O\n";
			System.out.println(dbOptions);
			String answ = scanner.nextLine();
			
			switch(answ) {
			case "1":
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection(
						"jdbc:sqlite:databases/rurales/sqlite/rurales.db",
						"",
						""
						);
				break;
			case "2":
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				connection = DriverManager.getConnection(
						"jdbc:derby:databases/rurales/derby/rurales",
						"",
						""
						);
				break;
			case "3":
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection(
						"jdbc:h2:./databases/rurales/h2",
						"",
						""
						);
				break;
			case "4":
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection(
						"jdbc:hsqldb:file:databases/rurales/hsqldb",
						"",
						""
						);
				break;
			case "5":
				db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "databases/rurales/db4o/rurales.yap");
				//createObjects(db);
				db4oMenu();
				return;
			default:
				System.out.println("Not valid.");
				return;
			}
			
			do {
				printMenu();
				answ = scanner.nextLine();
				switch(answ) {
					case "1":
						System.out.println("Write the price: ");
						try {
							float price = Float.parseFloat(scanner.nextLine());
							ArrayList<Room> rooms = roomsCheaperThan(price);
							if(rooms != null) {
								System.out.println(rooms.toString());
							}
						} catch (NumberFormatException e) {
							System.out.println("Incorrect number.");
						}
						break;
					case "2":
						System.out.println("Write the name of the apartment:");
						String name = scanner.nextLine();
						String phoneNumber = getPhoneNumber(name);
						if(phoneNumber == null) {
							System.out.println("Not found.");
						} else {
							System.out.println("Phone number: " + phoneNumber);
						}
						break;
					case "3":
						ArrayList<Apartment> list = hasRoomsWithToilet();
						for(Apartment ap : list) {
							System.out.println(ap.toString());
						}
						break;
					case "4":
						System.out.println("Write the name of the apartment:");
						String name4 = scanner.nextLine();
						int count = numberOfRooms(name4, "single");
						System.out.println("Number of single rooms: " + count);
						break;
					case "5":
						System.out.println("Write the name of the apartment:");
						String name5 = scanner.nextLine();
						count = numberOfRooms(name5, "double");
						System.out.println("Number of double rooms: " + count);
						break;
					case "6":
						System.out.println("Write the name of the apartment:");
						String name6 = scanner.nextLine();
						count = numberOfRooms(name6, "triple");
						System.out.println("Number of triple rooms: " + count);
						break;
					case "7":
						System.out.println("Write the name of the apartment:");
						name = scanner.nextLine();
						String address = addressOfAnAparment(name);
						if(address == null) {
							System.out.println("Not found.");
						} else {
							System.out.println("Address: " + address);
						}
						break;
					case "8":
						list = allApartments();
						for(Apartment ap : list) {
							System.out.println(ap.toString());
						}
						break;
					case "9":
						showTables();
						break;
					case "10":
						System.out.println("BYE!");
						break;	
					default:
						System.out.println("Option not valid.");
						break;
				}
			} while(!answ.equals("9"));
			
			scanner.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void printMenu() {
		String menu = "---MENU---\n";
		menu += "1) Search for all rooms cheaper than...\n";
		menu += "2) Get the phone number of an apartment.\n";
		menu += "3) Search for apartments that have rooms with bathrooms.\n";
		menu += "4) Get the number of single rooms in an apartment.\n";
		menu += "5) Get the number of double rooms in an apartment.\n";
		menu += "6) Get the number of triple rooms in an apartment.\n";
		menu += "7) Get the address of an aparment.\n";
		menu += "8) Get all the apartments.\n";
		menu += "9) Show all tables in the database.\n";
		menu += "10) EXIT.\n";
		System.out.println(menu);
	}
	
	private static ArrayList<Room> roomsCheaperThan(float price) {
		ArrayList<Room> output = new ArrayList<Room>();
		String sql = "SELECT * FROM rooms WHERE price < ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setFloat(1, price);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Room(
						rs.getString("type"), 
						rs.getBoolean("hasBathroom"), 
						rs.getFloat("price")
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	private static String getPhoneNumber(String name) {
		String sql = "SELECT phoneNumber FROM apartments WHERE name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<Apartment> hasRoomsWithToilet() {
		ArrayList<Apartment> output = new ArrayList<Apartment>();
		String sql = "SELECT * FROM apartments WHERE id IN "
				+ "(SELECT apartmentId FROM rooms WHERE hasBathroom = true)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Apartment(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}
	
	private static int numberOfRooms(String name, String type) {
		String sql = "SELECT COUNT(id) FROM rooms WHERE type = ? AND apartmentId = "
				+ "(SELECT id FROM apartments WHERE name = ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static String addressOfAnAparment(String name) {
		String sql = "SELECT address FROM apartments WHERE name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<Apartment> allApartments() {
		ArrayList<Apartment> output = new ArrayList<Apartment>();
		String sql = "SELECT * FROM apartments";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Apartment(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}
	
	// DB40 methods
	public static void createObjects(ObjectContainer db) {
		Room r1 = new Room("single", false, 75.99f);
		Room r2 = new Room("double", false, 25.00f);
		Room r3 = new Room("double", false, 34.99f);
		Room r4 = new Room("triple", false, 20.35f);
		Room r5 = new Room("single", true, 72.25f);
		Room r6 = new Room("triple", true, 35.50f);
		Room r7 = new Room("single", false, 99.99f);
		Room r8 = new Room("single", false, 66.00f);
		Room r9 = new Room("double", false, 50.55f);
		Room r10 = new Room("double", true, 49.95f);
		Room r11 = new Room("double", true, 75.99f);
		Room r12 = new Room("single", false, 55.00f);
		Room r13 = new Room("single", false, 55.00f);
		Room r14 = new Room("single", true, 20.99f);
		
		Room[] rooms1 = new Room[] {r1, r2, r3};
		Room[] rooms2 = new Room[] {r4, r5};
		Room[] rooms3 = new Room[] {r6, r7, r8};
		Room[] rooms4 = new Room[] {r9, r10, r11, r12};
		Room[] rooms5 = new Room[] {r13, r14};
		
		Apartment a1 = new Apartment("Apartamento1", "Calle1", "917851121", rooms1);
		Apartment a2 = new Apartment("Apartamento2", "Calle2", "622087555", rooms2);
		Apartment a3 = new Apartment("Apartamento3", "Calle3", "915443212", rooms3);
		Apartment a4 = new Apartment("Apartamento4", "Calle4", "917852323", rooms4);
		Apartment a5 = new Apartment("Apartamento5", "Calle5", "915554443", rooms5);
		
		db.store(a1);
		db.store(a2);
		db.store(a3);
		db.store(a4);
		db.store(a5);
	}
	
	public static void db4oMenu() {
		String answ;
		Apartment ap;
		ObjectSet<Object> result;
		do {
			printMenu();
			answ = scanner.nextLine();
			switch (answ) {
			case "1":
				// Buscar habitaciones que tengan un precio inferior al indicado por el usuario.
				System.out.println("Write the price: ");
				float price = Float.parseFloat(scanner.nextLine());
				
				ap = new Apartment(null, null, null, null);
				result = db.queryByExample(ap);
				while(result.hasNext()) {
					Apartment current = (Apartment) result.next();
					for(int i = 0; i < current.getRooms().length; ++i) {
						if(current.getRooms()[i].getPrice() < price) {
							System.out.println("Apartment: " + current.getName() + ". " + current.getRooms()[i].toString());
						}
					}
				}
				break;
			case "2":
				// Consultar el n�mero de tel�fono de un alojamiento indicado por el usuario.
				System.out.println("Write the aparment's name: ");
				String name = scanner.nextLine();
				ap = new Apartment(name, null, null, null);
				result = db.queryByExample(ap);
				if(result.hasNext()) {
					Apartment current = (Apartment) result.next();
					System.out.println(current.getPhoneNumber());
				} else {
					System.out.println("Apartment not found.");
				}
				break;
			case "3":
				// Buscar todos los alojamientos que tengan alguna habitaci�n con ba�o.
				ap = new Apartment(null, null, null, null);
				result = db.queryByExample(ap);
				while(result.hasNext()) {
					Apartment current = (Apartment) result.next();
					boolean hasBathroom = false;
					for(int i = 0; i < current.getRooms().length && !hasBathroom; ++i) {
						if(current.getRooms()[i].isHasBathroom()) {
							System.out.println(current.toString());
							hasBathroom = true;
						}
					}
				}
				break;
			case "4":
				// Consultar el n�mero de habitaciones individuales de un determinado alojamiento.
				System.out.println("Write the aparment's name: ");
				name = scanner.nextLine();
				ap = new Apartment(name, null, null, null);
				result = db.queryByExample(ap);
				if(result.hasNext()) {
					numberOfRoomsDb4o("single", result);
				} else {
					System.out.println("Apartment not found.");
				}
				break;
			case "5":
				// Consultar el n�mero de habitaciones dobles de un determinado alojamiento.
				System.out.println("Write the aparment's name: ");
				name = scanner.nextLine();
				ap = new Apartment(name, null, null, null);
				result = db.queryByExample(ap);
				if(result.hasNext()) {
					numberOfRoomsDb4o("double", result);
				} else {
					System.out.println("Apartment not found.");
				}
				break;
			case "6":
				// Consultar el n�mero de habitaciones triples de un determinado alojamiento.
				System.out.println("Write the aparment's name: ");
				name = scanner.nextLine();
				ap = new Apartment(name, null, null, null);
				result = db.queryByExample(ap);
				if(result.hasNext()) {
					numberOfRoomsDb4o("triple", result);
				} else {
					System.out.println("Apartment not found.");
				}
				break;
			case "7":
				// Consultar la direcci�n de un determinado alojamiento.
				System.out.println("Write the aparment's name: ");
				name = scanner.nextLine();
				ap = new Apartment(name, null, null, null);
				result = db.queryByExample(ap);
				if(result.hasNext()) {
					Apartment current = (Apartment) result.next();
					System.out.println(current.getAddress());
				} else {
					System.out.println("Apartment not found.");
				}
				break;
			case "8":
				// Mostrar todos los alojamientos de la comunidad, con su direcci�n y su tel�fono.
				ap = new Apartment(null, null, null, null);
				result = db.queryByExample(ap);
				while(result.hasNext()) {
					Apartment current = (Apartment) result.next();
					System.out.println(current.toString());
				}
				break;
			case "9":
				System.out.println("BYE!");
				break;
			default:
				System.out.println("Option not valid.");
				break;	
			}
		} while (!answ.equals("9"));
		db.close();
		scanner.close();
	}
	
	private static void numberOfRoomsDb4o(String type, ObjectSet<Object> result) {
		Apartment current = (Apartment) result.next();
		int count = 0;
		for(int i = 0; i < current.getRooms().length; ++i) {
			if(current.getRooms()[i].getType().equals(type)) {
				++count;
			}
		}
		System.out.println("Number of " + type + " rooms: " + count);
	}
	
	// DatabaseMetaData
	private static void showTables() {
		try {
			DatabaseMetaData dbMeta = connection.getMetaData();
			ResultSet rs = dbMeta.getTables(null, null, null, null);
			
			while(rs.next()) {
				String catalog = rs.getString("TABLE_CAT");
				String schema = rs.getString("TABLE_SCHEM");
				String tableName = rs.getString("TABLE_NAME");
				String tableType = rs.getString("TABLE_TYPE");
				System.out.println("---TABLE---");
				System.out.println("Catalog: " + catalog + ". Schema: " + schema + 
						". Table name: " + tableName + ". Table type: " + tableType + ".");
				
				System.out.println("Columns:");
				ResultSet rsCol = dbMeta.getColumns(catalog, schema, tableName, "%");
				while(rsCol.next()) {
					String colName = rsCol.getString("COLUMN_NAME");
					String colType = rsCol.getString("DATA_TYPE");
					String colSize = rsCol.getString("COLUMN_SIZE");
					String colNullable = rsCol.getString("NULLABLE");
					System.out.println("Name: " + colName + ". Type: " + colType + ". Size: " + colSize + ". Nullable: " + colNullable + ".");
				}
				
				ResultSet rsPK = dbMeta.getPrimaryKeys(catalog, schema, tableName);
				if(rsPK.next()) {
					System.out.println("Primary keys:");
					String colName = rsPK.getString("COLUMN_NAME");
					System.out.println(colName);
					while(rsPK.next()) {
						colName = rsPK.getString("COLUMN_NAME");
						System.out.println(colName);
					}
				} else {
					System.out.println("No primary keys.");
				}
				
				ResultSet rsExp = dbMeta.getExportedKeys(catalog, schema, tableName);
				if(rsExp.next()) {
					System.out.println("Exported primary keys:");
					System.out.println("Name: " + rsExp.getString("PKCOLUMN_NAME") + ". To table: " + rsExp.getString("FKTABLE_NAME") + ".");
					while(rsExp.next()) {
						System.out.println("Name: " + rsExp.getString("PKCOLUMN_NAME") + ". To table: " + rsExp.getString("FKTABLE_NAME") + ".");
					}
				}
				
				ResultSet rsImp = dbMeta.getImportedKeys(catalog, schema, tableName);
				if(rsImp.next()) {
					System.out.println("Foreign keys:");
					
					System.out.println("FK Name: " + rsImp.getString("FKCOLUMN_NAME"));
					System.out.println("Referenced table's catalog: " + rsImp.getString("PKTABLE_CAT"));
					System.out.println("Referenced table's name: " + rsImp.getString("PKTABLE_NAME"));
					System.out.println("Referenced table's scheme: " + rsImp.getString("PKTABLE_SCHEM"));
					System.out.println("Referenced column's name: " + rsImp.getString("PKCOLUMN_NAME"));
					while(rsImp.next()) {
						System.out.println("FK Name: " + rsImp.getString("FKCOLUMN_NAME"));
						System.out.println("Referenced table's catalog: " + rsImp.getString("PKTABLE_CAT"));
						System.out.println("Referenced table's name: " + rsImp.getString("PKTABLE_NAME"));
						System.out.println("Referenced table's scheme: " + rsImp.getString("PKTABLE_SCHEM"));
						System.out.println("Referenced column's name: " + rsImp.getString("PKCOLUMN_NAME"));
					}
				} else {
					System.out.println("No foreign keys.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
