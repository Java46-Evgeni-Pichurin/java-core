package telran.json.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Retention(RUNTIME)
@Target(FIELD)
public @interface JsonField {
    String value();
    String message() default "Field validation";
}
