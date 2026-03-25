package edu.eci.dosw.tech_cup.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.dosw.tech_cup.entity.ImageEntity;
import edu.eci.dosw.tech_cup.repository.ImageRepository;

/**
 * Implementation of image management business logic.
 *
 * <p>Handles file uploads, storage, retrieval, and deletion of images in MongoDB.</p>
 */
@Service
public class ImageService implements IImageService {

    /**
     * MongoDB repository for image documents.
     */
    private final ImageRepository imageRepository;

    /**
     * Builds the service with its repository dependency.
     *
     * @param imageRepository injected image repository
     */
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageEntity uploadImage(MultipartFile file, String externalReference, String referenceType) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }
        if (externalReference == null || externalReference.isBlank()) {
            throw new IllegalArgumentException("External reference cannot be null or blank");
        }
        if (referenceType == null || referenceType.isBlank()) {
            throw new IllegalArgumentException("Reference type cannot be null or blank");
        }

        ImageEntity image = new ImageEntity();
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setData(file.getBytes());
        image.setUploadedAt(LocalDateTime.now());
        image.setExternalReference(externalReference);
        image.setReferenceType(referenceType);

        return imageRepository.save(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ImageEntity> getImageById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return imageRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageEntity> getAllImages() {
        return imageRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageEntity> getImagesByExternalReference(String externalReference) {
        if (externalReference == null || externalReference.isBlank()) {
            return List.of();
        }
        return imageRepository.findByExternalReference(externalReference);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageEntity> getImagesByExternalReferenceAndType(String externalReference, String referenceType) {
        if (externalReference == null || externalReference.isBlank() ||
            referenceType == null || referenceType.isBlank()) {
            return List.of();
        }
        return imageRepository.findByExternalReferenceAndReferenceType(externalReference, referenceType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteImage(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
