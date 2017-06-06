package by.htp.shop.service.impl;

import java.util.List;

import by.htp.shop.bean.Item;
import by.htp.shop.dao.EquipmentDAO;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.exception.EquipmentAlreadyExistsException;
import by.htp.shop.dao.factory.DAOFactory;
import by.htp.shop.service.exception.ServiceException;

public class EquipmentServiceImpl {
	
	public boolean isInCart(List<Integer> cart, int id) {
		for (Integer cartIDs : cart) {
			if (cartIDs == id) {
				return true;
			}
		}
		return false;
	}

	public void addRentedItem(int clientId, int equipmentId, int days) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			equipmentDAO.addRentedEquipment(clientId, equipmentId, days);
		} catch (EquipmentAlreadyExistsException e) {
			System.out.println("duplicate primary keys found");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Item> formCartEquipment (List<Integer> cart) throws ServiceException{
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
				
		try {
			return equipmentDAO.findCartEquipment(cart);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<String> formCategoryElementList() throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			List<String> folderList = equipmentDAO.formFolderElementsList();
			return folderList;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Item> formEquipmentList(String folder) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			List<Item> equipmentList = equipmentDAO.formEquipmentList(folder);
			return equipmentList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Item> formClientEquipment(int clientId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();

		try {
			return equipmentDAO.findClientEquipment(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
	
	public void removeRentedEquipment(int clientId, int equipmentId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO = daoObjectFactory.getEquipmentDAOImpl();
		
		try {
			equipmentDAO.removeRentedEquipment(clientId, equipmentId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
