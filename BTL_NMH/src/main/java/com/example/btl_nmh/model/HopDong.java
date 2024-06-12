package com.example.btl_nmh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HopDong {
    private int id;

    private String maHD;
    private Date ngayki;
    private String thoiHanThanhToan;
    private long soTienVay;
    private long soTienTraTruoc;
    private double laiSuat;

    private DoiTac doiTac;

    private NhanVien nhanVien;


    private KhachHang khachHang;


    private List<TaiSanTrongHopDong> taiSanTrongHopDong;


    private List<SanPhamDaMua> sanPhamDaMuas;

    private List<ThanhToan> thanhToans;
}

