package com.example.btl_nmh.controller;

import com.example.btl_nmh.DAO.HopDongDAO;
import com.example.btl_nmh.model.HopDong;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hopdong")
public class HopDongController {
     private HopDongDAO hopDongDAO = new HopDongDAO();

     @GetMapping("/xemhopdong")
     public String xemhopdong(@RequestParam int id, HttpSession session) {
         List<HopDong> hopDongList = hopDongDAO.getAllContracts(id);
         if(hopDongList.size() > 0){
            session.setAttribute("hopDongList", hopDongList);
            return "quanlihopdong";
         }
         return "quanlikhachhang";
     }
}
