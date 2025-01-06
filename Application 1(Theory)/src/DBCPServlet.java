import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/dbcp")
public class DBCPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //moved to MyListner
        /*System.out.println("DBCPServlet doGet");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/company");
        dataSource.setUsername("root");
        dataSource.setPassword("Ijse@123");
        dataSource.setMaxTotal(5);
        dataSource.setInitialSize(5);*/


        //common interface to all servlets
       /* ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("dataSource", dataSource);*/

        ServletContext servletContext = req.getServletContext();
        BasicDataSource dataSource = (BasicDataSource)servletContext.getAttribute("dataSource");



        try {
            Connection connection = dataSource.getConnection();
            ResultSet rst = connection.prepareStatement("select * from customer").executeQuery();

            //create json arry
            JsonArrayBuilder allCustomer = Json.createArrayBuilder();
            while (rst.next()){
                int id = rst.getInt(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                System.out.println(id+" "+name+" "+address);

                JsonObjectBuilder customer = Json.createObjectBuilder();
                customer.add("id",id);
                customer.add("name",name);
                customer.add("address",address);

                allCustomer.add(customer);

            }
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
       ServletContext servletContext = req.getServletContext();
        BasicDataSource ds = (BasicDataSource)servletContext.getAttribute("dataSource");


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
