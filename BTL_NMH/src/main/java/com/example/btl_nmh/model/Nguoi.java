package com.example.btl_nmh.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Nguoi {

    private int id;
    private String email;
    private String sdt;
    private String fullName;
    private String diaChi;
    private String ngaySinh;
    private String Cmnd;
}
