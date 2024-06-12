package com.example.btl_nmh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DoiTac {
    private int id;

    private String diaChi;
    private String sdt;
    private String fullName;
    private String tenCongTy;
    private long tongTienVay;
    private long tongTienTra;
    private List<HopDong> hopDongList;
}
