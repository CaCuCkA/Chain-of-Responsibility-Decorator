package ua.edu.ucu.apps.ocr;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.SQLException;

@AllArgsConstructor
public class TimedDocument implements Document {
    public String gcsPath;

    @Override
    public String parse() throws IOException, SQLException {
        long startSmart = System.currentTimeMillis();
        String text = new SmartDocument(gcsPath).parse();
        long endSmart = System.currentTimeMillis();
        System.out.println("SmartDocument time (s): " + (endSmart - startSmart) / 1000.0);

        long startCached = System.currentTimeMillis();
        new CachedDocument(gcsPath).parse();
        long endCached = System.currentTimeMillis();
        System.out.println("CachedDocument time (s): " + (endCached - startCached) / 1000.0);

        return text;
    }
}
