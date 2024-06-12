package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends DAO{

    public boolean login(String username, String password) {
        System.out.println(username+password);
        NhanVien nhanVien = new NhanVien();
        String sql = "SELECT * FROM nhan_vien WHERE user_name = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public NhanVien checkRolle(String username, String password) {
        NhanVien nhanVien = new NhanVien();
        String sql = "SELECT * FROM nhan_vien WHERE user_name = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Thiết lập tham số cho câu lệnh SQL
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                nhanVien.setId(id);
                String sql1 = "SELECT * FROM btl_nmh.nguoi WHERE id = ?";


                nhanVien.setViTri(resultSet.getString("vi_tri"));
                nhanVien.setPassword(password);
                nhanVien.setUserName(username);
                nhanVien.setMaNv(resultSet.getString("ma_nv"));
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setInt(1, id);
                resultSet = statement1.executeQuery();
                if(resultSet.next()){
                    nhanVien.setFullName(resultSet.getString("full_name"));

                }

                return nhanVien;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
