package com.example.btl_nmh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SanPham {

    private int id;
    private String tenMh;
    private long gia;
    private List<SanPhamDaMua> sanPhamDaMua;
}
