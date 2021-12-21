package ru.angen.final_test.manager;

import ru.angen.final_test.Main;
import ru.angen.final_test.entity.ServiceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    public static List select() throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "select ID,Title,MainImagePath,Cost,Duration,Discount from service";
            Statement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            List<ServiceEntity> list = new ArrayList<>();
            while (rs.next())
            {
             list.add(new ServiceEntity(
                     rs.getInt(1),
                     rs.getString(2),
                     rs.getString(3),
                     rs.getInt(4),
                     rs.getInt(5),
                     rs.getInt(6)
             ));
            }
            return list;
        }
    }
    public static void insert(ServiceEntity serviceEntity) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "INSERT INTO service (Title,MainImagePath,Cost,Duration,Discount) VALUES" +
                    "(?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,serviceEntity.getTitle());
            ps.setString(2,serviceEntity.getImagePath());
            ps.setInt(3,serviceEntity.getCost());
            ps.setInt(4,serviceEntity.getDuration());
            ps.setInt(5,serviceEntity.getDiscount());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
            {
                serviceEntity.setId(keys.getInt(1));
            } else {
                System.out.println("error");
            }


        }
    }
    public static void edit(ServiceEntity serviceEntity) throws SQLException
    {
        try(Connection c = Main.getConnection())
        {
            String sql = "UPDATE service " +
                    "SET Title=?,MainImagePath=?,Cost=?,Duration=?,Discount=? " +
                    "WHERE ID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,serviceEntity.getTitle());
            ps.setString(2,serviceEntity.getImagePath());
            ps.setInt(3,serviceEntity.getCost());
            ps.setInt(4,serviceEntity.getDuration());
            ps.setInt(5,serviceEntity.getDiscount());
            ps.setInt(6,serviceEntity.getId());


            try{
                ps.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    public static void delete(ServiceEntity serviceEntity) throws SQLException {
        try (Connection c = Main.getConnection()) {
            String sql = "DELETE FROM service WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,serviceEntity.getId());
            ps.executeUpdate();
        }
    }

}
