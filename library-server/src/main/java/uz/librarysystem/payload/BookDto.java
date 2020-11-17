package uz.librarysystem.payload;

import javax.validation.constraints.NotBlank;

public class BookDto {
    private Long id;
    @NotBlank(message = "Name should be filled!")
    private String name;
    @NotBlank(message = "Description should be filled!")
    private String description;
    @NotBlank(message = "author should be filled!")
    private String author;
    @NotBlank(message = "Written year should be filled!")
    private String writtenYear;
    private Integer amount;
    private Long attachmentId;

    public BookDto(Long id, @NotBlank(message = "Name should be filled!") String name, @NotBlank(message = "Description should be filled!") String description, @NotBlank(message = "author should be filled!") String author, @NotBlank(message = "Written year should be filled!") String writtenYear, Integer amount, Long attachmentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.writtenYear = writtenYear;
        this.amount = amount;
        this.attachmentId = attachmentId;
    }

    public BookDto() {
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

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
