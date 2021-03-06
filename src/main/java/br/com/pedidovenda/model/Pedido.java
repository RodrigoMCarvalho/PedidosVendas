package br.com.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_criacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)  //será salvo no BD a data e hora
	@NotNull
	private Date dataCriacao;
	
	@Column(columnDefinition = "text")
	private String observacao;
	
	@Column(name = "data_entrega", nullable = false)
	@Temporal(TemporalType.DATE)   //será salvo no BD somente a data
	@NotNull
	private Date dataEntrega;
	
	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	@NotNull
	private BigDecimal valorFrete;
	
	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	@NotNull
	private BigDecimal valorDesconto;
	
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	@NotNull
	private BigDecimal valorTotal;
	
	@Enumerated(EnumType.STRING) //será salvo o nome da enumeração(ORCAMENTO, EMITIDO ou CANCELADO)
	@Column(nullable = false, length = 20)
	private StatusPedido status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 20)
	@NotNull(message = "A forma de pagamento deverá ser informado.")
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	@NotNull(message = "O vendedor deverá ser informado.")
	private Usuario vendedor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	@NotNull(message = "O cliente deverá ser informado.")
	private Cliente cliente;
	
	@Embedded //embutido
	private EnderecoEntrega enderecoEntrega;
	
	//cadasde ALL para quando salvar um pedido, salvar também os itens do pedido
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
