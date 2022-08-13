package com.bank.msparameter.models.dao;

import com.bank.msparameter.models.documents.Parameter;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ParameterDao extends ReactiveMongoRepository<Parameter, String> {

}
