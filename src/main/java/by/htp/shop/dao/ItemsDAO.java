package by.htp.shop.dao;

import java.util.List;

import by.htp.shop.bean.Item;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.exception.ItemAlreadyExistsException;

public interface ItemsDAO {

	void removeRentedItem(int clientId, int equipmentId) throws DAOException;
	List<Item> findClientItems (int clientId) throws DAOException;
	void addRentedItem(int clientId, int equipmentId, int days) throws DAOException, ItemAlreadyExistsException;
	List<Item> findCartItems(List<Integer> cart) throws DAOException;
	List<Item> formItemList(String folder) throws DAOException;
	List<String> formFolderElementsList() throws DAOException;
	
}
