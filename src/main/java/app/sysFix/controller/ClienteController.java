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

import app.sysFix.entity.ClienteEntity;
import app.sysFix.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ClienteEntity cliente) {

		try {

			String mensagem = this.clienteService.save(cliente);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody ClienteEntity cliente,@PathVariable  long id) {
		try {
			String mensagem = this.clienteService.update(cliente, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable long id) {
		try {
			String mensagem = this.clienteService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<ClienteEntity>> findAll(){
		try {
			List<ClienteEntity> lista = this.clienteService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<ClienteEntity> findById(@PathVariable long id) {
		try {
			ClienteEntity cliente = this.clienteService.findById(id);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}
}
