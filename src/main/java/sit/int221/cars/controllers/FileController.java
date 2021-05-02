package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sit.int221.cars.models.FileInfo;
import sit.int221.cars.services.StorageService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/img")
public class FileController {
    final StorageService storageService;
    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            storageService.store(file);
            return ResponseEntity.status(HttpStatus.OK).body("Upload successful: "+file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Upload failed: "+e.getMessage());
        }
    }

    @GetMapping("/file")
    public List<FileInfo> getListFile() throws IOException {
        return storageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",path.getFileName().toString()).build().toString();
            return new FileInfo(fileName,url);
        }).collect(Collectors.toList());
    }

    @GetMapping(value = "/{filename:.+}",produces = MediaType.IMAGE_PNG_VALUE)
    public Resource getFile(@PathVariable String filename) throws IOException, URISyntaxException {
        return storageService.loadAsResource(filename);
    }
}
