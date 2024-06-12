package com.example.btl_nmh.controller;

import com.example.btl_nmh.DAO.KhachHangDao;
import com.example.btl_nmh.model.KhachHang;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    private KhachHangDao khachHangDao = new KhachHangDao();

    @GetMapping("/quanlikhachhang")
    public String khachang(HttpSession session) {
        session.removeAttribute("khachHangList");
        return "quanlikhachhang";
    }

    @PostMapping("/quanlikhachhang")
    public String khachangSubmit(@RequestParam String timkiem, HttpSession session) {
        System.out.println(1);
        List<KhachHang> khachHangList = khachHangDao.searchKhachHangByName(timkiem);
        session.setAttribute("khachHangList", khachHangList);
        return "quanlikhachhang";
    }


}
