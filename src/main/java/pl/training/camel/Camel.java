package pl.training.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.component.activemq.ActiveMQComponent;

// https://github.com/landrzejewski/camel

public class Camel {

    public static void main(String[] args) {

        var jmsComponent = new ActiveMQComponent();
        jmsComponent.setBrokerURL("tcp://localhost:61616");

        try (var context = new DefaultCamelContext()) {
            var propertiesComponent = context.getPropertiesComponent();
            propertiesComponent.setLocation("classpath:training.properties");
            context.getRegistry().bind("ymlFilter", new YmlFileFilter<>());
            context.addComponent("customJms", jmsComponent);
            context.addRoutes(new Routes());
            context.start();
            Thread.sleep(10_000);
        }  catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        /*try (CamelContext context = new DefaultCamelContext()) {
            context.start();
            Thread.sleep(10_000);
        }  catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }*/

       /* try {
            context.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }*/
    }

}