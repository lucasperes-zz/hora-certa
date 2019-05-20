package com.alliancetecnologia.config.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ConexaoJpa implements Serializable {
	
	private static final long serialVersionUID = -4366129256151555934L;
	
	private EntityManagerFactory factory;
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("hibernatePU");
		}
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}
