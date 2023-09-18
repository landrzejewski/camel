package pl.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.component.file.GenericFile;

import static org.apache.camel.Exchange.FILE_NAME;

public class FileTypePredicate implements Predicate {

    private static final String ALLOWED_EXTENSION = "yml";

    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn()
                .getBody(GenericFile.class)
                .getFileName()
                .endsWith(ALLOWED_EXTENSION);
    }

}
