package dao.machine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.machine.Machine;
import models.machine.MachineType;

import java.util.ArrayList;

public class DaoMachine {

	private static DaoMachine instance;
	private DataSource ds;

	public static DaoMachine getInstance() {
		if (instance == null) {
			instance = new DaoMachine();
		}
		return instance;
	}

	@SuppressWarnings("unused")
	private DaoMachine() {

		try {
			InitialContext cxt = new InitialContext();
			if (cxt == null) {
			}
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mysql");
			if (ds == null) {
			}
		} catch (NamingException e) {
		}
	}

	private final String MACHINE_ID = "machine_id";
	private final String MACHINE_TYPE = "machine_type";
	private final String MACHINE_MODEL = "machine_model";
	private final String MACHINE_UNITS = "stock_units";
	private final String MANUFACTURER_NAME = "manufacturer_name";
	private final String YEAR_PRODUCED = "year_produced";
	private final String IMG_PATH = "img_path";

// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECT_MACHINES = "SELECT * FROM machines";
	private static String SELECT_MACHINE_BY_ID = "SELECT * FROM `machines` WHERE `machine_id` = ?";
	private static String SELECT_MACHINE_BY_MODEL = "SELECT * FROM `machines` WHERE `machine_model` = ?";
	private static String SELECT_MACHINE_BY_TYPE = "SELECT * FROM `machines` WHERE `machine_type` = ?";
	private static String SELECT_MACHINES_BY_USER = "SELECT machines.machine_id, machine_type, machine_model, manufacturer_name, year_produced, img_path, user_rent_machine.user_id FROM machines JOIN user_rent_machine ON machines.machine_id = user_rent_machine.machine_id WHERE user_id=?";

	private static String INSERT_MACHINE = "INSERT INTO `machines`(`machine_type`, `machine_model`,"
			+ "`stock_units`, `manufacturer_name`, `year_produced`, `img_path`) VALUES (?,?,?,?,?,?)";

	private static String DELETE_MACHINE_BY_ID = "DELETE FROM `machines` WHERE `machine_id` = ?";

	private static String UPDATE_MACHINE_BY_ID = "UPDATE `machines` SET "
			+ "`machine_type`=?,`machine_model`=?,`stock_units`=?,`manufacturer_name`=?,`year_produced`=?, `img_path`=? WHERE `machine_id` = ? ";

	private static String UPDATE_MACHINE_UNITS = "UPDATE `machines` SET `stock_units`=? WHERE `machine_id` = ? ";

	// selectMachines
	public ArrayList<Machine> selectMachines() {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<Machine> ul = new ArrayList<Machine>();
		Machine machine = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_MACHINES);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			// pstm.setString(1, ime);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while (rs.next()) { // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				machine = new Machine();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				machine.setId(rs.getInt(MACHINE_ID));
				machine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				machine.setModel(rs.getString(MACHINE_MODEL));
				machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				machine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				machine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				machine.setImgPath(rs.getString(IMG_PATH));

				ul.add(machine);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		// System.out.println(ul);
		return ul;
	}
	// end of selectMachines

	// selectMachineById
	public Machine selectMachineById(int id) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		Machine machine = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_MACHINE_BY_ID);

			pstm.setInt(1, id);
			pstm.execute();

			rs = pstm.getResultSet();

			if (rs.next()) {
				machine = new Machine();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				machine.setId(rs.getInt(MACHINE_ID));
				machine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				machine.setModel(rs.getString(MACHINE_MODEL));
				machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				machine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				machine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				machine.setImgPath(rs.getString(IMG_PATH));

			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return machine;
	}
	// end of selectMachineById

	// selectMachineByModel
	public Machine selectMachineByModel(String model) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		Machine machine = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_MACHINE_BY_MODEL);

			pstm.setString(1, model);
			pstm.execute();

			rs = pstm.getResultSet();

			if (rs.next()) {
				machine = new Machine();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				machine.setId(rs.getInt(MACHINE_ID));
				machine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				machine.setModel(rs.getString(MACHINE_MODEL));
				machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				machine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				machine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				machine.setImgPath(rs.getString(IMG_PATH));

			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return machine;
	}
	// end of selectMachineByModel

	// selectMachineByType
	public ArrayList<Machine> selectMachineByType(MachineType type) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<Machine> ul = new ArrayList<Machine>();
		Machine machine = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_MACHINE_BY_TYPE);

			pstm.setString(1, type.toString());
			pstm.execute();

			rs = pstm.getResultSet();

			while (rs.next()) {
				machine = new Machine();

				machine.setId(rs.getInt(MACHINE_ID));
				machine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				machine.setModel(rs.getString(MACHINE_MODEL));
				machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				machine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				machine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				machine.setImgPath(rs.getString(IMG_PATH));

				ul.add(machine);

			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ul;
	}
	// end of selectMachineByType

	// insertMachine
	public void insertMachine(Machine machine) {

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERT_MACHINE);
System.out.println("U DaoMachine insert linija 261");
			pstm.setString(1, machine.getType().toString());
			pstm.setString(2, machine.getModel());
			pstm.setInt(3, machine.getStockUnits());
			pstm.setString(4, machine.getManufacturerName());
			pstm.setInt(5, machine.getYearProduced());
			pstm.setString(6, machine.getImgPath());
			pstm.execute();

//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// end of insertMachine

	// deleteMachineByID
	public void deleteMachineByID(int id) {

		Connection con = null;
		PreparedStatement pstm = null;

		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_MACHINE_BY_ID);

			pstm.setInt(1, id);
			pstm.execute();

//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// end of deleteMachineByID

	// updateMachine
	public void updateMachine(Machine machine) {

		Connection con = null;
		PreparedStatement pstm = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATE_MACHINE_BY_ID);

			pstm.setString(1, machine.getType().toString());
			pstm.setString(2, machine.getModel());
			pstm.setInt(3, machine.getStockUnits());
			pstm.setString(4, machine.getManufacturerName());
			pstm.setInt(5, machine.getYearProduced());
			pstm.setString(6, machine.getImgPath());
			pstm.setInt(7, machine.getId());
			pstm.execute();

//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// end of updateMachine

	// updateMachineUnits
	public void updateMachineUnits(Machine machine) {

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATE_MACHINE_UNITS);

			System.out.println("u dao machine dobijeno:" + machine.getStockUnits());
			pstm.setInt(1, machine.getStockUnits());
			pstm.setInt(2, machine.getId());
			pstm.execute();

//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// end of updateMachineUnits

	// selectMachinesByUser
	public ArrayList<Machine> selectMachinesByUser(int userId) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<Machine> ul = new ArrayList<Machine>();
		Machine machine = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_MACHINES_BY_USER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setInt(1, userId);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while (rs.next()) { // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				machine = new Machine();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				machine.setId(rs.getInt(MACHINE_ID));
				machine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				machine.setModel(rs.getString(MACHINE_MODEL));
				// machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				machine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				machine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				machine.setImgPath(rs.getString(IMG_PATH));
				// System.out.println(machine);
				ul.add(machine);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		// System.out.println(ul);
		System.out.println(ul);
		return ul;
	}
	// end of selectMachinesByUser

}
