package by.htp.shop.service.factory;

import by.htp.shop.service.impl.ClientImplService;
import by.htp.shop.service.impl.ItemImplService;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ClientImplService clientImplService = new ClientImplService();
	private final ItemImplService itemImplService = new ItemImplService();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ClientImplService getClientImplService() {
		return clientImplService;
	}
	
	public ItemImplService getItemImplService() {
		return itemImplService;
	}
	
}
