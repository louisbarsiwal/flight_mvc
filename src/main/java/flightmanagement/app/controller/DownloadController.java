package flightmanagement.app.controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class DownloadController {

    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        String filePath ="src/main/resources/static/files/booking_confirmation.pdf"; // Update this with the actual path to your PDF file
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
