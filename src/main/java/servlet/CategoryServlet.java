package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")

public class CategoryServlet extends HttpServlet {
    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryManager.getAll();
        req.setAttribute("categories", categories);
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        List<Item> items = itemManager.getByCategoryId(categoryId);
        req.setAttribute("itembycatId", items);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);


    }
}

