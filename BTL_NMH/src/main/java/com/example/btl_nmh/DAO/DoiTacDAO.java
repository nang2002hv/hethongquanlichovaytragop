package com.example.btl_nmh.DAO;

import com.example.btl_nmh.model.DoiTac;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoiTacDAO extends DAO{
    public List<DoiTac> timKiemDoiTac(String timkiem) {
        List<DoiTac> doiTacList = new ArrayList<>();
        if(timkiem != ""){
            timkiem = "%" + timkiem + "%";
        }
        String sql = "select * from doi_tac where ten_cong_ty like ?";
        try(Connection con = getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, timkiem);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DoiTac doiTac = new DoiTac();
                doiTac.setId(resultSet.getInt("id"));
                doiTac.setFullName(resultSet.getString("full_name"));
                doiTac.setSdt(resultSet.getString("sdt"));
                doiTac.setDiaChi(resultSet.getString("dia_chi"));
                doiTac.setTongTienTra(resultSet.getLong("tong_tien_tra"));
                doiTac.setTongTienVay(resultSet.getLong("tong_tien_vay"));
                doiTac.setTenCongTy(resultSet.getString("ten_cong_ty"));
                doiTacList.add(doiTac);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doiTacList;
    }

    public boolean themDoiTac(DoiTac doiTac) {
        String sql = "INSERT INTO doi_tac (full_name, sdt, dia_chi, tong_tien_tra, tong_tien_vay, ten_cong_ty) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, doiTac.getFullName());
            preparedStatement.setString(2, doiTac.getSdt());
            preparedStatement.setString(3, doiTac.getDiaChi());
            preparedStatement.setLong(4, doiTac.getTongTienTra());
            preparedStatement.setLong(5, doiTac.getTongTienVay());
            preparedStatement.setString(6, doiTac.getTenCongTy());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting doi tac: " + e.getMessage());
        }
    }

    public boolean capNhatDoiTac(DoiTac doiTac) {
        String sql = "UPDATE doi_tac SET full_name = ?, sdt = ?, dia_chi = ?, tong_tien_tra = ?, tong_tien_vay = ?, ten_cong_ty = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, doiTac.getFullName());
            preparedStatement.setString(2, doiTac.getSdt());
            preparedStatement.setString(3, doiTac.getDiaChi());
            preparedStatement.setLong(4, doiTac.getTongTienTra());
            preparedStatement.setLong(5, doiTac.getTongTienVay());
            preparedStatement.setString(6, doiTac.getTenCongTy());
            preparedStatement.setInt(7, doiTac.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating doi tac: " + e.getMessage());
        }
    }


}
