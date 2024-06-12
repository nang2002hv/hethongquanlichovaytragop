package com.example.btl_nmh.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDaMua {

    private int id;

    private long soluong;
    private long tongtien;

    private SanPham sanpham;


    private HopDong hopDong;
}
