package com.bank.msparameter.services.impl;

import com.bank.msparameter.handler.ResponseHandler;
import com.bank.msparameter.models.dao.ParameterDao;
import com.bank.msparameter.models.documents.Parameter;
import com.bank.msparameter.services.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterDao dao;

    private static final Logger log = LoggerFactory.getLogger(ParameterServiceImpl.class);

    @Override
    public Mono<ResponseHandler> create(Parameter p) {
        log.info("[INI] Create Parameter");
        p.setDateRegister(LocalDateTime.now());
        return dao.save(p)
                .doOnNext(parameter -> log.info(parameter.toString()))
                .map(parameter -> new ResponseHandler("Done", HttpStatus.OK, parameter))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] Create Parameter"));
    }

    @Override
    public Mono<ResponseHandler> findAll() {
        log.info("[INI] FindAll Parameter");
        return dao.findAll()
                .doOnNext(parameter -> log.info(parameter.toString()))
                .collectList().map(parameters -> new ResponseHandler("Done", HttpStatus.OK, parameters))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] FindAll Parameter"));

    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        log.info("[INI] Find Parameter");
        return dao.findById(id)
                .doOnNext(parameter -> log.info(parameter.toString()))
                .map(parameter -> new ResponseHandler("Done", HttpStatus.OK, parameter))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] Find Parameter"));
    }

    @Override
    public Mono<ResponseHandler> update(String id,Parameter p) {
        log.info("[INI] Update Parameter");
        return dao.existsById(id).flatMap(check -> {
                    if (check){
                        p.setDateUpdate(LocalDateTime.now());
                        return dao.save(p)
                                .doOnNext(parameter -> log.info(parameter.toString()))
                                .map(parameter -> new ResponseHandler("Done", HttpStatus.OK, parameter))
                                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
                    }
                    else
                        return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));

                })
                .doFinally(fin -> log.info("[END] Update Parameter"));
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        log.info("[INI] Delete Parameter");
        return dao.existsById(id).flatMap(check -> {
                    if (check)
                        return dao.deleteById(id).then(Mono.just(new ResponseHandler("Done", HttpStatus.OK, null)));
                    else
                        return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
                })
                .doFinally(fin -> log.info("[END] Delete Parameter"));
    }

    @Override
    public Mono<ResponseHandler> findByCode(String code,String value) {
        log.info("[INI] FindByCode Parameter");

        Flux<Parameter> parameters = dao.findAll();

        return parameters
                .filter(p -> p.getCode().toString().equals(code) && (value!=null?p.getValue().equals(value):true))
                .doOnNext(p -> log.info(p.toString()))
                .collectList().map(p -> new ResponseHandler("Done", HttpStatus.OK, p))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] FindByCode Parameter"));

    }
}
