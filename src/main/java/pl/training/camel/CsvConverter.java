package pl.training.camel;

public class CsvConverter {

    public String process(String text) {
        return text.replaceAll(";", ",");
    }

}
