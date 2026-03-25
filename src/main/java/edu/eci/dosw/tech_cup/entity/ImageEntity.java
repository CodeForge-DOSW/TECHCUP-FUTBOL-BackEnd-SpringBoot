package edu.eci.dosw.tech_cup.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * MongoDB document for storing image files and their metadata.
 *
 * <p>This entity stores binary image data along with metadata such as
 * filename, content type, size, and a reference to an external entity.</p>
 */
@Document(collection = "images")
public class ImageEntity {

    /**
     * Unique identifier for the image document in MongoDB.
     */
    @Id
    private String id;

    /**
     * Original filename of the uploaded image.
     */
    @NotBlank
    private String fileName;

    /**
     * MIME type of the image (e.g., image/jpeg, image/png).
     */
    @NotBlank
    private String contentType;

    /**
     * Size of the image in bytes.
     */
    @NotNull
    private Long size;

    /**
     * Binary data of the image.
     */
    @NotNull
    private byte[] data;

    /**
     * Timestamp when the image was uploaded.
     */
    @NotNull
    private LocalDateTime uploadedAt;

    /**
     * External reference identifier (e.g., tournament ID, team ID).
     */
    @NotBlank
    private String externalReference;

    /**
     * Type of external reference (e.g., TOURNAMENT, TEAM, PLAYER).
     */
    @NotBlank
    private String referenceType;

    // Constructors
    public ImageEntity() {
    }

    public ImageEntity(String fileName, String contentType, Long size, byte[] data,
                       LocalDateTime uploadedAt, String externalReference, String referenceType) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.uploadedAt = uploadedAt;
        this.externalReference = externalReference;
        this.referenceType = referenceType;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }
}
