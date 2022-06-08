/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ti.umy.project.ktp.news.dummy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Controller
public class DummyController {
    //bagian ngambil dari jpa
    DummyJpaController dummyController = new DummyJpaController();
  List<Dummy> data = new ArrayList<>();
 //request map sini untuk membaca data dummynya
  @RequestMapping("/read")
  public String getDummy(Model m) {
    try {
      data = dummyController.findDummyEntities();

    } catch (Exception e) { //bagian try catch ini tuh mengecek data berhasil diambil atau tidak, jika try data berhasil diambil jika catch berarti data sudah keambil

    }
    m.addAttribute("data", data); //untuk model data (nanti ketika nambahin nantinya akan ditampilkan ke web)
    return "dummy";
  }
  // beralih halaman ke create data
  @RequestMapping("/create")
  public String createDummy() {
    return "dummy/create";
  }
  // method post ini tuh untuk mempost atau mengirim data yang ditambahkan
  @PostMapping(value = "/newdata", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  public String newDummyData(@RequestParam("gambar") MultipartFile multiFile, HttpServletRequest request, HttpServletResponse response)
      throws ParseException, Exception {
    Dummy dummyData = new Dummy();
// bagian fungsi parsing parameter (dari yang id itutuh di parse nanti masuk ke db nanti jadi int)
    int id = Integer.parseInt(request.getParameter("id"));
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("tanggal"));
    byte[] img = multiFile.getBytes();
    dummyData.setId(id); //sett untuk mengatur formatnya
    dummyData.setTanggal(date);
    dummyData.setGambar(img);
// dibagian sini tuh buat direct (ngirim) ke bagian read
    dummyController.create(dummyData);
    response.sendRedirect("/read");
    return "created"; //artinya berhasil
  }
// untuk mengambil data gambar yang telah dimasukan ke data
  @RequestMapping(value = "/img", method = RequestMethod.GET, produces = {
      MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE
  })
  // public respon untuk respon data yang diambil dari primary key
  public ResponseEntity<byte[]> getImg(@RequestParam("id") int id) throws Exception {
    Dummy d = dummyController.findDummy(id);
    byte[] img = d.getGambar();
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
  }
//function dari hapus data
  @GetMapping("/delete/{id}")
  @ResponseBody
  public String deleteDummy(@PathVariable("id") int id, HttpServletResponse response) throws Exception {
    dummyController.destroy(id);
    response.sendRedirect("/read");
    return "deleted"; //berhasil menghapus
  }
//mengalihkan halaman ke bagian edit agar bisa mengedit/update data
  @RequestMapping("/edit/{id}")
  public String updateDummy(@PathVariable("id") int id, Model m) throws Exception { //data yang diambil idnya
    Dummy dummy = dummyController.findDummy(id);
    m.addAttribute("data", dummy);
    return "dummy/update";
  }
//mengirimkan data yang diedit ke database
  @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  public String updateDummyData(@RequestParam("gambar") MultipartFile multiFile, HttpServletRequest request, HttpServletResponse response)
      throws ParseException, Exception {
    Dummy dummyData = new Dummy();
// sama kayak yang parse diatas bagian sini parsing parameter (dari yang id itutuh di parse nanti masuk ke db nanti jadi int)
    int id = Integer.parseInt(request.getParameter("id"));
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("tanggal"));
    byte[] img = multiFile.getBytes();
    dummyData.setId(id); //lalu disini untuk sett convert mengatur format nya dari id ke id dsbg
    dummyData.setTanggal(date);
    dummyData.setGambar(img); //long_blob
//mengalihkan ke halaman read data
    dummyController.edit(dummyData);
    response.sendRedirect("/read");
    return "updated";
  }
}
//