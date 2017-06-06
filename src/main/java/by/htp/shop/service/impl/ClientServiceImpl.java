package by.htp.shop.service.impl;

import by.htp.shop.bean.ClientData;
import by.htp.shop.dao.ClientDAO;
import by.htp.shop.dao.exception.DAOException;
import by.htp.shop.dao.factory.DAOFactory;
import by.htp.shop.service.ClientService;
import by.htp.shop.service.exception.EmptyFieldsException;
import by.htp.shop.service.exception.LoginException;
import by.htp.shop.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService{
	
	@Override
	public ClientData loginClient(String login, String password) throws ServiceException, LoginException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ClientDAO clientsDAO = daoObjectFactory.getClientImplDAO();

		try {
			ClientData clientData = clientsDAO.formClientData(login, password);
			if (clientData == null) {
				throw new LoginException("wrong login or password");
			}
			return clientData;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void checkEmptyFields(ClientData clientData) throws EmptyFieldsException {
		if (isEmptyFieldExist(clientData)) {
			throw new EmptyFieldsException("empty fields found");
		}
	}
	
	@Override
	public void checkLogin(String login) throws ServiceException, LoginException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ClientDAO clientsDAO = daoObjectFactory.getClientImplDAO();

		try {
			if (clientsDAO.isClientExist(login)) {
				throw new LoginException("login already in use");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Override
	public void registerClient (ClientData clientData) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		ClientDAO clientsDAO = daoObjectFactory.getClientImplDAO();
		
		try {
			clientsDAO.addNewClient(clientData);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	private boolean isEmptyFieldExist (ClientData clientData) {
		if (clientData.getName().equals("") | 
				clientData.getSurname().equals("") |
				clientData.getEmail().equals("") |
				clientData.getPhone().equals("") |
				clientData.getLogin().equals("") |
				clientData.getPassword().equals("")) {
			return true;
		}
		return false;
	}

}
