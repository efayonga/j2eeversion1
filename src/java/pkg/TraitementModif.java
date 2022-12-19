package pkg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aegam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TraitementModif {
    private static String jdbcURL = "jdbc:mysql://127.0.0.1:3306/Contacts";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";
    
    private static final String INSERT_CONTACT_SQL = "INSERT INTO contact" + "  (nom, adresse, code_postal, ville, code_secteur) VALUES "
                    + " (?, ?, ?, ?, ?);";

    private static final String SELECT_CONTACT_BY_NUMERO = "select numero,nom,adresse,code_postal,ville,code_secteur from contact where numero =?;";
    private static final String SELECT_ALL_USERS = "select * from contact;";
    private static final String DELETE_USERS_SQL = "delete from contact where numero = ?;";
    private static final String UPDATE_USERS_SQL = "update contact set nom=?,adresse=?,code_postal=?,ville=?,code_secteur=? where numero = ?;";

    static Connection getConnection() {
		Connection connection = null;
		try {
                        System.out.println("Trying to connect to database");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                        System.out.println("Succesfully connected to database");
		} catch (SQLException e) {
                    System.out.println("PROBLEME");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
                        System.out.println("PROBLEME");
			e.printStackTrace();
		}
		return connection;
	}
    
    public static void insertContact(Contact contact) throws SQLException {
		// try-with-resource statement will auto close the connection.
                
                //numero, nom, adresse, code_postal, ville, code_secteur
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
			preparedStatement.setString(1, contact.getNom());
			preparedStatement.setString(2, contact.getAdresse());
			preparedStatement.setString(3, contact.getCode_postal());
                        preparedStatement.setString(4, contact.getVille());
			preparedStatement.setString(5, contact.getCode_secteur());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	public static Contact selectContact(String numero) {
		Contact contact = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTACT_BY_NUMERO);) {
			preparedStatement.setString(1, numero);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
                            String num= rs.getString("numero");
                            String nom = rs.getString("nom");
                            String adresse= rs.getString("adresse");
                            String code_postal= rs.getString("code_postal");
                            String ville= rs.getString("ville");
                            String code_secteur= rs.getString("code_secteur");
                            contact = new Contact(num, nom, adresse, code_postal,ville,code_secteur);
			}
		} catch (SQLException e) {
		}
		return contact;
	}

	public static List<Contact> selectAllContacts() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Contact> contacts = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
                        System.out.println(rs);


			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String num= rs.getString("numero");
                                String nom = rs.getString("nom");
                                String adresse= rs.getString("adresse");
                                String code_postal= rs.getString("code_postal");
                                String ville= rs.getString("ville");
                                String code_secteur= rs.getString("code_secteur");
				contacts.add(new Contact(num, nom, adresse, code_postal,ville,code_secteur));
			}
		} catch (SQLException e) {
                    System.out.println("PROBLEME SELECT LIST");
		}
		return contacts;
	}

	public static boolean deleteContact(String numero) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, numero);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public static boolean updateContact(Contact contact) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			preparedStatement.setString(1, contact.getNom());
			preparedStatement.setString(2, contact.getAdresse());
                        preparedStatement.setString(3, contact.getCode_postal());
			preparedStatement.setString(4, contact.getVille());
			preparedStatement.setString(5, contact.getCode_secteur());
                        preparedStatement.setString(6, contact.getNumero());


			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}

