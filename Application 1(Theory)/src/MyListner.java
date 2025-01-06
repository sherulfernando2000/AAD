import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class MyListner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/company");
        dataSource.setUsername("root");
        dataSource.setPassword("Ijse@123");
        dataSource.setMaxTotal(5);
        dataSource.setInitialSize(5);

        //common interface to all servlets
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dataSource", dataSource);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
        ServletContext servletcontext = sce.getServletContext();
        BasicDataSource dataSource = (BasicDataSource) servletcontext.getAttribute("dataSource");

        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
