package com.bank.msparameter.controllers;

import com.bank.msparameter.handler.ResponseHandler;
import com.bank.msparameter.models.documents.Parameter;
import com.bank.msparameter.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/parameter")
public class ParameterControllers {

    @Autowired
    private ParameterService parameterService;

    @PostMapping
    public Mono<ResponseHandler> create(@Valid @RequestBody Parameter p) {
        return parameterService.create(p);
    }

    @GetMapping
    public Mono<ResponseHandler> findAll() {
        return parameterService.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ResponseHandler> find(@PathVariable String id) {
        return parameterService.find(id);
    }

    @PutMapping("/{id}")
    public Mono<ResponseHandler> update(@PathVariable("id") String id,@Valid @RequestBody Parameter p) {
        return parameterService.update(id,p);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseHandler> delete(@PathVariable("id") String id) {
        return parameterService.delete(id);
    }

    @GetMapping(value ={"/catalogue/{code}","/catalogue/{code}/{value}"})
    public Mono<ResponseHandler> findByCode(@PathVariable String code,@PathVariable(required = false) String value) {
        return parameterService.findByCode(code, value);
    }
}
