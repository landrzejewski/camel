package pl.training.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;
import static org.apache.camel.model.dataformat.BindyType.Csv;
import static org.apache.camel.support.builder.PredicateBuilder.not;

@Component
public class Routes extends RouteBuilder {

    private final CsvConverterProcessor csvConverter;

    @Autowired
    public Routes(CsvConverterProcessor csvConverter) {
        this.csvConverter = csvConverter;
    }

    @Override
    public void configure() throws Exception {
        // env:VARIABLE_NAME // using ENV
        // RAW(value) // escaped uri value
        // #name // accessing registered element from context
        from("file:data/input?noop=true&delay={{file.interval}}&filter=#ymlFilter")
                //.filter(new FileTypePredicate())
                .process(new LoggingProcessor())
                .to("file:data/output");

        from("ftp://46.41.138.121?delay=5000&delete=false&passiveMode=true&username=user&password=123")
                //.to("activemq:orders");
                //.toD("jms:queue:${header.finalDestination}"); // dynamic destination
                .to("customJms:oders");

        from("customJms:orders")
                .log("Message received: ${header.CamelFileName}")
                .to("log:pl.training.camel");

        /*from("file:data/input/source?noop=true&delay=5000")
                .filter(not(header("CamelFileName").endsWith("json")))
                .filter(xpath("/order[not(@test)]"))
                .log(INFO, "Received file: ${header.CamelFileName}")
                .to("jms:placedOrders");*/

        from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("https://raw.githubusercontent.com/landrzejewski/camel/master/data/input/orders.csv")
                //.convertBodyTo(String.class)
                //.process(csvConverter)
                //.bean(CsvConverter.class)
                //.transform(body().regexReplaceAll("; ",","))
                //.transform(new ConvertCsvExpression())
                .unmarshal().bindy(Csv, Order.class)
                .log("\n${body}");



    }

}
