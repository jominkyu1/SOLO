package mkcrud.mkboard.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcItemRepository implements ItemRepository{

    private final DataSource dataSource;

    @Autowired
    public JdbcItemRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int itemCnt(){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try{
            connection = dataSource.getConnection();
            String sqlQuery = "select count(*) from board";
            ps = callQuery(sqlQuery, connection);
            rs = ps.executeQuery();

            if(rs.next()) return rs.getInt(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(ps!=null) ps.close();
                if(rs!=null) rs.close();
                if(connection!=null) connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return -1;
    }

    @Override
    public List<Item> showList() {
        List<Item> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try{
            connection = dataSource.getConnection();
            String sqlQuery = "select * from board";
            ps = callQuery(sqlQuery, connection);
            rs = ps.executeQuery();

            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getLong(1));
                item.setTitle(rs.getString(2));
                item.setContent(rs.getString(3));

                list.add(item);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(ps!=null) ps.close();
                if(rs!=null) rs.close();
                if(connection!=null) connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long saveItem(Item item) {
        Long lastId=-1L;
        PreparedStatement ps = null;
        Connection connection = null;
        try{
            String sqlQuery = "insert into board values (?, ?, ?)";
            List<Item> items = showList();
            lastId = items.get(0).getId();
            connection = dataSource.getConnection();

            ps = callQuery(sqlQuery, connection);

            ps.setString(1, String.valueOf(++lastId));
            ps.setString(2, item.getTitle());
            ps.setString(3, item.getContent());
            ps.execute();

        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(ps!=null) ps.close();
                if(connection!=null) connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return lastId;
    }

    @Override
    public Item findItemById(Long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            String sqlQuery = "select * from board where id=?";
            connection = dataSource.getConnection();

            ps = callQuery(sqlQuery, connection);
            ps.setString(1, String.valueOf(id));
            rs = ps.executeQuery();

            if(rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong(1));
                item.setTitle(rs.getString(2));
                item.setContent(rs.getString(3));
                return item;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(ps!=null) ps.close();
                if(rs!=null) rs.close();
                if(connection!=null) connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void itemUpdate(Long itemId, Item item) {
        PreparedStatement ps = null;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            String sqlQuery = "update board set title=?, content=? where id=?";
            ps = callQuery(sqlQuery, connection);

            ps.setString(1, item.getTitle());
            ps.setString(2, item.getContent());
            ps.setLong(3, item.getId());
            ps.execute();

        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(ps!=null) ps.close();
                if(connection!=null) connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private PreparedStatement callQuery(String sqlQuery, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlQuery);

        return ps;
    }
}
