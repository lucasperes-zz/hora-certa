/**
 * 
 */
package com.alliancetecnologia.app;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 20 de mai de 2019
 *
 */
public class AppSession {
	
	private static AppSession instance;
	
	private Map<Funcionario, List<>>
	
	private AppSession() {
		// Block instance
	}
	
	public static AppSession getInstance() {
		if(instance == null) {
			instance = new AppSession();
		}
		return instance;
	}

}
