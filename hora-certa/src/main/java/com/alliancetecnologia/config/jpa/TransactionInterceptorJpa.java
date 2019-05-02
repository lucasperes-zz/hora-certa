package com.alliancetecnologia.config.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.alliancetecnologia.annotations.TransactionJpa;

@Interceptor
@TransactionJpa
public class TransactionInterceptorJpa implements Serializable {
	
private static final long serialVersionUID = 1L;

	@Inject
	private Session session;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		
		Transaction transaction = session.beginTransaction();
		
		try {
			
			if(!transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
				
				// Truque para fazer o rollbach no que ja passou, senão um futuro commit confirmaria até operações sem transações
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
				
			}
			
			return context.proceed();
			
		} catch(Exception e) {
			if(transaction != null && transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
				transaction.rollback();
			}
			
			throw e;
		} finally {
			if(transaction != null && transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
				transaction.commit();
			}
		}
		
	}

}
