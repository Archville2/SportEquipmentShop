package by.htp.shop.service.factory;

import by.htp.shop.service.ClientService;
import by.htp.shop.service.EquipmentService;
import by.htp.shop.service.impl.ClientServiceImpl;
import by.htp.shop.service.impl.EquipmentServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ClientServiceImpl clientServiceImpl = new ClientServiceImpl();
	private final EquipmentServiceImpl equipmentServiceImpl = new EquipmentServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ClientService getClientServiceImpl() {
		return clientServiceImpl;
	}
	
	public EquipmentService getEquipmentServiceImpl() {
		return equipmentServiceImpl;
	}
	
}
