package pl.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class CsvConverterProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        var body = exchange.getIn().getBody(String.class)
                        .replaceAll(";", ",");
        exchange.getIn().setBody(body);
    }

}
