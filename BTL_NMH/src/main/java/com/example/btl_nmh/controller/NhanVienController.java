package com.example.btl_nmh.controller;


import ch.qos.logback.core.model.Model;
import com.example.btl_nmh.DAO.LoginDAO;
import com.example.btl_nmh.model.NhanVien;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    private LoginDAO loginDAO = new LoginDAO();
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
     public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession){
        System.out.println(username +password);
        if(loginDAO.login(username,password)){
            NhanVien nhanVien = new NhanVien();
            nhanVien = loginDAO.checkRolle(username,password);
            System.out.println(nhanVien.getViTri());
            if(nhanVien != null && nhanVien.getViTri().equals("1")){
                System.out.println(nhanVien.getFullName() + "/");
                httpSession.setAttribute("nhanvien",nhanVien);
                return "quanlidoitac";
            }
            if(nhanVien != null && nhanVien.getViTri().equals("2")){
                System.out.println(nhanVien.getFullName() + "/");
                httpSession.setAttribute("nhanvien",nhanVien);
                return "quanlikhachhang";
            }
        }
        return "login";
    }
}
