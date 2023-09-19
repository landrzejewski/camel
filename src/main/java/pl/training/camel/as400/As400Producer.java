package pl.training.camel.as400;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultAsyncProducer;

import java.util.concurrent.ExecutorService;

public class As400Producer extends DefaultAsyncProducer {

    private ExecutorService executorService;

    public As400Producer(Endpoint endpoint) {
        super(endpoint);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        executorService = getEndpoint().getCamelContext().getExecutorServiceManager().newFixedThreadPool(this, "As400", 50);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        getEndpoint().getCamelContext().getExecutorServiceManager().shutdown(executorService);
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {
        executorService.submit(new Task(exchange, callback));
        return false;
    }

}

class Task implements Runnable {

    private Exchange exchange;
    private AsyncCallback asyncCallback;

    public Task(Exchange exchange, AsyncCallback asyncCallback) {
        this.exchange = exchange;
        this.asyncCallback = asyncCallback;
    }

    @Override
    public void run() {
        System.out.println("Calling As400");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("As400 finished work");
        exchange.getOut().setBody("As400 results");
        asyncCallback.done(false);
    }

}