import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/test1")
public class Test1Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource ds = (BasicDataSource)servletContext.getAttribute("dataSource");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        try {
            Connection connection = ds.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into customer(id,name,address) values(?,?,?)");
            pst.setInt(1,id);
            pst.setString(2,name);
            pst.setString(3,address);
            int i = pst.executeUpdate();
            if (i>0) {
                resp.getWriter().write("{\"message\": \"student saved...!\"}");
                System.out.println(id+" "+name+" "+address);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
