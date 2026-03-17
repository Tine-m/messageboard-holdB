package app.persistence;

import app.entities.Post;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostMapper {

    public static Post findPost(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from post where post_id=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                return new Post(post_id, title, content, image);
            }
            else
            {
                throw new DatabaseException("Fejl i søgning");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static List<Post> findAll(ConnectionPool connectionPool) throws DatabaseException {
        List<Post> postList = new ArrayList<>();
        String sql = "select * from post order by post_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int post_id = rs.getInt("post_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                postList.add(new Post(post_id, title, content, image));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
        return postList;
    }
}
