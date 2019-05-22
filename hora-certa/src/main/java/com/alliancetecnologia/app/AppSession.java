/**
 * 
 */
package com.alliancetecnologia.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alliancetecnologia.enums.TipoRegistro;
import com.alliancetecnologia.model.FuncionarioModel;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 20 de mai de 2019
 *
 */
public class AppSession {
	
	private static AppSession instance;
	
	private LocalDate diaTrabalho;
	private Map<FuncionarioModel, List<TipoRegistro>> registros;
	
	private AppSession() {
		// Block instance
		registros = new HashMap<>();
	}
	
	public static AppSession getInstance() {
		if(instance == null) {
			instance = new AppSession();
		}
		return instance;
	}

	/**
	 * @return the diaTrabalho
	 */
	public LocalDate getDiaTrabalho() {
		return diaTrabalho;
	}

	/**
	 * @param diaTrabalho the diaTrabalho to set
	 */
	public void setDiaTrabalho(LocalDate diaTrabalho) {
		this.diaTrabalho = diaTrabalho;
	}

	public void adicionarRegistroPonto(FuncionarioModel funcionario, TipoRegistro tipo) {
		if(!verificarRegistroPonto(funcionario, tipo)) {
			List<TipoRegistro> tiposRegistros = registros.get(funcionario);
			if(tiposRegistros == null) {
				tiposRegistros = new ArrayList<>();
			}
			tiposRegistros.add(tipo);
			registros.put(funcionario, tiposRegistros);
		}
	}
	
	public boolean verificarRegistroPonto(FuncionarioModel funcionario, TipoRegistro tipo) {
		boolean result = false;
		List<TipoRegistro> tiposRegistros = registros.get(funcionario);
		if(tiposRegistros != null && !tiposRegistros.isEmpty()) {
			result = tiposRegistros.contains(tipo);
		}
		return result;
	}
	
}
