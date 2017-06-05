package by.htp.shop.service.impl;

import java.util.List;

import by.htp.shop.bean.Item;
import by.htp.shop.dao.ItemsDAO;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.exception.ItemAlreadyExistsException;
import by.htp.shop.dao.factory.DAOFactory;
import by.htp.shop.service.exception.ServiceException;

public class ItemImplService {
	
	public boolean isInCart(List<Integer> cart, int id) throws ServiceException {
		for (Integer cartIDs : cart) {
			if (cartIDs == id) {
				return true;
			}
		}
		return false;
	}

	public void addRentedItem(int clientId, int equipmentId, int days) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();

		try {
			itemsDAO.addRentedItem(clientId, equipmentId, days);
		} catch (ItemAlreadyExistsException e) {
			System.out.println("duplicate primary keys found");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Item> formCartItems (List<Integer> cart) throws ServiceException{
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();
				
		try {
			return itemsDAO.findCartItems(cart);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<String> formCategoryElementList() throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();

		try {
			List<String> folderList = itemsDAO.formFolderElementsList();
			return folderList;

		} catch (DAOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Item> formItemList(String folder) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();

		try {
			List<Item> itemList = itemsDAO.formItemList(folder);
			return itemList;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}
	
	public List<Item> formClientItems(int clientId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();

		try {
			return itemsDAO.findClientItems(clientId);
		} catch (DAOException e) {
			throw new ServiceException("cant get client items", e);
		}

	}
	
	public void removeRentedItem(int clientId, int equipmentId) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ItemsDAO itemsDAO = daoObjectFactory.getItemImplDAO();
		
		try {
			itemsDAO.removeRentedItem(clientId, equipmentId);
		} catch (DAOException e) {
			throw new ServiceException("cant remove rented item", e);
		}
	}

}
