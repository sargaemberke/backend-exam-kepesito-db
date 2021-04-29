package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {

        List<String> breeds = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement("SELECT breed FROM dinosaur " +
                    "WHERE actual > expected " +
                    "ORDER BY breed ASC");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                breeds.add(rs.getString("breed"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return breeds;
    }

}
