package app.sysFix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sysFix.entity.ClienteEntity;
import app.sysFix.entity.EnderecoEntity;
import app.sysFix.repository.ClienteRepository;
import app.sysFix.repository.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public String save (ClienteEntity cliente) {
		if (cliente.getEndereco() != null && cliente.getEndereco().getId() != null) {
			EnderecoEntity enderecoExistente = enderecoRepository
			.findById(cliente.getEndereco().getId())
			.orElseThrow(() -> new RuntimeException("endereco nao encontrado"));
			
			cliente.setEndereco(enderecoExistente);
		}this.clienteRepository.save(cliente);
		return "cliente salvo com sucesso";
	}

	public String update(ClienteEntity cliente, long id) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);
		return "cliente foi atualizado com sucesoo";
	}
	
	public String delete (long id) {
		this.clienteRepository.deleteById(id);
		return "cliente deletado com sucesso";
	}
	
	public List<ClienteEntity> findAll(){
		List<ClienteEntity> lista = this.clienteRepository.findAll();
		return lista;
	}
	
	public ClienteEntity findById(long id) {
		ClienteEntity cliente = this.clienteRepository.findById(id).get();
		return cliente;
	}
}
