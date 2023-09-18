package pl.training.camel;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class YmlFileFilter<T> implements GenericFileFilter<T> {

    private static final String EXTENSION = "yml";

    public boolean accept(GenericFile<T> file) {
        return file.getFileName().endsWith(EXTENSION);
    }

}