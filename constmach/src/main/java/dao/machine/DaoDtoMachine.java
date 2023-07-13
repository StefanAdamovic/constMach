package dao.machine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.DtoRentMachine;
import models.machine.MachineType;

import java.util.ArrayList;

public class DaoDtoMachine {

	private static DaoDtoMachine instance;
	private DataSource ds;

	public static DaoDtoMachine getInstance() {
		if (instance == null) {
			instance = new DaoDtoMachine();
		}
		return instance;
	}

	@SuppressWarnings("unused")
	private DaoDtoMachine() {

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

	private final String DTO_ID = "id";
	private final String USER_ID = "user_id";
	private final String MACHINE_ID = "machine_id";
	private final String MACHINE_TYPE = "machine_type";
	private final String MACHINE_MODEL = "machine_model";
	private final String RENTED_UNITS = "units_rent";
	private final String MANUFACTURER_NAME = "manufacturer_name";
	private final String YEAR_PRODUCED = "year_produced";
	private final String IMG_PATH = "img_path";
	private final String RENT_STATUS = "rent_status";
	private final String START_DATE = "start_date";
	private final String END_DATE = "end_date";

// DEFINICIJA KONEKCIONIH STRINGOVA

	private static String SELECT_MACHINES_BY_USER = "SELECT machines.machine_id, machine_type, machine_model, manufacturer_name, year_produced, img_path, user_rent_machine.user_id,"
			+ "user_rent_machine.rent_status, user_rent_machine.start_date, user_rent_machine.end_date, user_rent_machine.units_rent, user_rent_machine.id FROM machines JOIN user_rent_machine ON machines.machine_id = user_rent_machine.machine_id WHERE user_id=?";

	private static String INSERT_MACHINE_RENTING = "INSERT INTO `user_rent_machine`(`user_id`, `machine_id`, `rent_status`,  `units_rent`) VALUES (?,?,?,?)";

	private static String UPDATE_RENTING_STATUS = "UPDATE user_rent_machine SET rent_status=?,end_date=? WHERE id = ?";

	// selectDtoMachinesByUser
	public ArrayList<DtoRentMachine> selectDtoMachinesByUser(int userId) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<DtoRentMachine> ul = new ArrayList<DtoRentMachine>();
		DtoRentMachine dtoMachine = null;

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
				dtoMachine = new DtoRentMachine();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				dtoMachine.setMachineId(rs.getInt(MACHINE_ID));
				dtoMachine.setType(MachineType.valueOf((rs.getString(MACHINE_TYPE).toUpperCase())));
				dtoMachine.setModel(rs.getString(MACHINE_MODEL));
				// machine.setStockUnits(rs.getInt(MACHINE_UNITS));
				dtoMachine.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				dtoMachine.setYearProduced(rs.getInt(YEAR_PRODUCED));
				dtoMachine.setImgPath(rs.getString(IMG_PATH));
				dtoMachine.setRentStatus(rs.getString(RENT_STATUS));
				dtoMachine.setEndDate(rs.getString(END_DATE));
				dtoMachine.setStartDate(rs.getString(START_DATE));
				dtoMachine.setUnitsRented(rs.getInt(RENTED_UNITS));
				dtoMachine.setUserId(rs.getInt(USER_ID));
				dtoMachine.setId(rs.getInt(DTO_ID));
				// System.out.println(machine);
				ul.add(dtoMachine);
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
		//System.out.println(ul);
		return ul;
	}
	// end of selectMachinesByUser

	// insertMachineRenting
	public void insertMachineRent(int userId, int machineId, String rentStatus, int unitsRenting) {

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERT_MACHINE_RENTING);

			pstm.setInt(1, userId);
			pstm.setInt(2, machineId);
			pstm.setString(3, rentStatus.toUpperCase());
			pstm.setInt(4, unitsRenting);
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
	// end of insertMachineRenting

	// insertMachineRenting
	public void updateRentStatus(int rentId, String rentStatus) {

		Connection con = null;
		PreparedStatement pstm = null;

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATE_RENTING_STATUS);

			//System.out.println("update status dao usao");
			//System.out.println(rentId + "- - - -" + rentStatus);
			pstm.setString(1, rentStatus.toUpperCase());
			pstm.setTimestamp(2, timestamp);
			pstm.setInt(3, rentId);
			pstm.execute();
			//System.out.println("update status odradio execute");

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
	// end of insertMachineRenting

}
