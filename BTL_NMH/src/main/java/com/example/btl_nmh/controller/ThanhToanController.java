package com.example.btl_nmh.controller;

import com.example.btl_nmh.DAO.*;
import com.example.btl_nmh.model.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("thanh-toan")
public class ThanhToanController {
    private KhachHangDao khachHangDao = new KhachHangDao();
    private TaiSanDamBaoTrongHopDongDAO taiSanDamBaoTrongHopDongDAO = new TaiSanDamBaoTrongHopDongDAO();
    private SanPhamDaMuaDAO sanPhamDaMuaDAO = new SanPhamDaMuaDAO();
    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
    private HopDongDAO hopDongDAO = new HopDongDAO();
    @GetMapping("/hopdong")
    public String thanhtoan(@RequestParam int id, HttpSession session) {
        List<HopDong> hopDongList = (List<HopDong>) session.getAttribute("hopDongList");
        HopDong hopDong = findDoiTacById(hopDongList,id);
        session.setAttribute("hopDong", hopDong);
        KhachHang khachang = khachHangDao.getKhachHangByHopDong(id);
        session.setAttribute("khachHang", khachang);
        List<String> taiSanDamBao = taiSanDamBaoTrongHopDongDAO.getTenTaiSanDamBaoByHopDongId(id);
        List<String> sanPhamDaMua = sanPhamDaMuaDAO.getTenMatHangDaMua(id);
        String sau1 = "";
        String sau2 ="";
        for(String x : taiSanDamBao){
            sau1 += x +",";
        }
        for(String x : sanPhamDaMua){
            sau2 += x +",";
        }
        if(sau1 != "" && sau2 != ""){
            sau1 = sau1.substring(0,sau1.length()-1);
            sau2 = sau2.substring(0,sau2.length()-1);
        }
        System.out.println(sau1 + sau2);
        session.setAttribute("taiSanBaoDam", sau1);
        session.setAttribute("sanPhamDaMua", sau2);
        ThanhToan thanhToan = thanhToanDAO.lay1HoaDonCuoiCungTheoId(id);
        System.out.println(hopDong.getLaiSuat() +"/"+ hopDong.getSoTienVay());
        Long sotienlai = (long) (hopDong.getLaiSuat() * hopDong.getSoTienVay());
        thanhToan.setSoTienLai(sotienlai);
        session.setAttribute("thanhToan", thanhToan);
        
        return "thanhtoan";

    }

    public HopDong findDoiTacById(List<HopDong> hopDongList, int id) {
        for (HopDong hopDong : hopDongList) {
            if (hopDong.getId() == id) {
                return hopDong;
            }
        }
        return null;
    }

    @PostMapping("/thanhtoan")
    public String thanhtoan(@RequestParam int id, @RequestParam long soTienLai, @RequestParam String phuongThucThanhToan, @RequestParam long soTienNo, @RequestParam long soTienDong) {
        System.out.println(id +" " + soTienLai + " " + phuongThucThanhToan + " " + soTienNo + " " + soTienDong);
        ThanhToan thanhToan = new ThanhToan();
        thanhToan.setHopDong(id);
        thanhToan.setSoTienLai(soTienLai);
        thanhToan.setSoTienNo(soTienNo-soTienDong+soTienLai);
        thanhToan.setPhuongthucthanhtoan(phuongThucThanhToan);
        thanhToan.setSoTienTra(soTienDong);
        thanhToanDAO.luuThanhToan(thanhToan);
        return "quanlihopdong";
    }
}
