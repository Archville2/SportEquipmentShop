package by.htp.shop.dao.factory;

import by.htp.shop.dao.ClientDAO;
import by.htp.shop.dao.EquipmentDAO;
import by.htp.shop.dao.impl.ClientDAOImpl;
import by.htp.shop.dao.impl.EquipmentDAOImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public final class DAOFactory {

	private final static String DEFAULT = "default.properties";
	private final static String URL = "jdbc.url";
	private final static String USERNAME = "jdbc.username";
	private final static String PASSWORD = "jdbc.password";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static Logger LOGGER = Logger.getLogger(DAOFactory.class);
	private final static DAOFactory instance = new DAOFactory();
	private final DataSource dataSource;

	private DAOFactory() {
		try {
			this.dataSource = getDataSource();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public ClientDAO getClientDAOImpl() {
		return new ClientDAOImpl(dataSource);
	}

	public EquipmentDAO getEquipmentDAOImpl() {
		return new EquipmentDAOImpl(dataSource);
	}

	private ComboPooledDataSource getDataSource() throws SQLException {

		Properties properties = getDefaultProperties();
		registerSQLDriver();

		ComboPooledDataSource cpds = new ComboPooledDataSource();

		cpds.setJdbcUrl(properties.getProperty(URL));
		cpds.setUser(properties.getProperty(USERNAME));
		cpds.setPassword(properties.getProperty(PASSWORD));

		cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(100);

		return cpds;
	}

	private void registerSQLDriver() {
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private Properties getDefaultProperties() {
		Properties properties = new Properties();
		try {
			try (InputStream in = getClass().getClassLoader().getResourceAsStream(DEFAULT)) {
				properties.load(in);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return properties;
	}

}
