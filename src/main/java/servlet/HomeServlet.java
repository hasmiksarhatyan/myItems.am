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

@WebServlet(urlPatterns = "")

public class HomeServlet extends HttpServlet {
    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> last20categories = categoryManager.getAll();
        req.setAttribute("categories", last20categories);
        List<Item> last20items = itemManager.getlast20items();
        req.setAttribute("item", last20items);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}


