package pl.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static org.apache.camel.Exchange.FILE_NAME;

public class LoggingProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        var fileName = exchange.getIn()
                .getHeader(FILE_NAME)
                .toString();
        System.out.println("Processing file: " + fileName);
    }

}
