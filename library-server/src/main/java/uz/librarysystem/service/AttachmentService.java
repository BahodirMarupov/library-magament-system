package uz.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.librarysystem.entity.Attachment;
import uz.librarysystem.repository.AttachmentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Object uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        List<Long> idList = new ArrayList<>();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            assert file != null;
            Attachment attachment = new Attachment(
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes()
            );
            Attachment saved = attachmentRepository.save(attachment);
            idList.add(saved.getId());
        }
        return idList;
    }

    public ResponseEntity<?> download(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;\"" + attachment.getName() + "\"")
                    .body(attachment.getBytes());
        } else {
            return null;
        }
    }

    public ResponseEntity<?> preview(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;\"" + attachment.getName() + "\"")
                    .body(attachment.getBytes());
        } else {
            return null;
        }
    }
}
