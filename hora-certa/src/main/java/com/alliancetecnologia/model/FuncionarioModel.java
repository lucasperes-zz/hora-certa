package com.alliancetecnologia.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCIONARIO")
public class FuncionarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO")
	private Integer codigo;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	@Column(name = "CPF")
	private String cpf;
	@Column(name = "CTPS")
	private String ctps;
	@Column(name = "VALOR_SALARIO")
	private Double valorSalario;
	@Column(name = "CARGO")
	private String cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public Double getValorSalario() {
		return valorSalario;
	}

	public void setValorSalario(Double valorSalario) {
		this.valorSalario = valorSalario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
