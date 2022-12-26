package br.com.banco.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;

@RestController
public class TransferenciaController {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping(value = "/transferencia", method = RequestMethod.GET)
    public List<Transferencia> Get() {
        return transferenciaRepository.findAll();
    }

    @RequestMapping(value = "/transferencia/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transferencia> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
        if(transferencia.isPresent())
            return new ResponseEntity<Transferencia>(transferencia.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/transferencia", method =  RequestMethod.POST)
    public Transferencia Post(@Validated @RequestBody Transferencia transferencia)
    {
        return transferenciaRepository.save(transferencia);
    }

    @RequestMapping(value = "/transferencia/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Transferencia> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Transferencia newTransferencia)
    {
        Optional<Transferencia> oldTransferecia = transferenciaRepository.findById(id);
        if(oldTransferecia.isPresent()){
        	Transferencia transferencia = oldTransferecia.get();
            transferenciaRepository.save(transferencia);
            return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/transferencia/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
        if(transferencia.isPresent()){
            transferenciaRepository.delete(transferencia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{idConta}", method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> GetByContaId(@PathVariable(value = "idConta") long idConta) {
    	List<Transferencia> transferencia = transferenciaService.getByContaId(idConta);
        if(Objects.nonNull(transferencia) && !transferencia.isEmpty())
            return ResponseEntity.ok(transferencia);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{idConta}/{dataInicio}/{dataFim}", method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> GetByContaIdTempo(@PathVariable(value = "idConta") long idConta, @PathVariable(value = "dataInicio") String dataInicio, 
    														@PathVariable(value = "dataFim") String dataFim) {
    	List<Transferencia> transferencia = transferenciaService.getByContaIdTempo(idConta, dataInicio, dataFim);
    	if(Objects.nonNull(transferencia) && !transferencia.isEmpty())
            return ResponseEntity.ok(transferencia);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{idConta}/{nomeOperadorTransacao}", method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> GetByContaIdOperador(@PathVariable(value = "idConta") long idConta, @PathVariable(value = "nomeOperadorTransacao") String nomeOperadorTransacao) {
    	List<Transferencia> transferencia = transferenciaService.getByContaIdOperador(idConta, nomeOperadorTransacao);
    	if(Objects.nonNull(transferencia) && !transferencia.isEmpty())
            return ResponseEntity.ok(transferencia);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{idConta}/{dataInicio}/{dataFim}/{nomeOperadorTransacao}", method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> GetByContaIdOperador(@PathVariable(value = "idConta") long idConta, @PathVariable(value = "dataInicio") String dataInicio, 
											@PathVariable(value = "dataFim") String dataFim, @PathVariable(value = "nomeOperadorTransacao") String nomeOperadorTransacao) {
    	List<Transferencia> transferencia = transferenciaService.getByContaIdOperadorTempo(idConta, dataInicio, dataFim, nomeOperadorTransacao);
    	if(Objects.nonNull(transferencia) && !transferencia.isEmpty())
            return ResponseEntity.ok(transferencia);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}