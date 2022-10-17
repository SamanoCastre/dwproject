package com.dw.dwproject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.dw.dwproject.core.UserService;
import com.dw.dwproject.health.UserHealthCheck;
import com.dw.dwproject.resources.UserResource;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import io.dropwizard.Application;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWProjectApplication extends Application<DWProjectConfiguration> {
	/*
	private final JooqBundle<DWProjectConfiguration> jooq = new JooqBundle<DWProjectConfiguration>() {
	    
		protected void configure(Configuration configuration) {
	        configuration.set(SQLDialect.MYSQL);
	    }

	    @Override
	    public DataSourceFactory getDataSourceFactory(DWProjectConfiguration configuration) {
	        return configuration.getDataSourceFactory();
	    }
	};*/

	@Override
	public void initialize(Bootstrap<DWProjectConfiguration> bootstrap) {
	    super.initialize(bootstrap);
	}
	
	public static void main(final String[] args) throws Exception {
	  	new DWProjectApplication().run(new String[] {"server","config.yml"});
	  }
	
	@Override
	public String getName() {
	    return "DropWzard Project";
	}
	
	public void generateModel() throws IOException, Exception {
		GenerationTool.generate(
		  Files.readString(
		    Path.of("jooq-config.xml")
		  )    
		);
	}

	@Override
	public void run(DWProjectConfiguration configuration, Environment environment) throws Exception {
		this.generateModel();
	    final PooledDataSourceFactory dbConfig = configuration.getDataSourceFactory();
        ManagedDataSource dataSource = dbConfig.build(environment.metrics(), "jooq");

        DefaultConfiguration config = new DefaultConfiguration();
        config.set(new DataSourceConnectionProvider(dataSource));
      DefaultDSLContext dslContext =  new DefaultDSLContext(config);
        environment.jersey().register(new UserResource(dslContext));
        
      //Register Health Check
      	UserHealthCheck healthcheck = new UserHealthCheck(new UserService(dslContext));
      	environment.healthChecks().register(getName(), healthcheck);
      	
	}
}
