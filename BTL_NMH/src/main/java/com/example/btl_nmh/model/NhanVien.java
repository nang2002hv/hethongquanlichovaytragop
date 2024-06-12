package com.example.btl_nmh.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class NhanVien extends Nguoi{
    private int id;
    private String maNv;
    private String viTri;
    private String userName;
    private String password;
}
