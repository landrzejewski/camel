package pl.training.camel;

import org.apache.camel.Converter;
import org.apache.camel.util.IOHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Converter
public final class IOConverter {

    @Converter
    public static InputStream toInputStream(URL url) throws IOException {
        return IOHelper.buffered(url.openStream());
    }

}