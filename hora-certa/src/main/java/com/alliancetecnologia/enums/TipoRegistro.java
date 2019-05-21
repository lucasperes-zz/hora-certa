/**
 * 
 */
package com.alliancetecnologia.enums;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 20 de mai de 2019
 *
 */
public enum TipoRegistro {
	
	ENTRADA("Entrada"), SAIDA_ALMOCO("Saída Almoço"), RETORNO_ALMOCO("Retorno Almoço"), SAIDA("Saída");
	
	private String descricao;

	/**
	 * @param descricao
	 */
	private TipoRegistro(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
