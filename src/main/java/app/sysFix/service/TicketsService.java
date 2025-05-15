package app.sysFix.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sysFix.entity.TicketsEntity;
import app.sysFix.enums.StatusAtendimento;
import app.sysFix.repository.TicketsRepository;

@Service
public class TicketsService {
	
	@Autowired
	private TicketsRepository ticketsrepository;

	public TicketsEntity findById(long id) {
		TicketsEntity tickets = this.ticketsrepository.findById(id).get();
		return tickets;
	} 
	
	public String save (TicketsEntity tickets) {
		this.ticketsrepository.save(tickets);
		return "chamado salvo com sucesso";
	}
	
	public String update (TicketsEntity tickets, Long id) {
		tickets.setId(id);
		this.ticketsrepository.save(tickets);
		return "chamado atualizado com sucesso";
	}
	
	public String delete(Long id) {
		this.ticketsrepository.deleteById(id);
		return "chamado deletado com sucesso";
	}
	
	public List<TicketsEntity> findAll(){
		List<TicketsEntity> lista = this.ticketsrepository.findAll();
		return lista;
	}
	
	public String concluirChamado (Long ticketId) {
		try {
			TicketsEntity ticket = ticketsrepository.findById(ticketId)
					.orElseThrow(() -> new RuntimeException("tickets nao encontrado"));
			ticket.setStatus(StatusAtendimento.ATENDIDO);
		    ticket.setDataFechamento(LocalDate.now());
			
			ticketsrepository.save(ticket);
			return "chamado finalizado com sucesso";
		} catch (Exception e) {
			return "erro ao finalizar o ticket" + e.getMessage();
		}
	}
	
	public long getTicketsAtendidosNoMes(int mes, int ano) {
		return ticketsrepository.countTicketsAtentidosNoMes(mes, ano);
	}
	
	public List<TicketsEntity> listarTicketsAno(int ano){
		return ticketsrepository.listarTicketsPorAno(ano);
	}
	
	public double calcularCoeficienteAtendimentos() {
		long totalAbertos = ticketsrepository.count();
		long totalAtendidos = ticketsrepository.countByStatus(StatusAtendimento.ATENDIDO);
		if (totalAbertos == 0) return 0.0;
		return (double) totalAtendidos / totalAbertos;
	}
}
