package com.example.btl_nmh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThanhToan {

    private int id;

    private String phuongthucthanhtoan;
    private long soTienLai;
    private long soTienNo;
    private long soTienTra;
    private Date ngaytra;
    private int hopDong;
}
