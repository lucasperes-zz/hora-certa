/**
 * 
 */
package com.alliancetecnologia.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 20 de mai de 2019
 *
 */
@Entity
@Table(name = "REGISTRO_PONTO")
public class RegistroPontoModel {

	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name = "CODIGO_FUNCIONARIO")
	private FuncionarioModel codigoFuncionario;
	@Column(name = "DATA_MOVIMENTO")
	private LocalDate dataMovimento;
	@Column(name = "HORA_ENTRADA")
	private LocalTime horaEntrada;
	@Column(name = "HORA_SAIDA_ALMOCO")
	private LocalTime horaSaidaAlmoco;
	@Column(name = "HORA_RETORNO_ALMOCO")
	private LocalTime horaRetornoAlmoco;
	@Column(name = "HORA_SAIDA")
	private LocalTime horaSaida;
	@Column(name = "HORAS_TRABALHADAS")
	private LocalTime horasTrabalhadas;
	@Column(name = "HORAS_EXTRAS")
	private LocalTime horasExtras;
	@Column(name = "BANCO_HORAS")
	private LocalTime bancoHoras;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	

	public LocalDate getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDate dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSaidaAlmoco() {
		return horaSaidaAlmoco;
	}

	public void setHoraSaidaAlmoco(LocalTime horaSaidaAlmoco) {
		this.horaSaidaAlmoco = horaSaidaAlmoco;
	}

	public LocalTime getHoraRetornoAlmoco() {
		return horaRetornoAlmoco;
	}

	public void setHoraRetornoAlmoco(LocalTime horaRetornoAlmoco) {
		this.horaRetornoAlmoco = horaRetornoAlmoco;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public LocalTime getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(LocalTime horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public LocalTime getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(LocalTime horasExtras) {
		this.horasExtras = horasExtras;
	}

	public LocalTime getBancoHoras() {
		return bancoHoras;
	}

	public void setBancoHoras(LocalTime bancoHoras) {
		this.bancoHoras = bancoHoras;
	}

}
