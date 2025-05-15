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

import app.sysFix.entity.ColaboradoresEntity;
import app.sysFix.service.ColaboradorService;

@RestController
@RequestMapping("/api/colaborador")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ColaboradoresEntity colaborador) {

		try {

			String mensagem = this.colaboradorService.save(colaborador);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody ColaboradoresEntity colaborador,@PathVariable  long id) {
		try {
			String mensagem = this.colaboradorService.update(colaborador, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable long id) {
		try {
			String mensagem = this.colaboradorService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<ColaboradoresEntity>> findAll(){
		try {
			List<ColaboradoresEntity> lista = this.colaboradorService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<ColaboradoresEntity> findById(@PathVariable long id) {
		try {
			ColaboradoresEntity colaborador = this.colaboradorService.findById(id);
			return new ResponseEntity<>(colaborador, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

}
