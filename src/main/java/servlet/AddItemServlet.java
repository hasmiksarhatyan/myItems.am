package servlet;

import lombok.SneakyThrows;
import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;
import model.Category;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();
    private static final String IMAGE_PATH = "C:\\Users\\User\\IdeaProjects\\myItems.am\\projectimages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> all = categoryManager.getAll();
        req.setAttribute("categories", all);
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        Double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part picPart = req.getPart("image");
        String fileName = null;
        if (picPart.getSize() != 0) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + picPart.getSubmittedFileName();
            picPart.write(IMAGE_PATH + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picURL(fileName)
                .user(userManager.getById(user.getId()))
                .build();
        itemManager.add(item);
        resp.sendRedirect("/");
    }
}

