package br.com.banco.controller;

import java.util.List;
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

@RestController
public class TransferenciaController {
    @Autowired
    private TransferenciaRepository _transferenciaRepository;

    @RequestMapping(value = "/transferencia", method = RequestMethod.GET)
    public List<Transferencia> Get() {
        return _transferenciaRepository.findAll();
    }

    @RequestMapping(value = "/transferencia/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transferencia> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Transferencia> transferencia = _transferenciaRepository.findById(id);
        if(transferencia.isPresent())
            return new ResponseEntity<Transferencia>(transferencia.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/transferencia", method =  RequestMethod.POST)
    public Transferencia Post(@Validated @RequestBody Transferencia transferencia)
    {
        return _transferenciaRepository.save(transferencia);
    }

    @RequestMapping(value = "/transferencia/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Transferencia> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Transferencia newTransferencia)
    {
        Optional<Transferencia> oldTransferecia = _transferenciaRepository.findById(id);
        if(oldTransferecia.isPresent()){
        	Transferencia transferencia = oldTransferecia.get();
            _transferenciaRepository.save(transferencia);
            return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/transferencia/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Transferencia> transferencia = _transferenciaRepository.findById(id);
        if(transferencia.isPresent()){
            _transferenciaRepository.delete(transferencia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}