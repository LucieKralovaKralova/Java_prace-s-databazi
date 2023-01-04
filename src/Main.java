import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/flowers",
                    "flowers_siud",
                    "ASdf1234");


            Statement statement = connection.createStatement();

            /** pokud poisonous = 1  ==> rostline JE jedovatá
             *  pokud poisonous = 0  ==> rostlina NENÍ jedovatá
             *
             */

            // insertNewFlower(statement, "Bledule", "White",1);
            // insertNewFlower(statement, "Kopretina", "White", 0);
            updateDescriptionFlower(statement, 1, "Pozor na cibulku - obsahuje největší koncentraci jedu!");
            deleteOfPoisonousFlowers(statement);
            printAllFlowers(statement);

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void insertNewFlower(Statement statement, String name, String color, int poisonous) throws SQLException {
        int numberOfAffectedRoows = statement.executeUpdate("INSERT INTO flower (name, color, poisonous) " +
                "VALUES ('"+name+"', '"+color+"', '"+poisonous+"')");
        System.out.println("number of affected rows:"+numberOfAffectedRoows);
    }

    private static void printAllFlowers(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM flower;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt(1));
        }
    }
    private static void updateDescriptionFlower(Statement statement, int id, String description) throws SQLException {
        int numberOfUpdateRows = statement.executeUpdate("UPDATE flower " +
                "SET description = '"+description+"'" +
                "WHERE id ="+id);
        System.out.println("number of update rows: "+numberOfUpdateRows+" --- id = "+id+" --- new description = "+description);
    }

    private static void deleteOfPoisonousFlowers(Statement statement) throws SQLException {
        int numberOfDeleteRows = statement.executeUpdate("DELETE FROM flower WHERE poisonous = '1'");
        System.out.println("number of delete rows: "+numberOfDeleteRows);
    }
}