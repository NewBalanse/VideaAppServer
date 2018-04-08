package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream ServletSout = resp.getOutputStream();

        try {
            Connection connection = getConnection();

            ServletSout.write("Hello world".getBytes());
            ServletSout.flush();
            ServletSout.close();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String user = dbUri.getUserInfo().split(":")[0];
            String pass = dbUri.getUserInfo().split(":")[1];

            String dbURL = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            return DriverManager.getConnection(dbURL, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
