package uz.librarysystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String author;

    private String writtenYear;

    private Integer amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    public Book() {
    }

    public Book(Long id, String name, String description, String author, String writtenYear, Integer amount, Attachment attachment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.writtenYear = writtenYear;
        this.amount = amount;
        this.attachment = attachment;
    }

    public Book(String name, String description, String author, String writtenYear, Integer amount, Attachment attachment) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.writtenYear = writtenYear;
        this.amount = amount;
        this.attachment = attachment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWrittenYear() {
        return writtenYear;
    }

    public void setWrittenYear(String writtenYear) {
        this.writtenYear = writtenYear;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
