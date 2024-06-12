package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.SanPhamDaMua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDaMuaDAO extends DAO {

    public List<String> getTenMatHangDaMua(int hopDongId) {
        List<String> tenMatHangList = new ArrayList<>();
        String sql = "SELECT sp.ten_mh " +
                "FROM san_pham_da_mua spdm " +
                "INNER JOIN san_pham sp ON spdm.id_sanpham = sp.id " +
                "WHERE spdm.id_hopdong = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hopDongId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String tenMatHang = resultSet.getString("ten_mh");
                    tenMatHangList.add(tenMatHang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenMatHangList;
    }
}
