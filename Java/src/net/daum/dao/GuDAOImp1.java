package net.daum.dao;

import net.daum.vo.GuVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuDAOImp1 {
    final String DRIVER = "oracle.jdbc.OracleDriver";
    final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

    String user = "night";
    String password = "123456";
    String sql;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    public List<GuVO> getGuList() {

        List<GuVO> arr = new ArrayList<>();

        try {
            sql = "select * from tbl_gu order by gno";
            dbCon(sql, true);

            while (rs.next()) {
                GuVO gv = new GuVO();
                gv.setGno(rs.getInt("gno"));
                gv.setGname(rs.getString("gname"));
                gv.setGtitle(rs.getString("gtitle"));
                gv.setGcont(rs.getString("gcont"));
                gv.setGdate(rs.getString("gdate"));

                arr.add(gv);
            }
            dbConClose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    public int insertGu(GuVO g) {
        int result=-1;
        try {
            sql = "insert into tbl_gu values (gno_seq10.nextval, ?, ?, ?, sysdate)";
            dbCon(sql, false);

            ps.setString(1, g.getGname());
            ps.setString(2, g.getGtitle());
            ps.setString(3, g.getGcont());

            result = ps.executeUpdate();
            dbConClose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void dbCon(String sql, boolean rsGet) {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql);
            if (rsGet) rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbConClose() {
        try {
            if (con != null) con.close();
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}