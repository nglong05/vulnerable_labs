package com.example.cc5lab;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UiController {

  private static final Path UPLOAD = Paths.get(System.getProperty("java.io.tmpdir"), "upload.ser");

  @GetMapping({"/"})
  public String index(Model model,
                      @RequestParam(value = "message", required = false) String message,
                      @RequestParam(value = "result", required = false) String result) {
    model.addAttribute("drinks", Drink.values());
    model.addAttribute("message", message);
    model.addAttribute("result", result);
    model.addAttribute("uploadPath", UPLOAD.toString());
    return "index";
  }

  // chọn đồ uống -> server serialize và trả về file .ser
  @PostMapping(value = "/serialize")
  public ResponseEntity<byte[]> serialize(@RequestParam("drinks") List<String> drinks) throws Exception {
    List<Drink> chosen = drinks.stream()
            .map(Drink::fromDisplayName) // this return enum name
            .collect(Collectors.toList());
    Order order = new Order(chosen);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try (ObjectOutputStream oos = new ObjectOutputStream(bos)) { oos.writeObject(order); }
    byte[] ser = bos.toByteArray();

    HttpHeaders h = new HttpHeaders();
    h.setContentDisposition(ContentDisposition.attachment().filename("order.ser").build());
    h.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    return new ResponseEntity<>(ser, h, HttpStatus.OK);
  }

  // upload file .ser
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam("file") MultipartFile file, Model model) throws Exception {
    Files.write(UPLOAD, file.getBytes());
    model.addAttribute("message", "Đã lưu: " + UPLOAD);
    model.addAttribute("drinks", Drink.values());
    return "index";
  }

  // deser file và hiển thị
  @PostMapping("/deserialize")
  public String deserialize(Model model) {
    model.addAttribute("drinks", Drink.values());
    try {
      byte[] data = Files.readAllBytes(UPLOAD);
      try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
        Object obj = ois.readObject(); // here
        if (obj instanceof Order) {
          Order o = (Order) obj;
          model.addAttribute("result", "Bạn đã chọn: " + o.getDrinks());
        } else {
          model.addAttribute("result", "Đã deserialize: " + obj.getClass().getName());
        }
      }
    } catch (Exception e) {
      model.addAttribute("result", "Lỗi: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
    return "index";
  }
}

