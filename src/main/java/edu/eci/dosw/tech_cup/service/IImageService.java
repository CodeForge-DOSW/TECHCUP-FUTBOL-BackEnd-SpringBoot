package edu.eci.dosw.tech_cup.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import edu.eci.dosw.tech_cup.entity.ImageEntity;

/**
 * Business logic contract for image management.
 *
 * <p>Defines operations for uploading, retrieving, and deleting images stored in MongoDB.</p>
 */
public interface IImageService {

    /**
     * Uploads a new image file and stores it with metadata.
     *
     * @param file the multipart file to upload
     * @param externalReference the external reference identifier (e.g., tournament or team ID)
     * @param referenceType the type of reference (e.g., TOURNAMENT, TEAM, PLAYER)
     * @return the saved ImageEntity with assigned ID
     * @throws IOException if file reading fails
     * @throws IllegalArgumentException if file is null, empty, or reference data is invalid
     */
    ImageEntity uploadImage(MultipartFile file, String externalReference, String referenceType) throws IOException;

    /**
     * Retrieves a specific image by its ID.
     *
     * @param id the image document ID
     * @return Optional containing the image if found, empty otherwise
     */
    Optional<ImageEntity> getImageById(String id);

    /**
     * Retrieves all images in the database.
     *
     * @return list of all images, empty list if none exist
     */
    List<ImageEntity> getAllImages();

    /**
     * Retrieves all images associated with a specific external reference.
     *
     * @param externalReference the external reference identifier
     * @return list of images matching the reference, empty if none found
     */
    List<ImageEntity> getImagesByExternalReference(String externalReference);

    /**
     * Retrieves all images by both external reference and reference type.
     *
     * @param externalReference the external reference identifier
     * @param referenceType the type of reference (e.g., TOURNAMENT, TEAM)
     * @return list of images matching both criteria, empty if none found
     */
    List<ImageEntity> getImagesByExternalReferenceAndType(String externalReference, String referenceType);

    /**
     * Deletes an image by its ID.
     *
     * @param id the image document ID
     * @return true if image was deleted, false if not found
     */
    boolean deleteImage(String id);
}
