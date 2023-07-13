package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.user.User;
import models.user.UserType;

import java.util.ArrayList;

public class DaoUser {

	private static DaoUser instance;
	private DataSource ds;

	public static DaoUser getInstance() {
		if (instance == null) {
			instance = new DaoUser();
		}
		return instance;
	}

	@SuppressWarnings("unused")
	private DaoUser() {

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

	private final String USER_ID = "user_id";
	private final String USER_TYPE = "user_type";
	private final String EMAIL = "email";
	private final String PASSWORD = "password";
	private final String FIRST_NAME = "first_name";
	private final String LAST_NAME = "last_name";
	private final String YEAR_BORN = "year_born";

// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECT_USERS = "SELECT * FROM users";
	private static String SELECT_USER_BY_ID = "SELECT * FROM `users` WHERE `user_id` = ?";
	private static String SELECT_USER_BY_EMAIL = "SELECT * FROM `users` WHERE `email` = ?";
	private static String SELECT_USER_BY_TYPE = "SELECT * FROM `users` WHERE `user_type` = ?";

	private static String INSERT_USER = "INSERT INTO `users`"
			+ "(`user_type`, `email`, `password`, `first_name`, `last_name`, `year_born`)" + " VALUES (?,?,?,?,?,?)";

	private static String DELETE_USER_BY_ID = "DELETE FROM `users` WHERE `user_id` = ?";
	private static String DELETE_USER_BY_EMAIL = "DELETE FROM `users` WHERE `email` = ?";

	private static String UPDATE_USER_BY_ID = "UPDATE `users` " + "SET `user_type`=?,`email`=?,`password`=?,"
			+ "`first_name`=?,`last_name`=?,`year_born`=? WHERE user_id =?";

	// selectUser
	public ArrayList<User> selectUsers() {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<User> ul = new ArrayList<User>();
		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			// pstm.setString(1, ime);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while (rs.next()) { // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				user.setId(rs.getInt(USER_ID));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
				user.setFirstName(rs.getString(FIRST_NAME));
				user.setLastName(rs.getString(LAST_NAME));
				user.setYearBorn(rs.getInt(YEAR_BORN));
				// user.setType((rs.getString(USER_TYPE)));
				// String test = rs.getString(USER_TYPE).toUpperCase();
				// System.out.println(test);
				// System.out.println("- - - - - -");
				// System.out.println(UserType.valueOf(test));
				user.setType(UserType.valueOf((rs.getString(USER_TYPE).toUpperCase())));

				// System.out.println(user);
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA
				ul.add(user);
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
	// end of selectUser

	// selectUserById
	public User selectUserById(int id) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_ID);

			pstm.setInt(1, id);
			pstm.execute();

			rs = pstm.getResultSet();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt(USER_ID));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
				user.setFirstName(rs.getString(FIRST_NAME));
				user.setLastName(rs.getString(LAST_NAME));
				user.setYearBorn(rs.getInt(YEAR_BORN));
				user.setType(UserType.valueOf((rs.getString(USER_TYPE).toUpperCase())));
				// System.out.println(user);

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

		return user;
	}
	// end of selectUserById

	// selectUserByEmail
	public User selectUserByEmail(String email) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_EMAIL);

			pstm.setString(1, email);
			pstm.execute();

			rs = pstm.getResultSet();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt(USER_ID));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
				user.setFirstName(rs.getString(FIRST_NAME));
				user.setLastName(rs.getString(LAST_NAME));
				user.setYearBorn(rs.getInt(YEAR_BORN));
				user.setType(UserType.valueOf((rs.getString(USER_TYPE).toUpperCase())));
				// System.out.println(user);

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

		return user;
	}
	// end of selectUserByEmail

	// selectUserByType
	public ArrayList<User> selectUsersByType(UserType type) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<User> ul = new ArrayList<User>();
		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_USER_BY_TYPE);

			pstm.setString(1, type.toString());
			pstm.execute();

			rs = pstm.getResultSet();

			while (rs.next()) {
				user = new User();

				user.setId(rs.getInt(USER_ID));
				user.setEmail(rs.getString(EMAIL));
				user.setPassword(rs.getString(PASSWORD));
				user.setFirstName(rs.getString(FIRST_NAME));
				user.setLastName(rs.getString(LAST_NAME));
				user.setYearBorn(rs.getInt(YEAR_BORN));
				user.setType(UserType.valueOf((rs.getString(USER_TYPE).toUpperCase())));
				ul.add(user);

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
	// end of selectUserByEmail

	// insertUser
	public void insertUser(User user) {

		Connection con = null;
		PreparedStatement pstm = null;

		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERT_USER);

			pstm.setString(1, user.getType().toString());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getFirstName());
			pstm.setString(5, user.getLastName());
			pstm.setInt(6, user.getYearBorn());
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
	// end of insertUser

	// deleteUserByID
	public void deleteUserByID(int id) {

		Connection con = null;
		PreparedStatement pstm = null;

		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_USER_BY_ID);

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
	// end of deleteUserByID

	// deleteUserByEmail
	public void deleteUserByEmail(String email) {

		Connection con = null;
		PreparedStatement pstm = null;

		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_USER_BY_EMAIL);

			pstm.setString(1, email);
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
	// end of deleteUserByID

	// updateUserBy
	public void updateUser(User user) {

		Connection con = null;
		PreparedStatement pstm = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATE_USER_BY_ID);

			pstm.setString(1, user.getType().toString());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getFirstName());
			pstm.setString(5, user.getLastName());
			pstm.setInt(6, user.getYearBorn());
			pstm.setInt(7, user.getId());
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
	// end of updateUser
}
