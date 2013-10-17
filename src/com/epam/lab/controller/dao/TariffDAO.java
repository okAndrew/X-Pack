package com.epam.lab.controller.dao;

import java.util.List;

import com.epam.lab.model.Tariff;

public interface TariffDAO extends GenericDAO<Tariff> {
	
	List<Tariff> getAvailableTariffs(); 
}
