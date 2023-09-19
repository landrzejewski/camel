package pl.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;

public class ConvertCsvExpression implements Expression {

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        var body = exchange.getIn().getBody(String.class)
                .replaceAll(";", ",");
        return (T) body;
    }

}
