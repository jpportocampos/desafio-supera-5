package br.com.banco.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	public List<Transferencia> getByContaId(Long idConta) {
		List<Transferencia> transferencia = transferenciaRepository.getTransferenciaByContaId(idConta);
		return transferencia;
	}
	
	public List<Transferencia> getByContaIdTempo(Long idConta, String dataInicioString, String dataFimString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime dataInicio = LocalDateTime.parse(dataInicioString, formatter);
		LocalDateTime dataFim = LocalDateTime.parse(dataFimString, formatter);
		
		List<Transferencia> transferencia = transferenciaRepository.getTransferenciaByPeriodo(idConta, dataInicio, dataFim);
		return transferencia;
	}
	
	public List<Transferencia> getByContaIdOperador(Long idConta, String nomeOperadorTransacao) {
		List<Transferencia> transferencia = transferenciaRepository.getTransferenciaByContaId(idConta);
		List<Transferencia> retorno = new ArrayList<Transferencia>();
		for (int i = 0; i < transferencia.size(); i++) {
			if (transferencia.get(i).getNomeOperadorTransacao() != null && transferencia.get(i).getNomeOperadorTransacao().equals(nomeOperadorTransacao)) {
				retorno.add(transferencia.get(i));
			}
		}
		return retorno;
	}
	
	public List<Transferencia> getByContaIdOperadorTempo(Long idConta, String dataInicioString, String dataFimString, String nomeOperadorTransacao) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime dataInicio = LocalDateTime.parse(dataInicioString, formatter);
		LocalDateTime dataFim = LocalDateTime.parse(dataFimString, formatter);
		
		List<Transferencia> transferencia = transferenciaRepository.getTransferenciaByPeriodo(idConta, dataInicio, dataFim);
		List<Transferencia> retorno = new ArrayList<Transferencia>();
		for (int i = 0; i < transferencia.size(); i++) {
			if (transferencia.get(i).getNomeOperadorTransacao() != null && transferencia.get(i).getNomeOperadorTransacao().equals(nomeOperadorTransacao)) {
				retorno.add(transferencia.get(i));
			}
		}
		return retorno;
	}

}
