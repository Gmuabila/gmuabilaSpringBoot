package com.gtechsb.gtechfirstspringboot.dto;

public class AddBookAuthorDto {
    private Long bookId;
    private Long authorId;

    public AddBookAuthorDto() {
    }

    public AddBookAuthorDto(Long bookId, Long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
