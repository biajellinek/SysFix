package app.sysFix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.sysFix.entity.ColaboradoresEntity;
import app.sysFix.entity.TicketsEntity;
import app.sysFix.enums.StatusAtendimento;
import app.sysFix.repository.TicketsRepository;
import app.sysFix.service.ColaboradorService;
import app.sysFix.service.TicketsService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/chamados")
public class TicketsController {
	
	@Autowired
	private ColaboradorService colaboradoresService;
	
	@Autowired
	private TicketsService ticketsService;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@PostMapping("/abrirChamado/{colabId}")
	public ResponseEntity<?> abrirChamado (@PathVariable Long colabId,@RequestBody String descricao) {
		
	try {
		ColaboradoresEntity colaboradores = this.colaboradoresService.findById(colabId);
		
		TicketsEntity tickets = new TicketsEntity(descricao, StatusAtendimento.SEM_ALOCACAO, colaboradores);
		TicketsEntity salvo = ticketsRepository.save(tickets);
		//tickets.setDataAbertura(LocalDate.now());
		
		return ResponseEntity.ok(salvo);
	} catch (Exception e) {
		return new ResponseEntity<String>("colaborador nao encontrado", HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PutMapping("/concluirChamado/{ticketId}")
	public ResponseEntity<String> concluirChamado(@PathVariable Long ticketId){
		try {
			String resultado = ticketsService.concluirChamado(ticketId);
			return ResponseEntity.ok(resultado);
		} catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erro interno ao concluir o chamado: " + e.getMessage());
}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody TicketsEntity tickets,@PathVariable  long id) {
		try {
			String mensagem = this.ticketsService.update(tickets, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable long id) {
		try {
			String mensagem = this.ticketsService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<TicketsEntity>> findAll(){
		try {
			List<TicketsEntity> lista = this.ticketsService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<TicketsEntity> findById(@PathVariable long id) {
		try {
			TicketsEntity tickets = this.ticketsService.findById(id);
			return new ResponseEntity<>(tickets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping("/relatorio/atendidos-mensal")
	public ResponseEntity<?> getAtendidosNoMes(@RequestParam int mes, @RequestParam int ano) {
		try {
			long quantidade = ticketsService.getTicketsAtendidosNoMes(mes, ano);
			return new ResponseEntity<>("tickets atedidos no mes: " + quantidade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("erro ao buscar tickets", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/relatorio/listagem-anual")
	public ResponseEntity<?> listarPorAno(@RequestParam int ano) {
		try {
			return new ResponseEntity<>(ticketsService.listarTicketsAno(ano), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("erro ao listar", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/relatorio/coeficiente")
	public ResponseEntity<?> getCoeficienteAtendimentos() {
		try {
			double coef = ticketsService.calcularCoeficienteAtendimentos();
			return new ResponseEntity<>("atendimentos resolvidos: " + coef, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("erro ao calcular os atendimentos", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
