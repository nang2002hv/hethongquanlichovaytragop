package com.example.btl_nmh.controller;

import ch.qos.logback.core.model.Model;
import com.example.btl_nmh.DAO.DoiTacDAO;
import com.example.btl_nmh.model.DoiTac;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doitac")
public class DoiTacController {
    private DoiTacDAO doiTacDAO = new DoiTacDAO();
    @GetMapping("/quanlidoitac")
    public String quanlidoitac(HttpSession session) {
        session.removeAttribute("doiTacList");
        session.removeAttribute("timkiem");
        return "quanlidoitac";
    }

    @GetMapping("search")
    public String search(@RequestParam String timkiem, HttpSession session) {
        List<DoiTac> doiTac = doiTacDAO.timKiemDoiTac(timkiem);
        session.setAttribute("doiTacList", doiTac);
        System.out.println(timkiem);
        session.removeAttribute("timkiem");
        session.setAttribute("timkiem", timkiem);
        return "quanlidoitac";
    }

    @GetMapping("/update")
    public String update(@RequestParam int id, HttpSession session) {
        List<DoiTac> doiTacList = (List<DoiTac>) session.getAttribute("doiTacList");
        DoiTac doiTac = findDoiTacById(doiTacList, id);
        session.removeAttribute("doiTac");
        session.setAttribute("doiTac",doiTac);
        return "capnhat";
    }

    @PostMapping("/update")
    public String update(@RequestParam String address,
                         @RequestParam String name,
                         @RequestParam String company,
                         @RequestParam long total_payment,
                         @RequestParam String phone,
                         @RequestParam long total_loan,
                         @RequestParam int id,
                         HttpSession session) {
        List<DoiTac> doiTacList = (List<DoiTac>) session.getAttribute("doiTacList");

        for (DoiTac doiTac : doiTacList) {
            if (doiTac.getId() == id) {
                doiTac.setDiaChi(address);
                doiTac.setFullName(name);
                doiTac.setTenCongTy(company);
                doiTac.setTongTienTra(total_payment);
                doiTac.setSdt(phone);
                doiTac.setTongTienVay(total_loan);
                boolean kiemtra = doiTacDAO.capNhatDoiTac(doiTac);
                break;
            }
        }
        session.setAttribute("doiTacList", doiTacList);
        return "quanlidoitac";
    }


    public DoiTac findDoiTacById(List<DoiTac> doiTacList, int id) {
        for (DoiTac doiTac : doiTacList) {
            if (doiTac.getId() == id) {
                return doiTac;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    @GetMapping("/create")
    public String create() {
        return "themmoi";
    }

    @PostMapping("/create")
    public String create(@RequestParam String address,
                         @RequestParam String name,
                         @RequestParam String company,
                         @RequestParam long total_payment,
                         @RequestParam String phone,
                         @RequestParam long total_loan
                         ) {

        DoiTac doiTac = new DoiTac();
        doiTac.setDiaChi(address);
        doiTac.setFullName(name);
        doiTac.setTenCongTy(company);
        doiTac.setTongTienTra(total_payment);
        doiTac.setSdt(phone);
        doiTac.setTongTienVay(total_loan);
        boolean kiemtra = doiTacDAO.themDoiTac(doiTac);

        return "quanlidoitac";
    }
}
