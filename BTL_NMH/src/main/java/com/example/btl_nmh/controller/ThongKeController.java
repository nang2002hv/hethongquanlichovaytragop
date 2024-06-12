package com.example.btl_nmh.controller;

import com.example.btl_nmh.DAO.HopDongDAO;
import com.example.btl_nmh.DAO.KhachHangDao;
import com.example.btl_nmh.DAO.ThanhToanDAO;
import com.example.btl_nmh.model.HopDong;
import com.example.btl_nmh.model.KhachHang;
import com.example.btl_nmh.model.ThanhToan;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {
    private KhachHangDao khachHangDao = new KhachHangDao();
    private HopDongDAO hopDongDAO = new HopDongDAO();
    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
    @GetMapping ("/thongkeduno")
    public String thongke(HttpSession session) {
        session.removeAttribute("khachHangList");
        return "thongke";
    }



    @PostMapping ("/thongkeduno")
    public String thongKe(HttpSession session){
        List<KhachHang> khachHangList = khachHangDao.getAllKhachHang();
        for(int i = 0; i < khachHangList.size(); i++){
            long so_du_no = 0;
            List<HopDong> hopDongList = hopDongDAO.getAllContracts(khachHangList.get(i).getId());
            if(hopDongList.size() > 0){
                for(int j = 0; j < hopDongList.size(); j++){
                    ThanhToan thanhToan = thanhToanDAO.lay1HoaDonCuoiCungTheoId(hopDongList.get(j).getId());
                    if(thanhToan != null){
                        so_du_no += thanhToan.getSoTienNo();
                    }
                }
            }
            khachHangList.get(i).setSoduno(so_du_no);
            System.out.println(khachHangList.get(i).getSoduno() + khachHangList.get(i).getId());
        }
        khachHangList.sort((kh1, kh2) -> Long.compare(kh2.getSoduno(), kh1.getSoduno()));
        session.setAttribute("khachHangList", khachHangList);
        return "thongke";
    }

    @GetMapping("/xem")
    public String xem(@RequestParam int id,HttpSession session) {
        List<HopDong> hopDongList = hopDongDAO.getAllContracts(id);
        session.setAttribute("hopDongList",hopDongList);
        return "thongtinhopdong";
    }


}
