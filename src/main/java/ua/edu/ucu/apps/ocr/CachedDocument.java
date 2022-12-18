package ua.edu.ucu.apps.ocr;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.*;

@AllArgsConstructor
public class CachedDocument implements Document {
    String gcsPath;

    @Override
    public String parse() throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:cache.sqlite");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS documents (id INTEGER PRIMARY KEY, gcsPath TEXT, text TEXT)");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT text FROM documents WHERE gcsPath = ?");
        preparedStatement.setString(1, gcsPath);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String text = resultSet.getString("text");
            connection.close();
            return text;
        }

        String text = new SmartDocument(gcsPath).parse();
        preparedStatement = connection.prepareStatement("INSERT INTO documents (gcsPath, text) VALUES (?, ?)");
        preparedStatement.setString(1, gcsPath);
        preparedStatement.setString(2, text);
        preparedStatement.executeUpdate();

        connection.close();
        return text;
    }
}
