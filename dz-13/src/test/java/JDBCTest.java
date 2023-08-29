import org.testng.annotations.Test;

import java.sql.*;

public class JDBCTest
{
    @Test
    public void test() {
        String url = String.format(
          "jdbc:mysql://%s:%s/%s",
                "localhost",
                "3306",
                "opencart-db"
        );

        String username = "opencart";
        String password = "opencart";
        try {
            Connection conn = DriverManager.getConnection(url , username , password);
            String insertQuery = "INSERT INTO homework_user_data (first_name, last_name, login) VALUES ('John', 'Doe', 'nevergup')";
            conn.createStatement().executeUpdate(insertQuery);


            ResultSet result = conn.createStatement().executeQuery("SELECT id, first_name, last_name, login FROM homework_user_data");
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String login = result.getString("login");
                System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Login: " + login);
            }

            int idToUPDATE = 12;
            String updateQuery = "UPDATE homework_user_data SET first_name = 'Slava', last_name = 'Ukraine', login = 'haleluya' WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setInt(1, idToUPDATE);
            preparedStatement.executeUpdate();

            int idToDelete = 12;
            String deleteQuery = "DELETE FROM homework_user_data WHERE id = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, idToDelete );
            deleteStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException("Couldn't connect to database" , e);
        }
    }

}
