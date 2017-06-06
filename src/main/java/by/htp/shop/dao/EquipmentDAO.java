package by.htp.shop.dao;

import java.util.List;

import by.htp.shop.bean.Item;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.exception.EquipmentAlreadyExistsException;

public interface EquipmentDAO {

	void removeRentedEquipment(int clientId, int equipmentId) throws DAOException;
	List<Item> findClientEquipment (int clientId) throws DAOException;
	void addRentedEquipment(int clientId, int equipmentId, int days) throws DAOException, EquipmentAlreadyExistsException;
	List<Item> findCartEquipment(List<Integer> cart) throws DAOException;
	List<Item> formEquipmentList(String folder) throws DAOException;
	List<String> formFolderElementsList() throws DAOException;
	
}
