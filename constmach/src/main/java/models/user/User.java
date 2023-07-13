package models.user;

public class User {

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int yearBorn;
	private UserType type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearBorn() {
		return yearBorn;
	}

	public void setYearBorn(int yearBorn) {
		this.yearBorn = yearBorn;
	}

	public User() {

	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

//	public void setType(String type) {
//		if(type.equalsIgnoreCase("admin")) {
//			this.type = UserType.ADMIN;
//		} else {
//			this.type = UserType.WORKER;
//		}
//	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", yearBorn=" + yearBorn + ", type=" + type + "]";
	}

	public User(int id, String email, String password, String firstName, String lastName, int yearBorn, UserType type) {

		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearBorn = yearBorn;
		this.type = type;
	}

	public User(String email, String password, String firstName, String lastName, int yearBorn, UserType type) {

		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearBorn = yearBorn;
		this.type = type;
	}

}
