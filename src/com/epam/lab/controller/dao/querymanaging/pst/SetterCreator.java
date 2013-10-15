package com.epam.lab.controller.dao.querymanaging.pst;

import java.util.ArrayList;
import java.util.List;

public class SetterCreator {
	private static List<SetterItem> setters;
	static {
		setters = new ArrayList<SetterItem>();
		setters.add(SetterItem.INT);
		setters.add(SetterItem.LONG);
		setters.add(SetterItem.DOUBLE);
		setters.add(SetterItem.STRING);
		setters.add(SetterItem.BOOLEAN);
		setters.add(SetterItem.TIMESTAMP);
	}

	public SetterItem findByType(Class<?> type) {
		SetterItem result = null;
		for (SetterItem setter : setters) {
			if (type.equals(setter.getType())) {
				result = setter;
				break;
			}
		}
		return result;
	}
}
