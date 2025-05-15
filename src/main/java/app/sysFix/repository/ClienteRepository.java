package app.sysFix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.sysFix.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{


}
