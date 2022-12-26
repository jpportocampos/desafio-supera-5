package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query(value = "select * from transferencia t where t.conta_id= :contaId", nativeQuery = true)
	List<Transferencia> getTransferenciaByContaId(Long contaId);
	
	@Query(value = "select * from transferencia t where t.conta_id= :contaId and t.data_transferencia between :dataInicio and :dataFim", nativeQuery = true)
	List<Transferencia> getTransferenciaByPeriodo(Long contaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}
