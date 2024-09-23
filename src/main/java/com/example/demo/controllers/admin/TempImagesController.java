package com.example.demo.controllers.admin;

import com.example.demo.models.Category;
import com.example.demo.models.TempImage;
import com.example.demo.services.admin.TempImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TempImagesController {

    private final TempImageService tempImageService;

    private String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf('.') + 1);
        }
        return "";
    }

    // Define the upload directory relative to the current working directory

    public TempImagesController(TempImageService tempImageService) {
        this.tempImageService = tempImageService;
    }

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/admin/temp";

    @PostMapping("/admin/upload-temp-image/create")
    public ResponseEntity<Map<String, Object>> uploadTempImage(@RequestParam("image") MultipartFile image,
                                                               @ModelAttribute("tempImage") TempImage tempImage
    ) {
        Map<String, Object> response = new HashMap<>();
        if (!image.isEmpty()) {
            try {
                // Dynamically resolve the upload directory relative to the project root
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY).toAbsolutePath();
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath); // Create directory if it does not exist
                }

                // Get the original file name and create the target file
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String formattedNow = formatter.format(now);

                String originalFilename = image.getOriginalFilename();
                // Extract the file extension
                String extension = getFileExtension(originalFilename);
                //name
                String fileName = formattedNow + "." + extension;
                File targetFile = new File(uploadPath.toFile(), fileName);

                // Save the image to the target file
                image.transferTo(targetFile);

                tempImage.setName(fileName);
                tempImageService.save(tempImage);

                // Return the file name for further use
                response.put("status", true);
                response.put("image_id", tempImage.getId());
                return ResponseEntity.ok(response);
            } catch (IOException e) {
                e.printStackTrace();
                // Return an error response
                response.put("kaing", "kieang hz bro");
                 e.printStackTrace();
                response.put("status", false);
                response.put("message", "Image upload failed.");
                return ResponseEntity.status(500).body(response);
            }
        } else {
            response.put("status", false);
            response.put("message", "No image uploaded.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
