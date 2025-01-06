import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("item servlet");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/company",
                         "root",
                        "Ijse@123"
            );
            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet rst = pstm.executeQuery();

            //create json array
            JsonArrayBuilder allItem = Json.createArrayBuilder();

            while (rst.next()){
                String code = rst.getString(1);
                String desc = rst.getString(2);
                int qtyOnHand = rst.getInt(3);
                double price = rst.getDouble(4);

                JsonObjectBuilder item = Json.createObjectBuilder();
                item.add("code", code);
                item.add("desc", desc);
                item.add("qtyOnHand", qtyOnHand);
                item.add("price", price);
                allItem.add(item);
            }
            resp.setContentType("application/json");
            resp.getWriter().write(allItem.build().toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("item updated");

        String idString = req.getParameter("code");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        String qty = req.getParameter("qty");

        if (idString == null || idString.isEmpty() || desc == null || desc.isEmpty() || price == null || price.isEmpty() || qty == null || qty.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad request
            resp.getWriter().write("{\"error\" : \"customer id is required\"}");
        }else {
            try {
//                int id = Integer.parseInt(idString);

                ItemDTO itemById = findItemByCode(idString);
                if (itemById == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
                    resp.getWriter().write("{\"error\" : \"item not found\"}");

                }else{

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Ijse@123");
                        PreparedStatement pst = connection.prepareStatement("update item set description =?  qtyOnHand = ? unitPrice =? where code=?");
                        pst.setString(4, itemById.getId());
                        pst.setString(1, itemById.getDesc());
                        pst.setInt(2, (int) itemById.getQty());
                        pst.setDouble(3, itemById.getPrice());
                        pst.executeUpdate();
                        resp.setStatus(HttpServletResponse.SC_OK); //202 ok
                        resp.getWriter().write("{\"message\" : \"item updated\"}");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }



                }


            }catch (NumberFormatException e){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad request
                resp.getWriter().write("{\"error\" : \"invalid id or age\"}");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("item saved");
        String code =req.getParameter("code");
        String desc = req.getParameter("desc");
        double price = Double.parseDouble(req.getParameter("price"));
        int qty =Integer.parseInt(req.getParameter("qty"));


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Ijse@123");
            PreparedStatement pst = connection.prepareStatement("insert into item(code,description,qtyOnHand,unitPrice) values(?,?,?,?)");
            pst.setString(1,code);
            pst.setString(2,desc);
            pst.setDouble(4,price);
            pst.setInt(3,qty);
            int i = pst.executeUpdate();
            if (i>0) {
                resp.getWriter().write("{\"message\": \"item saved...!\"}");

            }




        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\" : \"Invalid age\"}");

        }

    }


    private ItemDTO findItemByCode(String code) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Ijse@123");
            PreparedStatement pst = connection.prepareStatement("select * from item where code=?");
            ResultSet resultSet = pst.executeQuery();
            ItemDTO itemDTO = null;
            while (resultSet.next()) {
                itemDTO = new ItemDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3),resultSet.getDouble(4));

            }
            return itemDTO;


        } catch (Exception e) {
            new RuntimeException(e);

        }

        return null;

    }


}

