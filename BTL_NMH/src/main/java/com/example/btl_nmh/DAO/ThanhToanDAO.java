package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.ThanhToan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class ThanhToanDAO extends DAO {

    public ThanhToan lay1HoaDonCuoiCungTheoId(int contractId) {
        ThanhToan thanhToan = null;
        String sql = "SELECT * FROM thanh_toan WHERE id_hopdong = ? ORDER BY ngaytra DESC LIMIT 1";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, contractId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                thanhToan = new ThanhToan();
                thanhToan.setId(resultSet.getInt("id"));
                thanhToan.setPhuongthucthanhtoan(resultSet.getString("phuongthucthanhtoan"));
                thanhToan.setSoTienLai(resultSet.getLong("so_tien_lai"));
                thanhToan.setSoTienNo(resultSet.getLong("so_tien_no"));
                thanhToan.setSoTienTra(resultSet.getLong("so_tien_tra"));
                thanhToan.setNgaytra(resultSet.getDate("ngaytra"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thanhToan;
    }

    public boolean luuThanhToan(ThanhToan thanhToan) {
        String sql = "INSERT INTO thanh_toan (id_hopdong, phuongthucthanhtoan, so_tien_lai, so_tien_no, so_tien_tra, ngaytra) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, thanhToan.getHopDong());
            statement.setString(2, thanhToan.getPhuongthucthanhtoan());
            statement.setLong(3, thanhToan.getSoTienLai());
            statement.setLong(4, thanhToan.getSoTienNo());
            statement.setLong(5, thanhToan.getSoTienTra());
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            statement.setDate(6, sqlDate);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
