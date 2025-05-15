package app.sysFix.entity;

import java.time.LocalDate;
import java.util.List;

import app.sysFix.enums.StatusAtendimento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TicketsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(
			name = "tickets_cliente",
			joinColumns = @JoinColumn (name = "tickets_id"),
			inverseJoinColumns = @JoinColumn (name = "cliente_id")
			)
	
	private List<ClienteEntity> cliente;
	
	@ManyToOne
	private ColaboradoresEntity colaboradores;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)//deixando o enum branco
	private StatusAtendimento status;
	
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;
	
	public TicketsEntity(String descricao, StatusAtendimento status, ColaboradoresEntity colaboradores) {
		this.descricao = descricao;
		this.status = status;
		this.colaboradores = colaboradores;
		this.dataAbertura = LocalDate.now();
	}
	
	//public TicketsEntity() {}
}
