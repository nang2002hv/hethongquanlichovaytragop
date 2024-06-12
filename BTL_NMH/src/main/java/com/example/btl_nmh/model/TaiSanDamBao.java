package com.example.btl_nmh.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiSanDamBao {

    private int id;

    private String diaChi;
    private String tenTaiSan;
    private String giaTri;
    private String tinhTrang;

    private KhachHang khachHang;


    private List<TaiSanTrongHopDong> taiSanTrongHopDongList;
}
