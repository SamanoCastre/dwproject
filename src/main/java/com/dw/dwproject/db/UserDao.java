package com.dw.dwproject.db;
import java.util.List;
import java.util.Optional;
import com.dw.dwproject.api.model.Tables;
import com.dw.dwproject.api.pojo.UserPojo;
import org.jooq.DSLContext;


public class UserDao {
	DSLContext context;

	public UserDao(DSLContext context) {
		this.context = context;
	}
	
	  public Optional<List<UserPojo>> getAll(){
		  List<UserPojo> users = context.select()
				  .from(Tables.USER)
				  .fetchInto(UserPojo.class);
		  return Optional.ofNullable(users);
	  }
	
	  
	  public Optional<UserPojo> getUnique(final int id) {
		  return Optional.ofNullable(
				  context.select()
				  .from(Tables.USER)
				  .where(Tables.USER.ID.eq(id))
				  .fetchOneInto(UserPojo.class)
				  );
		  
	  }
	
	  public Integer saveUnique(final UserPojo user) {
		  return context.newRecord(Tables.USER, user).insert();
	  }
	  
	  public Integer updateUnique(final UserPojo user) {
		  return context.update(Tables.USER)
		  .set(Tables.USER.NOM.as("nom"), user.getNom())
		  .where(Tables.USER.ID.eq(user.getId()))
		  .execute();
	  }
	  
	  public Integer deleteUnique(final int id) {
		 return context.delete(Tables.USER).where(Tables.USER.ID.eq(id)).execute();
	  }	  

}
