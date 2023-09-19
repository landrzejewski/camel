package pl.training.camel;

import org.apache.camel.Headers;
import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class OrderToSql {

    private final AtomicLong atomicLong = new AtomicLong();

    public String map(Order order,
                      @Headers Map<String, Object> headers) {
        headers.put("id", atomicLong.incrementAndGet());
        headers.put("orderName", order.getName());
        headers.put("orderPrice", order.getPrice());
        headers.put("promoProduct", order.isPromoProduct());
        return "insert into orders (id, name, price, PROMO_PRODUCT) values (:?id, :?orderName,:?orderPrice, :?promoProduct)";
    }

}



/*

Exchange	The Camel exchange. This contains the values that will be bound to the method parameters.
Message	The Camel input message. It contains the body that’s often bound to the first method parameter.
CamelContext	The Camel context. This can be used in special circumstances when you need access to all of Camel’s moving parts.
TypeConverter	The Camel type-converter mechanism. This can be used when you need to convert types. Chapter 3 covered the type-converter mechanism.
Registry	The bean registry. This allows you to look up beans in the registry.
Exception	An exception, if one was thrown. Camel will bind to this only if the exchange has failed and contains
            an exception. This allows you to use beans to handle errors. Chapter 11 covers error handling; you can
            find an example of using a custom bean to handle failures in section 11.4.4.

@Body	Binds the parameter to the message body.
@Header(name)	Binds the parameter to the message header with the given name.
@Headers	Binds the parameter to all the input headers. The parameter must be a java.util.Map type.
@ExchangeProperty(name)	Binds the parameter to the exchange property with the given name.
@ExchangeProperties	Binds the parameter to all the exchange properties. The parameter must be a java.util.Map type.
@ExchangeException	Binds the parameter to the exception set on the exchange.
@Attachments	Binds the parameter to the message attachments. The parameter must be a java.util.Map type.

@Bean	Invokes a method on a bean	camel-core
@Constant	Evaluates as a constant value	camel-core
@Groovy	Evaluates a Groovy script	camel-script
@JavaScript	Evaluates a JavaScript script	camel-script
@JsonPath	Evaluates a JsonPath expression	camel-jsonpath
@MVEL	Evaluates a MVEL script	camel-mvel
@Simple	Evaluates a Simple expression. (Simple is a built-in language provided with Camel; see appendix A for more details.)	camel-core
@SpEL	Evaluates a Spring expression	camel-spring
@XPath	Evaluates an XPath expression	camel-core
@XQuery	Evaluates an XQuery expression	camel-saxon
 */
