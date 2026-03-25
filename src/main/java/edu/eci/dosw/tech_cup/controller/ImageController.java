package edu.eci.dosw.tech_cup.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.dosw.tech_cup.entity.ImageEntity;
import edu.eci.dosw.tech_cup.service.IImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller that exposes CRUD operations for image management.
 *
 * <p>All business rules are delegated to {@link IImageService}. This class is
 * responsible for HTTP mapping, multipart file handling, and response status management.</p>
 */
@RestController
@RequestMapping("/api/images")
@Tag(name = "Imágenes", description = "Operaciones relacionadas con imágenes")
public class ImageController {

    /**
     * Service that executes image-related use cases.
     */
    private final IImageService imageService;

    /**
     * Builds the controller with its service dependency.
     *
     * @param imageService injected image service implementation
     */
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Uploads a new image file.
     *
     * @param file the image file to upload (multipart/form-data)
     * @param externalReference the external reference identifier (e.g., tournament or team ID)
     * @param referenceType the type of reference (e.g., TOURNAMENT, TEAM, PLAYER)
     * @return 201 with the created ImageEntity; 400 with validation error
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir una nueva imagen", description = "Carga una imagen en el servidor y la asocia a una referencia externa")
    @ApiResponse(responseCode = "201", description = "Imagen subida exitosamente")
    @ApiResponse(responseCode = "400", description = "Error: archivo vacío o parámetros inválidos")
    public ResponseEntity<ImageEntity> uploadImage(
            @RequestPart("file")
            @Parameter(description = "Archivo de imagen a subir", required = true)
            MultipartFile file,
            @RequestParam("externalReference")
            @Parameter(description = "ID de referencia externa (ej: ID de torneo, equipo)", required = true)
            String externalReference,
            @RequestParam("referenceType")
            @Parameter(description = "Tipo de referencia (ej: TOURNAMENT, TEAM, PLAYER)", required = true)
            String referenceType) {
        try {
            ImageEntity savedImage = imageService.uploadImage(file, externalReference, referenceType);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Retrieves all images stored in the database.
     *
     * @return 200 with list of all images; 204 if no images exist
     */
    @GetMapping
    @Operation(summary = "Listar todas las imágenes", description = "Obtiene un listado de todas las imágenes almacenadas")
    @ApiResponse(responseCode = "200", description = "Lista de imágenes retornada")
    @ApiResponse(responseCode = "204", description = "No hay imágenes disponibles")
    public ResponseEntity<List<ImageEntity>> getAllImages() {
        List<ImageEntity> images = imageService.getAllImages();
        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    /**
     * Retrieves a specific image by its ID as binary data.
     *
     * @param id the image document ID
     * @return 200 with the image file in its original format; 404 if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una imagen por ID", description = "Descarga o visualiza una imagen específica por su identificador")
    @ApiResponse(responseCode = "200", description = "Imagen retornada como binary stream")
    @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    public ResponseEntity<byte[]> getImageById(
            @PathVariable("id")
            @Parameter(description = "ID único de la imagen en MongoDB", required = true)
            String id) {
        return imageService.getImageById(id)
                .map(image -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(image.getContentType()))
                        .header("Content-Disposition", "inline; filename=\"" + image.getFileName() + "\"")
                        .body(image.getData()))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all images associated with a specific external reference.
     *
     * @param externalReference the external reference identifier (e.g., tournament ID)
     * @return 200 with list of matching images; 204 if no images found for that reference
     */
    @GetMapping("/reference/{externalReference}")
    @Operation(summary = "Listar imágenes por referencia externa", description = "Obtiene todas las imágenes asociadas a una referencia específica (ej: torneo, equipo)")
    @ApiResponse(responseCode = "200", description = "Imágenes asociadas a la referencia retornadas")
    @ApiResponse(responseCode = "204", description = "No hay imágenes para esa referencia")
    public ResponseEntity<List<ImageEntity>> getImagesByReference(
            @PathVariable("externalReference")
            @Parameter(description = "ID de referencia externa", required = true)
            String externalReference) {
        List<ImageEntity> images = imageService.getImagesByExternalReference(externalReference);
        if (images.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(images);
    }

    /**
     * Deletes an image by its ID.
     *
     * @param id the image document ID
     * @return 204 no content if successfully deleted; 404 if image not found
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una imagen", description = "Elimina una imagen específica del servidor")
    @ApiResponse(responseCode = "204", description = "Imagen eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    public ResponseEntity<Void> deleteImage(
            @PathVariable("id")
            @Parameter(description = "ID único de la imagen a eliminar", required = true)
            String id) {
        boolean deleted = imageService.deleteImage(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
