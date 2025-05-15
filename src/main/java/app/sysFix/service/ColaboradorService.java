package app.sysFix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sysFix.entity.ColaboradoresEntity;
import app.sysFix.entity.EnderecoEntity;
import app.sysFix.repository.ColaboradoresRepository;
import app.sysFix.repository.EnderecoRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	@Autowired
	private ColaboradoresRepository colaboradoresRepository;

	public ColaboradoresEntity findById(long id) {
		ColaboradoresEntity colaboradores = this.colaboradoresRepository.findById(id).get();
		return colaboradores;
	} 
	
	public String save (ColaboradoresEntity colaborador) {
		if (colaborador.getEndereco() != null && colaborador.getEndereco().getId() != null) {
			EnderecoEntity enderecoExistente = enderecoRepository
			.findById(colaborador.getEndereco().getId())
			.orElseThrow(() -> new RuntimeException("endereco nao encontrado"));
			
			colaborador.setEndereco(enderecoExistente);
		}
		colaboradoresRepository.save(colaborador);
		return "colaborador salvo com sucesso";
	}

	public String update(ColaboradoresEntity colaborador, long id) {
		colaborador.setId(id);
		this.colaboradoresRepository.save(colaborador);
		return "colaborador foi atualizado com sucesoo";
	}
	
	public String delete (long id) {
		this.colaboradoresRepository.deleteById(id);
		return "colaborador deletado com sucesso";
	}
	
	public List<ColaboradoresEntity> findAll(){
		List<ColaboradoresEntity> lista = this.colaboradoresRepository.findAll();
		return lista;
	}
}
