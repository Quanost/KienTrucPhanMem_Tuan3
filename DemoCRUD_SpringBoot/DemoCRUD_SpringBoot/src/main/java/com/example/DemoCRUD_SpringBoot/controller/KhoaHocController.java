package com.example.DemoCRUD_SpringBoot.controller;


import com.example.DemoCRUD_SpringBoot.model.KhoaHoc;
import com.example.DemoCRUD_SpringBoot.model.ReponseObject;
import com.example.DemoCRUD_SpringBoot.model.User;
import com.example.DemoCRUD_SpringBoot.repository.IImageService;
import com.example.DemoCRUD_SpringBoot.repository.KhoaHocRepository;
import com.example.DemoCRUD_SpringBoot.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/khoahoc")
public class KhoaHocController {
    @Autowired
    private KhoaHocRepository khoaHocRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    IImageService imageService;





//Insert khoá học có upload image
    @PostMapping(value = "/upload/{userId}",consumes = {MediaType.APPLICATION_JSON_VALUE,
                                                MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity create(@RequestPart("khoahoc") String user,@RequestPart("file") MultipartFile[] files,@PathVariable(value = "userId") Integer userId
                                 ) throws Exception {
        KhoaHoc khoaHoc =new KhoaHoc();
        try{
            //Convert Sring to Object KhoaHoc
            ObjectMapper objectMapper=new ObjectMapper();
            khoaHoc=objectMapper.readValue(user,KhoaHoc.class);

            User userFind = userRepository.findById(userId).orElseThrow(() -> new Exception("Không tìm thấy User "));
            khoaHoc.setUser(userFind);
            List<KhoaHoc> foundKhoaHoc = khoaHocRepository.findByName(khoaHoc.getName().trim());
            if(foundKhoaHoc.size() > 0) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ReponseObject("failed", "Tên Khoá học đã tồn tại", "")
                );
            }
            for (MultipartFile file : files) {
                try {
                    String fileName = imageService.save(file);
                    String imageUrl = imageService.getImageUrl(fileName);
                    khoaHoc.setImage(imageUrl);

                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ReponseObject("ok", "Insert KhoaHoc successfully", khoaHocRepository.save(khoaHoc))
                    );

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }catch (IOException e){
            System.out.println("Errror"+e);
        }
        return new ResponseEntity<>("Insert KhoaHoc That bai", HttpStatus.OK);
    }


    //Insert khoa hoc không có upload image
//    @PostMapping("/khoahocs/{userId}")
//    public ResponseEntity<ReponseObject> addKhoaHoc(@PathVariable(value = "userId") Integer userId,
//
//                                                    @RequestBody KhoaHoc khoaHoc) throws Exception {
//
//        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Không tìm thấy User "));
//        khoaHoc.setUser(user);
//        List<KhoaHoc> foundKhoaHoc = khoaHocRepository.findByName(khoaHoc.getName().trim());
//        if(foundKhoaHoc.size() > 0) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ReponseObject("failed", "Tên Khoá học đã tồn tại", "")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ReponseObject("ok", "Insert KhoaHoc successfully", khoaHocRepository.save(khoaHoc))
//        );
//    }

    @GetMapping("/khoahocs")
    @Cacheable(value = "cache1", key = "'#key'")
    public List<KhoaHoc> getAllKhoaHoc() {

        return khoaHocRepository.findAll();
    }


    @GetMapping("khoahocs/{khoahocId}")
    @Cacheable(value = "cache1",key = "#khoahocId")
    public KhoaHoc findEmployeeById(@PathVariable(value = "khoahocId") Integer khoahocId) throws Exception {

        return khoaHocRepository.findById(khoahocId).orElseThrow();

    }


    @PutMapping("khoahocs/{khoahocId}")
//    @CachePut(value = "cache1",key = "#khoahocId")
    public KhoaHoc updateKhoaHoc(
                                 @RequestParam(name = "userId") Integer userId,
                                 @PathVariable(value = "khoahocId") Integer khoaHocID,
                                 @RequestBody KhoaHoc khoaHocDetails) throws Exception {
        KhoaHoc khoaHoc = khoaHocRepository.findById(khoaHocID).
            orElseThrow(() -> new Exception("Student not found - "));
        User user = userRepository.findById(userId).
            orElseThrow(() -> new Exception("Student not found - "));
        khoaHoc.setName(khoaHocDetails.getName());
        khoaHoc.setDescription(khoaHocDetails.getDescription());
        khoaHoc.setOldPrice(khoaHocDetails.getOldPrice());
        khoaHoc.setPrice(khoaHocDetails.getOldPrice());
        khoaHoc.setImage(khoaHocDetails.getImage());
        khoaHoc.setUser(user);
        final KhoaHoc updatedKhoaHoc = khoaHocRepository.save(khoaHoc);
        return updatedKhoaHoc;

    }


    @DeleteMapping("khoahocs/{id}")
    @CacheEvict(value = "cache1", allEntries = true)
    public ResponseEntity<ReponseObject> deleteKhoaHoc(@PathVariable(value = "id") Integer khoahocId) throws Exception {
        boolean exists = khoaHocRepository.existsById(khoahocId);
        if(exists) {
            khoaHocRepository.deleteById(khoahocId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ReponseObject("ok", "Delete KhoaHoc successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ReponseObject("failed", "Cannot find KhoaHoc to delete", "")
        );
    }

}
