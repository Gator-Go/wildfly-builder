
package ppp.ppp.ppp.servlet.helper;

import ppp.ppp.ppp.entity.Yyyyy;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;

/**
 * Lllll
 *
 * This is the validation helper class for the Yyyyy entity (YyyyyErrorHelper).
 * It validates a Yyyyy object (typically after form submission) using Bean Validation (Validator)
 * and additional custom checks.
 * It is used by YyyyyServlet on add and edit actions.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class YyyyyErrorHelper {

    @Inject
    private Validator validator;


    public boolean checkYyyyyData(Yyyyy yyyyy, HttpServletRequest request) {

        boolean errorFlag = doViolations(yyyyy, request);

        return errorFlag;
    }

    public boolean doViolations(Yyyyy yyyyy, HttpServletRequest request){

        boolean errorFlag = false;

___CHECK_VIOLATIONS_STRING___
___CHECK_VIOLATIONS_ENUM___
___CHECK_VIOLATIONS_TAG___
___CHECK_VIOLATIONS_CLOB___
___CHECK_VIOLATIONS_INTEGER___
___CHECK_VIOLATIONS_LONG___
___CHECK_VIOLATIONS_DOUBLE___
___CHECK_VIOLATIONS_BIG_DECIMAL___
___CHECK_VIOLATIONS_MONEY___
___CHECK_VIOLATIONS_LOC___
___CHECK_VIOLATIONS_CURRENT_LOC___
___CHECK_VIOLATIONS_DATE___
___CHECK_VIOLATIONS_DATE_TIME___

        return errorFlag;
    }

}
