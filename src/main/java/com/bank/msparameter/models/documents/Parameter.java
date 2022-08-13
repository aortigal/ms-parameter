package com.bank.msparameter.models.documents;

import com.bank.msparameter.models.utils.Audit;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "parameters")
public class Parameter extends Audit {
    @Id
    private String id;
    @NotNull(message = "code must not be null")
    private Integer code;
    @NotNull(message = "value must not be null")
    private String value;
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "argument must not be null")
    private String argument;

}
