package pl.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.CORRELATION_ID;
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
        /*from("file:data/input?noop=true&delay={{file.interval}}&filter=#ymlFilter")
                //.filter(new FileTypePredicate())
                .process(new LoggingProcessor())
                .to("file:data/output");

        from("ftp://46.41.138.121?delay=5000&delete=false&passiveMode=true&username=user&password=123")
                //.to("activemq:orders");
                //.toD("jms:queue:${header.finalDestination}"); // dynamic destination
                .to("customJms:oders");

        from("customJms:orders")
                .log("Message received: ${header.CamelFileName}")
                .to("log:pl.training.camel");*/

        /*from("file:data/input/source?noop=true&delay=5000")
                .filter(not(header("CamelFileName").endsWith("json")))
                .filter(xpath("/order[not(@test)]"))
                .log(INFO, "Received file: ${header.CamelFileName}")
                .to("jms:placedOrders");*/

        var jsonXmlDataFormat = new JacksonXMLDataFormat();
        jsonXmlDataFormat.setPrettyPrint("true");

        from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("https://raw.githubusercontent.com/landrzejewski/camel/master/data/input/orders.csv")
                //.convertBodyTo(String.class)
                //.process(csvConverter)
                //.bean(CsvConverter.class)
                //.transform(body().regexReplaceAll("; ",","))
                //.transform(new ConvertCsvExpression())
                .unmarshal().bindy(Csv, Order.class)
                //marshal().json()
                .marshal(jsonXmlDataFormat);
                //.log("\n${body}");
                //.to("file://data/output?fileName=orders-${header.Date}.xml");
        // content based router

        /*from("file:data/input?noop=true&delay={{file.interval}}")
                .choice()
                    .when(header("CamelFileName").endsWith("xml"))
                        .to("direct:orders-xml")
                    .when(header("CamelFileName").regex("^.*csv$"))
                        .to("direct:orders-csv")
                    .otherwise()
                        .to("direct:orders-other")
                .log("\n${body}");

        from("direct:orders-xml")
                .to("log:xml");
        from("direct:orders-csv")
                .unmarshal().bindy(Csv, Order.class)
                .split(body())
                //.bean(OrderToSql.class)
                //.to("log:csv")
                //.to("jdbc:dataSource?useHeadersAsParameters=true");
                .bean(OrdersRepository.class, "save")
                .to("log:csv");

        // multicasting

        from("direct:orders-other")
                .multicast()
                .parallelProcessing()
                .to("log:first-other", "log:second-other");*/
        /*

        // recipient list

        from("direct:orders-csv")
                //.bean(Recipients.class);
                .setHeader("recipients", () -> "direct:report")
                //.setHeader("recipients", method(Recipients.class, "accounting"))
                .recipientList(header("recipients"));

        from("direct:report")
                .to("log:report");


        // Slip router

        from("direct:queueA").to("log:queueA");
        from("direct:queueB").to("log:queueB");
        from("direct:queueC").to("log:queueC");

        *//*from("ile:data/input?noop=true&delay={{file.interval}})
                .setHeader("destination").method(ComputeSlip.class)
                .routingSlip(header("destination"), ";");
        *//*

        // Load balancer

        from("file:data/input?noop=true&delay={{file.interval}}")
                .loadBalance().roundRobin()
                .to("direct:queueA", "direct:queueB", "direct:queueC")
                .end();

        // split // aggregate

        from("file:data/input?noop=true&delay={{file.interval}}")
                .filter(header("CamelFileName").endsWith("csv"))
                .split(bodyAs(String.class).tokenize("\n"))
                .unmarshal().bindy(Csv, Order.class)
                .bean(OrderProcessor.class)
                .aggregate(header(CORRELATION_ID), new OrderAggregationStrategy())
                .completionSize(2)
                //.aggregationRepository()
                .to("log:split-aggregate");

        */

        /*errorHandler(defaultErrorHandler()
                .retryAttemptedLogInterval(1000)
                .maximumRedeliveries(3)
                .useExponentialBackOff()
                .backOffMultiplier(2)
        );*/

       /* errorHandler(deadLetterChannel("log:dead?level=Error")
                .useOriginalBody()
                //.onPrepareFailure()
        );*/

        /*from("file:module-four/source?noop=true")
                //.onException(RuntimeException.class, ValidationException.class).maximumRedeliveries(3)
                .onException(IllegalArgumentException.class)
                .continued(true)

                .convertBodyTo(String.class)
                .doTry()
                    .bean("upperCase")
                    .bean("lowerCase")
                .doCatch(IllegalAccessException.class)
                    .process(new FailureProcessor())
                    .to("log:pl.training.camel")
                .end()
                .to("log:pl.training.camel");*/

        from("file:data/input?noop=true&delay={{file.interval}}")
                .to("as400:main");


    }

}
