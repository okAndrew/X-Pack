package com.epam.lab.controller.dao;

import com.epam.lab.model.Admin;

public interface AdminDAO extends GenericDAO<Admin> {

	Admin getByLogin(String login);
}
