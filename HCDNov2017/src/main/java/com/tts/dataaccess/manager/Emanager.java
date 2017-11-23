package com.tts.dataaccess.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class Emanager {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public Emanager() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		
		this.load();
	}
public void load( ) throws ClassNotFoundException, SQLException {
	InitialContext ctx;
	try {
		DataSource ds;
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
		Map properties = new HashMap();
		properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
		emf = Persistence.createEntityManagerFactory("HCDNov2017", properties);

		em = emf.createEntityManager();

	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}	
public EntityManager getEntityManager(){
	return this.em;
}
public void unload() throws ClassNotFoundException, SQLException {
em.clear();
em.close();
emf.close();
}
}