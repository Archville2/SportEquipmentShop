package by.htp.shop.dao.impl;

import by.htp.shop.bean.ClientData;
import by.htp.shop.dao.ClientDAO;
import by.htp.shop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAOImpl implements ClientDAO {

	private final static Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);

	private DataSource dataSource;

	final String COUNT_CLIENTS = "SELECT COUNT(*) AS TotalUsers FROM clients WHERE login=?";
	final String GET_CLIENT_INFORMATION = "SELECT * FROM clients WHERE login=? AND password=?";
	final String ADD_CLIENT = "INSERT INTO clients (name,surname,email,phone,login,password,status) VALUES (?,?,?,?,?,?,?)";

	public ClientDAOImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

	@Override
	public boolean isClientExist(String login) throws DAOException {
		final int LOGIN = 1;
		final int TOTAL_CLIENTS = 1;

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(COUNT_CLIENTS);
			ps.setString(LOGIN, login);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (Integer.parseInt(rs.getString(TOTAL_CLIENTS)) > 0) {
						return true;
					}
				}
				return false;
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in countClients", e);
		}
	}

	@Override
	public ClientData formClientData(String login, String password) throws DAOException {
		final int LOGIN = 1;
		final int PASSWORD = 2;

		try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement(GET_CLIENT_INFORMATION);
			ps.setString(LOGIN, login);
            ps.setString(PASSWORD, password);
            
            try (ResultSet rs = ps.executeQuery()){
            	while (rs.next()) {

                return new ClientData(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8));
				}
			}
			return null;
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in formClientData", e);
		}
	}

	@Override
	public void addNewClient(ClientData clientData) throws DAOException {
		final int NAME = 1;
		final int SURNAME = 2;
		final int EMAIL = 3;
		final int PHONE = 4;
		final int LOGIN = 5;
		final int PASSWORD = 6;
		final int STATUS = 7;
		
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement ps = connection.prepareStatement(ADD_CLIENT);
			ps.setString(NAME, clientData.getName());
			ps.setString(SURNAME, clientData.getSurname());
			ps.setString(EMAIL, clientData.getEmail());
			ps.setString(PHONE, clientData.getPhone());
			ps.setString(LOGIN, clientData.getLogin());
			ps.setString(PASSWORD, clientData.getPassword());
			ps.setString(STATUS, "user");
			ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in addNewClient", e);
		}
	}

}