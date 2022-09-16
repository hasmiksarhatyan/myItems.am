package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void add(Item item) {
        String sql = "insert into myitems_am.item(title,price,category_id,pic_url,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicURL());
            ps.setInt(5, item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemByUserId(int userId) {
        String sql = "select * from item where user_id=" + userId;
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getlast20items() {
        String sql = "select * from item order by id desc limit 20";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public List<Item> getByCategoryId(int categoryId) {
        String sql = "select * from item where category_id=" + categoryId + " order by id desc limit 20 ";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setTitle(resultSet.getString("title"));
        item.setPrice(resultSet.getDouble("price"));
        item.setCategory(categoryManager.getById(resultSet.getInt("category_id")));
        item.setPicURL(resultSet.getString("pic_url"));
        item.setUser(userManager.getById(resultSet.getInt("user_id")));
        return item;
    }

    public void removeItemById(int id) {
        String sql = "delete from item where id=" + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Item item) {
        String sql = "update item set title=?,price=?,category_id=?,pic_url=?,user_id=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicURL());
            ps.setInt(5, item.getUser().getId());
            ps.setInt(6, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}