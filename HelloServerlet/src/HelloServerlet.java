import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

@WebServlet(urlPatterns = "/hello")
public class HelloServerlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /* PrintWriter out = resp.getWriter();
       out.println("<h1 styles=\"color:red\">Helloooo</h1>");*/

        String servletPath = req.getServletPath();
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String method = req.getMethod();
        String pathInfo = req.getPathInfo();

        /*
        servletPath: /hello
        requestURI: /HelloServerlet_Web_exploded/hello
        contextPath: /HelloServerlet_Web_exploded
        method: GET
        */


        System.out.println("servletPath: " + servletPath);
        System.out.println("requestURI: " + requestURI);
        System.out.println("contextPath: " + contextPath);
        System.out.println("method: " + method);
        System.out.println("pathInfo: " + pathInfo);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("POST method invoked");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("doDELETE method invoked");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("doPUT method invoked");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("doOption method invoked");
    }
}