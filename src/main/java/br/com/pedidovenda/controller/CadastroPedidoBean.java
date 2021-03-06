package br.com.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import br.com.pedidovenda.model.EnderecoEntrega;
import br.com.pedidovenda.model.Pedido;

@Named
@RequestScoped
public class CadastroPedidoBean {
	private Pedido pedido;
	private List<Integer> itens;
	
	public CadastroPedidoBean() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public void salvar() {
		
	}

	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	
}
