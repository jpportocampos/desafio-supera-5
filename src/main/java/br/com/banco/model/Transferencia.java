package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transferencia")
public class Transferencia {
	@Column
	private @Id @GeneratedValue Long id;
	@Column(name = "data_transferencia")
	private String dataTransferencia;
	@Column
	private float valor;
	@Column
	private String tipo;
	@Column(name = "nome_operador_transacao")
	private String nomeOperadorTransacao;
	@ManyToOne
	@JoinColumn(name = "id_conta", nullable = false)
	private Conta contaId;

	public String getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeOperadorTransacao() {
		return nomeOperadorTransacao;
	}

	public void setNomeOperadorTransacao(String nomeOperadorTransacao) {
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}
}
