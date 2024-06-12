package com.example.btl_nmh.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiSanTrongHopDong {

    private int id;

    private int soluong;


    private TaiSanDamBao taiSanDamBao;

    private  HopDong hopDong;
}
