package com.bank.msparameter.services;

import com.bank.msparameter.handler.ResponseHandler;
import com.bank.msparameter.models.documents.Parameter;
import reactor.core.publisher.Mono;

public interface ParameterService {
    Mono<ResponseHandler> create(Parameter p);

    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> update(String id,Parameter p);

    Mono<ResponseHandler> delete(String id);

    Mono<ResponseHandler> findByCode(String code,String value);
}
