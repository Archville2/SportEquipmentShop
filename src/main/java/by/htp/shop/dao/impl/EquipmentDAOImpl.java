package by.htp.shop.dao.impl;

import by.htp.shop.bean.Item;
import by.htp.shop.dao.EquipmentDAO;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.exception.EquipmentAlreadyExistsException;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {

    private final static Logger LOGGER = Logger.getLogger(EquipmentDAOImpl.class);

    private DataSource dataSource;

	final String FORM_EQUIPMENT_DATA = "SELECT e.*, r.client_id,types.folder FROM equipment e LEFT OUTER JOIN rented_items r ON e.id=r.equipment_id,types WHERE e.type=types.type AND types.folder LIKE ?";
	final String FORM_FOLDER_DATA = "SELECT DISTINCT folder FROM types";
	final String FORM_CART_LIST = "SELECT * FROM equipment WHERE id=?";
	final String ADD_RENTED_ITEM = "INSERT INTO rented_items (client_id, equipment_id, date, length) VALUES (?,?,?,?)";
	final String CLIENT_ITEMS = "SELECT * FROM equipment, rented_items WHERE equipment.id=rented_items.equipment_id AND rented_items.client_id=?";
	final String REMOVE_RENTED_ITEM = "DELETE FROM rented_items WHERE client_id=? AND equipment_id=?";

    public EquipmentDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void removeRentedEquipment(int clientId, int equipmentId) throws DAOException {
        final int CLIENTS_ID = 1;
        final int EQUIPMENT_ID = 2;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(REMOVE_RENTED_ITEM);
            ps.setInt(CLIENTS_ID, clientId);
            ps.setInt(EQUIPMENT_ID, equipmentId);
            ps.executeUpdate();

        } catch (SQLException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in removeRentedItem", e);
        }
    }

    @Override
    public List<Item> findClientEquipment(int clientId) throws DAOException {
        final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;

        List<Item> clientItems = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CLIENT_ITEMS);
            ps.setInt(ID, clientId);
            
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Item item = new Item.Builder(
                        rs.getInt(ID),
                        rs.getString(NAME),
                        rs.getString(TYPE),
                        rs.getString(DESCRIPTION),
                        rs.getString(MANUFACTURER),
                        rs.getInt(COST),
                        rs.getString(IMG),
                        null).build();
					clientItems.add(item);
				}
				return clientItems;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in findClientItems", e);
		}
	}

    @Override
    public void addRentedEquipment(int clientId, int equipmentId, int days) throws DAOException, EquipmentAlreadyExistsException {
        final int CLIENT_ID = 1;
        final int EQUIPMENT_ID = 2;
        final int DATE = 3;
        final int LENGTH = 4;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_RENTED_ITEM);
            ps.setInt(CLIENT_ID, clientId);
            ps.setInt(EQUIPMENT_ID, equipmentId);
            ps.setDate(DATE, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(LENGTH, days);
            ps.executeUpdate();

		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new EquipmentAlreadyExistsException("duplicated primary key found. Item ID: " + equipmentId);
			}
			LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in addRentedItem", e);
        }
    }

    @Override
    public List<Item> findCartEquipment(List<Integer> cart) throws DAOException {
        final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;

        List<Item> items = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
        	PreparedStatement ps = connection.prepareStatement(FORM_CART_LIST);
        	
			for (Integer id : cart) {
				ps.setInt(ID, id);
				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						Item item = new Item.Builder(
                            rs.getInt(ID),
                            rs.getString(NAME),
                            rs.getString(TYPE),
                            rs.getString(DESCRIPTION),
                            rs.getString(MANUFACTURER),
                            rs.getInt(COST),
                            rs.getString(IMG),
                            null).build();
						items.add(item);
					}
				}
			}
			return items;
		} catch (SQLException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new DAOException("Exception in findCartItems", e);
        }
    }

    @Override
    public List<Item> formEquipmentList(String folder) throws DAOException {
        final int ID = 1;
        final int TYPE = 2;
        final int NAME = 3;
        final int DESCRIPTION = 4;
        final int MANUFACTURER = 5;
        final int COST = 6;
        final int IMG = 7;
        final int CLIENT_ID = 8;
        final int FOLDER = 1;

        List<Item> items = new ArrayList<>();

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(FORM_EQUIPMENT_DATA);
			ps.setString(FOLDER, folder);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Item item = new Item.Builder(
                        rs.getInt(ID),
                        rs.getString(NAME),
                        rs.getString(TYPE),
                        rs.getString(DESCRIPTION),
                        rs.getString(MANUFACTURER),
                        rs.getInt(COST),
                        rs.getString(IMG),
                        rs.getString(CLIENT_ID)).build();
					items.add(item);
				}

				return items;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in formItemList", e);
		}
	}

	@Override
	public List<String> formFolderElementsList() throws DAOException {
		final int TYPE = 1;
		List<String> folderElements = new ArrayList<>();

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(FORM_FOLDER_DATA);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					folderElements.add(rs.getString(TYPE));
				}

				return folderElements;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Exception in formFolderElementsList", e);
		}
	}
}
