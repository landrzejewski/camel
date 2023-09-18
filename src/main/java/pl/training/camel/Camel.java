package pl.training.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import java.io.IOException;
import java.util.Stack;

public class Camel {

    public static void main(String[] args) {
        var main = new Main();
        try (var context = main.configure()) {
            context.addRoutesBuilder(new Routes());
            main.run();
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