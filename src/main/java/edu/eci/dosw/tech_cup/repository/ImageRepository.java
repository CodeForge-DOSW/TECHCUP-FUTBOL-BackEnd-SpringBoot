package edu.eci.dosw.tech_cup.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.dosw.tech_cup.entity.ImageEntity;

/**
 * MongoDB repository for Image documents.
 *
 * <p>Provides CRUD operations and custom queries for image storage and retrieval.</p>
 */
@Repository
public interface ImageRepository extends MongoRepository<ImageEntity, String> {

    /**
     * Finds all images associated with a specific external reference.
     *
     * @param externalReference the external reference identifier
     * @return list of images matching the reference, empty if none found
     */
    List<ImageEntity> findByExternalReference(String externalReference);

    /**
     * Finds all images by both external reference and reference type.
     *
     * @param externalReference the external reference identifier
     * @param referenceType the type of reference (e.g., TOURNAMENT, TEAM)
     * @return list of images matching both criteria, empty if none found
     */
    List<ImageEntity> findByExternalReferenceAndReferenceType(String externalReference, String referenceType);
}
