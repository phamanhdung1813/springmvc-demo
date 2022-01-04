package com.anhdungpham.api.admin;

import com.anhdungpham.dto.NewDTO;
import com.anhdungpham.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {

    @Autowired
    private INewService newService;

    @PostMapping("/api/new")
    public NewDTO createNew(@RequestBody NewDTO newDTO) {
//        return newDTO; // Postman
        return newService.insertNew(newDTO);
    }

    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO newDTO) {
        return newService.updateNew(newDTO);
    }

    @DeleteMapping("/api/new")
    public void deleteNew (@RequestBody long[] ids) {
        newService.delete(ids);
    }
}
