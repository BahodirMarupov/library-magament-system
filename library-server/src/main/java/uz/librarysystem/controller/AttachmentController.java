package uz.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.librarysystem.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/api/img")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

//    @Secured("ROLE_ADMIN")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        return ResponseEntity.ok(attachmentService.uploadFile(request));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        return attachmentService.download(id);
    }

    @GetMapping("/preview/{id}")
    public ResponseEntity<?> previewFile(@PathVariable Long id) {
        return attachmentService.preview(id);
    }
}
