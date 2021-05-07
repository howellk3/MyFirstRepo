
/**
 * Constructs an account object
 * @author kenthowell
 *
 */
public class Account {
	private String password, user, position;
	
	/**
	 * Default Account Constructor all fields set to null
	 */
	public Account() {
		setPassword(null);
		setUser(null);
		setPosition(null);
	}
	
	/**
	 * Constructs an Account Object with input parameters
	 * @param password accounts password
	 * @param user accounts username
	 * @param position accounts position
	 */
	public Account(String password, String user, String position) {
		setPassword(password);
		setUser(user);
		setPosition(position);
	}

	/**
	 * 
	 * @return Accounts password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the Account
	 * @param password Accounts password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return The accounts username
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the accounts username
	 * @param user accounts username
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 
	 * @return Accounts position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the accounts position
	 * @param position accounts position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	

}
