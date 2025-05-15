package app.sysFix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.sysFix.entity.TicketsEntity;
import app.sysFix.enums.StatusAtendimento;

public interface TicketsRepository extends JpaRepository<TicketsEntity, Long>{

	@Query ("SELECT COUNT(t) FROM TicketsEntity t WHERE t.status = 'ATENDIDO' AND MONTH(t.dataFechamento) = :mes AND YEAR(t.dataFechamento) = :ano")
	long countTicketsAtentidosNoMes(@Param("mes") int mes, @Param("ano") int ano);
	
	@Query("SELECT t FROM TicketsEntity t WHERE YEAR(t.dataAbertura) = :ano")
	List<TicketsEntity> listarTicketsPorAno(@Param("ano") int ano);
	
	long countByStatus(StatusAtendimento status);

}
