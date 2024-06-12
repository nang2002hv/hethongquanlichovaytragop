package com.example.btl_nmh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class KhachHang extends Nguoi{

    private int id;
    private int thuNhap;
    private String congViec;
    private long soduno;
    private List<TaiSanDamBao> taiSanDamBaoList;
}
