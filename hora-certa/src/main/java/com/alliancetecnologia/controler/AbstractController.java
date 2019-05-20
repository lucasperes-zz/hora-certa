/**
 * 
 */
package com.alliancetecnologia.controler;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 19 de mai de 2019
 *
 */
public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = -843035256056141448L;
	
	protected void addMessageInfo(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		context.addMessage(null, message);
	}

}
