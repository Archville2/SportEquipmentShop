package by.htp.shop.service;

import by.htp.shop.bean.ClientData;
import by.htp.shop.service.exception.EmptyFieldsException;
import by.htp.shop.service.exception.LoginException;
import by.htp.shop.service.exception.ServiceException;

public interface ClientService {
	ClientData loginClient(String login, String password) throws ServiceException, LoginException;

	void checkEmptyFields(ClientData clientData) throws EmptyFieldsException;

	void checkLogin(String login) throws ServiceException, LoginException;

	void registerClient(ClientData clientData) throws ServiceException;
}
