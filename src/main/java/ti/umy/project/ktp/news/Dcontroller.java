/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ti.umy.project.ktp.news;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Asus
 */
@Controller
public class Dcontroller {
    KtpdataJpaController dataCtrl = new KtpdataJpaController();
    List<Ktpdata> newKtpData = new ArrayList<>();
    
    @RequestMapping("/main")
    public String getMain(){
    return "menu";
    }
    
    @RequestMapping("/ktpdata")
    public String getDataKTP(Model model){
        int record = dataCtrl.getKtpdataCount();
        try{
           newKtpData = dataCtrl.findKtpdataEntities().subList(0,record);
        }catch(Exception e){
           
        }
        model.addAttribute("data", newKtpData);
        return "database";
    }
      @RequestMapping("/editktpdata")
        public String doEdit(){
        return "editktpdata";
        
    }
}
