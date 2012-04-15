package ru.ncedu.magpie.basicClasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet(urlPatterns = {"/db"})
public class TestDataSource extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TestDataSource.class);

    @Resource(name = "jdbc/magpieDB")
    private DataSource dataSource;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            //This also works fine, but @Resource is nicer
            /*
                Context ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx.lookup("jdbc/magpieDB");
            */
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                Statement statement = connection.createStatement();
                statement.execute("select username from USERS");
                ResultSet resultSet = statement.getResultSet();
                PrintWriter out = response.getWriter();

                while (resultSet.next()) {
                    out.print(resultSet.getString("username"));
                    out.print("\n");
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }

        } catch (SQLException e) {
            logger.error("Error!", e);
        } catch (IOException e) {
            logger.error("Error!", e);
        }
    }

}
