package app.sysFix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.sysFix.entity.EnderecoEntity;
import app.sysFix.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody EnderecoEntity endereco) {

		try {

			String mensagem = this.enderecoService.save(endereco);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody EnderecoEntity endereco,@PathVariable  long id) {
		try {
			String mensagem = this.enderecoService.update(endereco, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable long id) {
		try {
			String mensagem = this.enderecoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<EnderecoEntity>> findAll(){
		try {
			List<EnderecoEntity> lista = this.enderecoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<EnderecoEntity> findById(@PathVariable long id) {
		try {
			EnderecoEntity endereco = this.enderecoService.findById(id);
			return new ResponseEntity<>(endereco, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

}

