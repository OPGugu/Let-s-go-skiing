package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.dao.ProductDaoImpl;
import product.vo.Product;

@WebServlet("/productSelectByID")
public class ProductSelectByProductID extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        Integer productID = Integer.valueOf(request.getParameter("productID"));

        ProductDaoImpl productDaoImpl = new ProductDaoImpl();

        Product product = productDaoImpl.selectByProductID(productID);

        Gson gson = new Gson();
        String json = gson.toJson(product);

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}





