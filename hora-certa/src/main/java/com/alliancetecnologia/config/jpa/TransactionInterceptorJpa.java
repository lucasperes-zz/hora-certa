package com.alliancetecnologia.config.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.alliancetecnologia.annotations.TransactionJpa;

@Interceptor
@TransactionJpa
public class TransactionInterceptorJpa implements Serializable {
	
	private static final long serialVersionUID = -8802967227479283755L;
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = manager.getTransaction();
		try {
			
			if(!transaction.isActive()) {
				// Truque para fazer o rollbach no que ja passou, senão um futuro commit confirmaria até operações sem transações
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
			}
			return context.proceed();
		} catch(Exception e) {
			if(transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if(transaction != null && transaction.isActive()) {
				transaction.commit();
			}
		}
		
	}

}
