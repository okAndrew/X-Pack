package com.epam.lab.controller.dao.dbquerymanaging.rs;

import java.util.ArrayList;
import java.util.List;

public class GetterCreator {
	private static List<GetterItem> getters;
	static {
		getters = new ArrayList<GetterItem>();
		getters.add(GetterItem.INT);
		getters.add(GetterItem.LONG);
		getters.add(GetterItem.DOUBLE);
		getters.add(GetterItem.STRING);
		getters.add(GetterItem.BOOLEAN);
		getters.add(GetterItem.TIMESTAMP);
	}

	public GetterItem findByType(Class<?> type) {
		GetterItem result = null;
		for (GetterItem getter : getters) {
			if (type.equals(getter.getType())) {
				result = getter;
				break;
			}
		}
		return result;
	}
}
