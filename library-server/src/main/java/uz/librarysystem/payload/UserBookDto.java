package uz.librarysystem.payload;

import uz.librarysystem.entity.User;

import java.sql.Timestamp;

public class UserBookDto {
    private Long id;
    private User user;
    private BookDto bookDto;
    private Timestamp createdAt;

    public UserBookDto() {
    }

    public UserBookDto(Long id, User user, BookDto bookDto,Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.bookDto = bookDto;
        this.createdAt=createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
