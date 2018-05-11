package br.com.pedidovenda.teste;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.TipoPessoa;

public class TesteConexao {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Venda");
		EntityManager manager = factory.createEntityManager();
		
		try {
		EntityTransaction transacao = manager.getTransaction();
		transacao.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Rodrigo");
		cliente.setEmail("rodrigo@gmail.com");
		cliente.setDocumentoReceitaFederal("555.555.555-55");
		cliente.setTipo(TipoPessoa.FISICA);
		
		manager.persist(cliente);
		
		transacao.commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
		}
	}
}
