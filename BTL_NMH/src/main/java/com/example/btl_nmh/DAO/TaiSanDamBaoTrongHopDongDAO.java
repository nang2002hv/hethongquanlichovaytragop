package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.TaiSanDamBao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiSanDamBaoTrongHopDongDAO extends DAO {

    public List<String> getTenTaiSanDamBaoByHopDongId(int hopDongId) {
        List<String> tenTaiSanList = new ArrayList<>();
        String sql = "SELECT tsdb.ten_tai_san " +
                "FROM tai_san_dam_bao tsdb " +
                "INNER JOIN tai_san_trong_hop_dong tshd ON tsdb.id = tshd.id_taisandambao " +
                "INNER JOIN hop_dong hd ON tshd.id_hopdong = hd.id " +
                "WHERE hdh.id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hopDongId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String tenTaiSan = resultSet.getString("ten_tai_san");
                    tenTaiSanList.add(tenTaiSan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenTaiSanList;
    }
}
