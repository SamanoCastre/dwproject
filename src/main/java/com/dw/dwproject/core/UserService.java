package com.dw.dwproject.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import com.dw.dwproject.api.pojo.UserPojo;
import com.dw.dwproject.db.UserDao;
import com.dw.dwproject.utils.Const;
import com.mysql.cj.exceptions.UnableToConnectException;
import org.jooq.impl.DefaultDSLContext;

public class UserService {
	
	public UserDao dao;
	
	public UserService(DefaultDSLContext config) {
		this.dao = new UserDao(config);
	}
	
	public List<UserPojo>getUsers() {
		return this.dao.getAll().orElse(null);
	}
	
	public UserPojo getUser(int id) {
		Optional<UserPojo> user = this.dao.getUnique(id);
		if(user.isEmpty()) {
			throw new WebApplicationException(String.format(Const.OBJECT_NOT_FOUND, UserPojo.class.getName(), id), Status.NOT_FOUND);
		}
		return user.get();
	}
	
	public Integer createUser(UserPojo user) {
		user.setDateCreation(LocalDate.now());
		return this.dao.saveUnique(user);
	}

	public Integer editUser(UserPojo user) {
		if(Objects.isNull(this.getUser(user.getId()))) {
			throw new WebApplicationException(String.format(Const.OBJECT_NOT_FOUND, UserPojo.class.getName(), user.getId()), Status.NOT_FOUND);
		}
		return this.dao.updateUnique(user);
	}

	public UserPojo deleteUser(int id) {
		UserPojo user = this.getUser(id);
		if(Objects.isNull(user)) {
			throw new WebApplicationException(String.format(Const.OBJECT_NOT_FOUND, UserPojo.class.getName(), id), Status.NOT_FOUND);
		}
		this.dao.deleteUnique(id);
		return user;
	}
	
	 public String performHealthCheck() {
	    try {
	      this.dao.getAll();
	    } catch (UnableToConnectException ex) {
	    	this.checkUnableToObtainConnectionException(ex);
	    } catch (Exception ex) {
	      return Const.DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
	    }
	    return null;
	 }
	 
	 private String checkUnableToObtainConnectionException(UnableToConnectException ex) {
	    if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
	      return Const.DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
	    } else if (ex.getCause() instanceof java.sql.SQLException) {
	      return Const.DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
	    } else {
	      return Const.DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
	    }
	 }
	
}
