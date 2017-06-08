package by.htp.shop.service.factory;

import by.htp.shop.service.ClientService;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.impl.ClientServiceImpl;
import by.htp.shop.service.impl.EquipmentServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ClientService clientService = new ClientServiceImpl();
	private final EquipmentService equipmentService = new EquipmentServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ClientService getClientServiceImpl() {
		return clientService;
	}
	
	public EquipmentService getEquipmentServiceImpl() {
		return equipmentService;
	}
	
}
