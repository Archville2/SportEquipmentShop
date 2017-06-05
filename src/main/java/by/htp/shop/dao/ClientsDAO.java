package by.htp.shop.dao;

import by.htp.shop.bean.ClientData;
import by.htp.shop.dao.exception.DAOException;

public interface ClientsDAO {

	ClientData formClientData(String login, String password) throws DAOException;
	
	boolean isClientExist(String login) throws DAOException;
	
	void addNewClient(ClientData clientData) throws DAOException;
}