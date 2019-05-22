/**
 * 
 */
package com.alliancetecnologia.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.alliancetecnologia.app.AppSession;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 19 de mai de 2019
 *
 */
public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = -843035256056141448L;
	
	@Inject
	private EntityManager manager;
	
	protected void addMessageInfo(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		context.addMessage(null, message);
	}
	
	protected void addMessageWarn(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		context.addMessage(null, message);
	}
	
	protected AppSession getAppSession() {
		return AppSession.getInstance();
	}

	protected EntityManager getManager() {
		return manager;
	}
	
	protected Session getSession() {
		return getManager().unwrap(Session.class);
	}
	
}
