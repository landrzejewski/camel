package pl.training.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

// https://github.com/landrzejewski/camel

public class Camel {

    public static void main(String[] args) {
        try (var context = new DefaultCamelContext()) {
            var propertiesComponent = context.getPropertiesComponent();
            propertiesComponent.setLocation("classpath:training.properties");
            context.getRegistry().bind("ymlFilter", new YmlFileFilter<>());
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

        /*
        // System.out.println("Hello World");

        User user = new User("Marcin");
        // user.setFirstName("Jan");
        user.sayHello();
        */
    }

}

class User {

    private String firstName;
    private int age;

    public User(String name) {
        firstName = name;
    }

    public void sayHello() {
        System.out.println("Hi, I am " + firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}