package com.outsider.catalogo.infra.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DadosTamanhoValidadorCamposPresentes.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDadosTamanho {
    String message() default "Pelo menos um dos campos em DadosTamanho deve estar preenchido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}