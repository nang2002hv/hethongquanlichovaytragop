package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.HopDong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HopDongDAO extends DAO {

    public List<HopDong> getAllContracts(int id_khachang) {
        List<HopDong> contracts = new ArrayList<>();
        String sql = "SELECT * FROM hop_dong where id_khachhang = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_khachang);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HopDong contract = new HopDong();
                contract.setId(resultSet.getInt("id"));
                 contract.setMaHD(resultSet.getString("mahd"));
                 contract.setNgayki(resultSet.getDate("ngayki"));
                 contract.setThoiHanThanhToan(resultSet.getString("thoi_han_thanh_toan"));
                 contract.setSoTienVay(resultSet.getLong("so_tien_vay"));
                 contract.setSoTienTraTruoc(resultSet.getLong("so_tien_tra_truoc"));
                 contract.setLaiSuat(resultSet.getDouble("lai_suat"));
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
}
