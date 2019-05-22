/**
 * 
 */
package com.alliancetecnologia.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.Path.CrossParameterNode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.alliancetecnologia.annotations.TransactionJpa;
import com.alliancetecnologia.enums.TipoRegistro;
import com.alliancetecnologia.model.FuncionarioModel;
import com.alliancetecnologia.model.RegistroPontoModel;
import com.alliancetecnologia.utils.DateUtils;

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
	
	private static final int CARGA_HORARIA_DIARIA = 8;
	
	private Integer codigo;
	private TipoRegistro tipoRegistroSelecionado;
	
	private List<RegistroPontoModel> registros;
	
	@TransactionJpa
	public void registrarPonto() {
		if(codigo > 0 && tipoRegistroSelecionado != null) {
			FuncionarioModel funcionario = buscarFuncionario(codigo);
			if(funcionario != null && funcionario.getCodigo() > 0) {
				// Valida o tipo de registro para nao registrar o mesmo evento
				if(getAppSession().verificarRegistroPonto(funcionario, tipoRegistroSelecionado)) {
					addMessageWarn("Não é possível registrar o mesmo tipo de ponto no mesmo período! Tipo Registro: " 
									+ tipoRegistroSelecionado.getDescricao() + " / Data Movimento: " 
									+ DateUtils.formatterDate(LocalDate.now()));
				} else {
				
					RegistroPontoModel regPonto = buscarRegistroPonto(funcionario);
					if(regPonto == null || regPonto.getCodigo() == null) {
						regPonto = new RegistroPontoModel();
						regPonto.setDataMovimento(LocalDate.now());
					}
					regPonto.setFuncionario(funcionario);
					// ENTRADA
					if(TipoRegistro.ENTRADA.equals(tipoRegistroSelecionado)) {
						regPonto.setHoraEntrada(LocalTime.now());
						getAppSession().setDiaTrabalho(LocalDate.now());
					}
					// SAIDA ALMOCO
					if(TipoRegistro.SAIDA_ALMOCO.equals(tipoRegistroSelecionado)) {
						// Verifica se o ponto anterior foi registrado
						if(!getAppSession().verificarRegistroPonto(funcionario, TipoRegistro.ENTRADA)) {
							addMessageWarn("Registre primeiro a entrada para a jornada de trabalho!");
							return;
						} else {
							regPonto.setHoraSaidaAlmoco(LocalTime.now());
						}
					}
					// RETORNO ALMOCO
					if(TipoRegistro.RETORNO_ALMOCO.equals(tipoRegistroSelecionado)) {
						// Verifica se o ponto anterior foi registrado
						if(!getAppSession().verificarRegistroPonto(funcionario, TipoRegistro.SAIDA_ALMOCO)) {
							addMessageWarn("Registre primeiro a saída para o almoço!");
							return;
						} else {
							regPonto.setHoraRetornoAlmoco(LocalTime.now());
						}
					}
					// SAIDA
					if(TipoRegistro.SAIDA.equals(tipoRegistroSelecionado)) {
						// Verifica se o ponto anterior foi registrado
						if(!getAppSession().verificarRegistroPonto(funcionario, TipoRegistro.RETORNO_ALMOCO)) {
							addMessageWarn("Registre primeiro o retorno do almoço!");
							return;
						} else {
							regPonto.setHoraSaida(LocalTime.now());
							
							int primeiroTurno = regPonto.getHoraSaidaAlmoco().getHour() - regPonto.getHoraEntrada().getHour();
							int segundoTurno = regPonto.getHoraSaida().getHour() - regPonto.getHoraRetornoAlmoco().getHour();
							int horasTrabalhadas = primeiroTurno + segundoTurno;
							regPonto.setHorasTrabalhadas(horasTrabalhadas);
							regPonto.setHorasExtras(0);
							
							if(horasTrabalhadas > CARGA_HORARIA_DIARIA) {
								regPonto.setHorasExtras(horasTrabalhadas - CARGA_HORARIA_DIARIA);
							}
						}
					}
					
					salvarRegistroPonto(regPonto);
					
					// Registra o ponto na sessao
					getAppSession().adicionarRegistroPonto(funcionario, tipoRegistroSelecionado);
					addMessageInfo("Registro de ponto confirmado com sucesso! Funcionário: " 
									+ funcionario.getNome() + " / Tipo Registro: " + tipoRegistroSelecionado.getDescricao()
									+ " / Data Movimento: " + DateUtils.formatterDate(LocalDateTime.now()));
					codigo = null;
					tipoRegistroSelecionado = null;
				}
			} else {
				addMessageWarn("Funcionário não localizado!");
			}
		} else {
			addMessageWarn("Informe um valor numérico maior que zero e selecione um tipo de registro!");
		}
	}
	
	@TransactionJpa
	@SuppressWarnings("unchecked")
	public void listarRegistros() {
		Criteria criteria = getSession().createCriteria(RegistroPontoModel.class)
				.addOrder(Order.desc("dataMovimento"));
		registros = criteria.list();
	}
	
	// Metodos de banco de dados
	
	public FuncionarioModel buscarFuncionario(Integer codigo) {
		Criteria criteria = getSession().createCriteria(FuncionarioModel.class)
				.add(Restrictions.eq("codigo", codigo));
		return (FuncionarioModel) criteria.uniqueResult();
	}
	
	public RegistroPontoModel buscarRegistroPonto(FuncionarioModel funcionario) {
		Criteria criteria = getSession().createCriteria(RegistroPontoModel.class)
				.add(Restrictions.eq("funcionario", funcionario))
				.add(Restrictions.eq("dataMovimento", LocalDate.now()));
		return (RegistroPontoModel) criteria.uniqueResult();
	}
	
	public void salvarRegistroPonto(RegistroPontoModel registro) {
		getManager().merge(registro);
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public TipoRegistro[] getTiposRegistros() {
		return TipoRegistro.values();
	}

	/**
	 * @return the tipoRegistroSelecionado
	 */
	public TipoRegistro getTipoRegistroSelecionado() {
		return tipoRegistroSelecionado;
	}

	/**
	 * @param tipoRegistroSelecionado the tipoRegistroSelecionado to set
	 */
	public void setTipoRegistroSelecionado(TipoRegistro tipoRegistroSelecionado) {
		this.tipoRegistroSelecionado = tipoRegistroSelecionado;
	}

	/**
	 * @return the registros
	 */
	public List<RegistroPontoModel> getRegistros() {
		return registros;
	}
	
}
