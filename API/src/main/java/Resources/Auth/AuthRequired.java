package Resources.Auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface AuthRequired {

    ROLE value() default ROLE.MEMBER;

}

enum ROLE{
    MEMBER(0), MODERATOR(1);

    int val;

    ROLE(int val){
        this.val = val;
    }
}