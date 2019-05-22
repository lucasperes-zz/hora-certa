/**
 * 
 */
package com.alliancetecnologia.controller;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.alliancetecnologia.utils.DateUtils;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 19 de mai de 2019
 *
 */
@Named
@ViewScoped
public class IndexBean extends AbstractController {

	private static final long serialVersionUID = 7299979927346179846L;
	
	private String data;
	private String versao = "1.0.0";
	
	/**
	 * 
	 */
	public IndexBean() {
		System.out.println("Conectando a base de dados...");
	}
	
	@PostConstruct
	protected void init() {
		data = DateUtils.formatterDate(LocalDate.now());
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the versao
	 */
	public String getVersao() {
		return versao;
	}
	
}
