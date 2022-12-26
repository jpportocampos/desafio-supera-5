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

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;

@RestController
public class ContaController {
    @Autowired
    private ContaRepository _contaRepository;

    @RequestMapping(value = "/conta", method = RequestMethod.GET)
    public List<Conta> Get() {
        return _contaRepository.findAll();
    }

    @RequestMapping(value = "/conta/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = _contaRepository.findById(id);
        if(conta.isPresent())
            return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/conta", method =  RequestMethod.POST)
    public Conta Post(@Validated @RequestBody Conta conta)
    {
        return _contaRepository.save(conta);
    }

    @RequestMapping(value = "/conta/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Conta> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Conta newConta)
    {
        Optional<Conta> oldConta = _contaRepository.findById(id);
        if(oldConta.isPresent()){
        	Conta conta = oldConta.get();
            _contaRepository.save(conta);
            return new ResponseEntity<Conta>(conta, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = _contaRepository.findById(id);
        if(conta.isPresent()){
            _contaRepository.delete(conta.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}