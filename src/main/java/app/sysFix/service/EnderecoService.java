package app.sysFix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sysFix.entity.EnderecoEntity;
import app.sysFix.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public String save (EnderecoEntity endereco) {
		this.enderecoRepository.save(endereco);
		return "endereco salvo com sucesso";
	}

	public String update(EnderecoEntity endereco, long id) {
		endereco.setId(id);
		this.enderecoRepository.save(endereco);
		return "endereco foi atualizado com sucesoo";
	}
	
	public String delete (long id) {
		this.enderecoRepository.deleteById(id);
		return "endereco deletado com sucesso";
	}
	
	public List<EnderecoEntity> findAll(){
		List<EnderecoEntity> lista = this.enderecoRepository.findAll();
		return lista;
	}
	
	public EnderecoEntity findById(long id) {
		EnderecoEntity endereco = this.enderecoRepository.findById(id).get();
		return endereco;
	}
}
