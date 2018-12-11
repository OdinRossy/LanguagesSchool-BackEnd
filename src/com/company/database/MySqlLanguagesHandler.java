package com.company.database;

import com.company.model.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlLanguagesHandler extends MySqlStudentsHandler {

    public static List<Language> selectAllLanguages() {
        List<Language> languages = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "SELECT * FROM LanguagesSchool.languages;";
            MySqlHandler.preparedStatement = connection.prepareStatement(query);
            resultSet = MySqlHandler.preparedStatement.executeQuery();
            while (resultSet.next()){
                Language language = new Language();
                language.setId(resultSet.getInt("id"));
                language.setName(resultSet.getString("name"));
                languages.add(language);
            }
            return languages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreparedStatement();
            }
        }
    }
}
