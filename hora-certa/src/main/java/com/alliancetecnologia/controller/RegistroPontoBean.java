/**
 * 
 */
package com.alliancetecnologia.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.alliancetecnologia.enums.TipoRegistro;
import com.alliancetecnologia.model.FuncionarioModel;

/**
 * Classe Controller para registro de ponto
 * 
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 20 de mai de 2019
 *
 */
@Named
@SessionScoped
public class RegistroPontoBean extends AbstractController {

	private static final long serialVersionUID = 6556432154766661477L;

	private FuncionarioModel funcionario;
	// Metodos
	public boolean isPermiteRegistrar() {
		new AppSess
	}
	
}
