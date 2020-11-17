package uz.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.librarysystem.entity.User;
import uz.librarysystem.security.CurrentUser;
import uz.librarysystem.service.RentService;

@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping
    public ResponseEntity<?> getAllRents() {
        return ResponseEntity.ok(rentService.getAll());
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<?> rentBook(@CurrentUser User user, @PathVariable Long bookId) {
        return ResponseEntity.ok(rentService.rentBook(user, bookId));
    }

    @GetMapping("/myBooks")
    public ResponseEntity<?> getOwnRentBooks(@CurrentUser User user) {
        return ResponseEntity.ok(rentService.getOwnBooks(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRentBook(@PathVariable Long id, @CurrentUser User user) {
        return ResponseEntity.ok(rentService.delete(id, user));
    }


}
