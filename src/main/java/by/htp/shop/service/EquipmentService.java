package by.htp.shop.service;

import java.util.List;

import by.htp.shop.bean.Item;
import by.htp.shop.service.exception.ServiceException;

public interface EquipmentService {
	boolean isInCart(List<Integer> cart, int id);
	
	void addRentedItem(int clientId, int equipmentId, int days) throws ServiceException;

	List<Item> formCartEquipment(List<Integer> cart) throws ServiceException;

	List<String> formCategoryElementList() throws ServiceException;

	List<Item> formEquipmentList(String folder) throws ServiceException;

	List<Item> formClientEquipment(int clientId) throws ServiceException;

	void removeRentedEquipment(int clientId, int equipmentId) throws ServiceException;
}
