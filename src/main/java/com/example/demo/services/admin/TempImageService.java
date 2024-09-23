package com.example.demo.services.admin;

import com.example.demo.models.TempImage;
import com.example.demo.repositories.admin.TempImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempImageService {

    @Autowired
    private final TempImageRepository tempImageRepository;

    public TempImageService(TempImageRepository tempImageRepository) {
        this.tempImageRepository = tempImageRepository;
    }

    public TempImage save(TempImage tempImage) {
        return tempImageRepository.save(tempImage);
    }
}
