package main.anotacoes;

import java.lang.annotation.*;

/**
 * @author riverson
 */

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {
    String value();
}
