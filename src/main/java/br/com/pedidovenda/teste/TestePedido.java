package br.com.pedidovenda.teste;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.EnderecoEntrega;
import br.com.pedidovenda.model.FormaPagamento;
import br.com.pedidovenda.model.ItemPedido;
import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.model.Usuario;

public class TestePedido {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Venda"); //cria a fábrica de sessão
		EntityManager manager = factory.createEntityManager();  //cria o gerenciador da sessão

		try {
			EntityTransaction transacao = manager.getTransaction(); //obtém a transação e inicializa
			transacao.begin();
			
			Cliente cliente = manager.find(Cliente.class, 1L);  //busca e armazena o cliente com ID 1
			Produto produto = manager.find(Produto.class, 1L);  //busca e armazena o produto com ID 1
			Usuario vendedor = manager.find(Usuario.class, 1L); //busca e armazena o usuário com ID 1
			
			EnderecoEntrega enderecoEntrega = new EnderecoEntrega();  //instancia o endereço de entrega
			enderecoEntrega.setCep("20000-100");
			enderecoEntrega.setCidade("Rio de janeiro");
			enderecoEntrega.setLogradouro("Rua dos Mercados");
			enderecoEntrega.setNumero("10"); 
			enderecoEntrega.setUf("RJ");
			
			Pedido pedido = new Pedido();   //instancia o pedido
			pedido.setCliente(cliente);
			pedido.setDataCriacao(new Date());
			pedido.setDataEntrega(new Date());
			pedido.setEnderecoEntrega(enderecoEntrega);
			pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
			pedido.setObservacao("aberto das 12 horas às 16 horas");
			pedido.setStatus(StatusPedido.ORCAMENTO);
			pedido.setValorDesconto(BigDecimal.ZERO);
			pedido.setValorFrete(BigDecimal.ZERO);
			pedido.setValorTotal(new BigDecimal(24.2));
			pedido.setVendedor(vendedor);
			
			ItemPedido itemPedido = new ItemPedido();  //instancia o item do pedido
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(10);
			itemPedido.setValorUnitario(new BigDecimal(2.50));
			
			pedido.getItens().add(itemPedido);  //adiciona os itens no pedido
			
			manager.persist(pedido);   //persiste os dados no BD
			
			transacao.commit();
			
			} catch (Exception e) {
				manager.getTransaction().rollback(); //caso ocorra um exception retorna a transação para o estado anterior
				e.printStackTrace();
			} finally {
				if (manager.isOpen()) {
					manager.close();    //finaliza a sessao caso a mesma esteja aberta
				}
			}

	}

}
