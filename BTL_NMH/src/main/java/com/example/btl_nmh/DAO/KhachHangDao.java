package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao extends DAO {

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangList = new ArrayList<>();
        String sql = "SELECT kh.*, ng.* FROM khach_hang kh JOIN nguoi ng ON kh.id = ng.id";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setId(resultSet.getInt("id"));
                khachHang.setThuNhap(resultSet.getInt("thu_nhap"));
                khachHang.setCongViec(resultSet.getString("cong_viec"));
                khachHang.setEmail(resultSet.getString("email"));
                khachHang.setSdt(resultSet.getString("sdt"));
                khachHang.setFullName(resultSet.getString("full_name"));
                khachHang.setDiaChi(resultSet.getString("dia_chi"));
                khachHang.setNgaySinh(resultSet.getString("ngay_sinh"));
                khachHang.setCmnd(resultSet.getString("cmnd"));
                khachHangList.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHangList;
    }

    public List<KhachHang> searchKhachHangByName(String fullName) {
        List<KhachHang> khachHangList = new ArrayList<>();
        String sql = "SELECT kh.*, ng.* FROM khach_hang kh JOIN nguoi ng ON kh.id = ng.id WHERE ng.full_name LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + fullName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    KhachHang khachHang = new KhachHang();
                    khachHang.setId(resultSet.getInt("id"));
                    khachHang.setThuNhap(resultSet.getInt("thu_nhap"));
                    khachHang.setCongViec(resultSet.getString("cong_viec"));
                    khachHang.setEmail(resultSet.getString("email"));
                    khachHang.setSdt(resultSet.getString("sdt"));
                    khachHang.setFullName(resultSet.getString("full_name"));
                    khachHang.setDiaChi(resultSet.getString("dia_chi"));
                    khachHang.setNgaySinh(resultSet.getString("ngay_sinh"));
                    khachHang.setCmnd(resultSet.getString("cmnd"));
                    khachHangList.add(khachHang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHangList;
    }

    public KhachHang getKhachHangByHopDong(int maHopDong) {
        KhachHang khachHang = null;
        String sql = "SELECT kh.*, ng.* " +
                "FROM khach_hang kh " +
                "INNER JOIN hop_dong hd ON kh.id = hd.id_khachhang " +
                "INNER JOIN nguoi ng ON kh.id = ng.id " +
                "WHERE hd.id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maHopDong);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    khachHang = new KhachHang();
                    khachHang.setId(resultSet.getInt("id"));
                    khachHang.setFullName(resultSet.getString("full_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return khachHang;
    }

}
