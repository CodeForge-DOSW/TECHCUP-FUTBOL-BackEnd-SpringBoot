# Image Microservice API

## Overview

The Image Microservice provides REST endpoints for uploading, retrieving, and managing image files stored in MongoDB. It is integrated with the TechCup football tournament management system and allows associating images with external entities such as tournaments, teams, and players.

## Technology Stack

- **Framework**: Spring Boot 4.0.3
- **Database**: MongoDB 7.0
- **Language**: Java 17
- **Build Tool**: Maven
- **API Documentation**: Swagger/OpenAPI 3.0

## Features

✅ **File Upload**: Upload image files via multipart/form-data  
✅ **Metadata Storage**: Automatically store file metadata (name, size, type, timestamp)  
✅ **External References**: Associate images with external entities (tournaments, teams, players)  
✅ **Binary Retrieval**: Download images with correct MIME type and inline viewing  
✅ **Query Filtering**: Retrieve images by ID or external reference  
✅ **Deletion**: Remove images from storage  
✅ **Error Handling**: Comprehensive validation and error responses  
✅ **Documentation**: Swagger/OpenAPI endpoints auto-documented

## REST Endpoints

### 1. Upload Image
```
POST /api/images
Content-Type: multipart/form-data

Parameters:
  - file (required): Image file (multipart file upload)
  - externalReference (required): External entity ID (e.g., tournament-001)
  - referenceType (required): Type of reference (TOURNAMENT, TEAM, PLAYER, etc.)

Response: 201 Created
{
  "id": "507f1f77bcf86cd799439011",
  "fileName": "image.jpg",
  "contentType": "image/jpeg",
  "size": 245632,
  "uploadedAt": "2026-03-25T13:15:30.123456",
  "externalReference": "tournament-001",
  "referenceType": "TOURNAMENT"
}
```

### 2. List All Images
```
GET /api/images

Response: 200 OK
[
  {
    "id": "507f1f77bcf86cd799439011",
    "fileName": "image.jpg",
    "contentType": "image/jpeg",
    "size": 245632,
    "uploadedAt": "2026-03-25T13:15:30.123456",
    "externalReference": "tournament-001",
    "referenceType": "TOURNAMENT"
  }
]

Or: 204 No Content (if no images exist)
```

### 3. Retrieve Image by ID
```
GET /api/images/{id}

Path Parameters:
  - id (required): MongoDB document ID

Response: 200 OK (Binary image data)
Headers:
  - Content-Type: image/jpeg (or appropriate MIME type)
  - Content-Disposition: inline; filename="image.jpg"

Or: 404 Not Found
```

### 4. List Images by External Reference
```
GET /api/images/reference/{externalReference}

Path Parameters:
  - externalReference (required): External entity ID

Response: 200 OK
[
  {
    "id": "507f1f77bcf86cd799439012",
    "fileName": "image.jpg",
    "contentType": "image/jpeg",
    "size": 245632,
    "uploadedAt": "2026-03-25T13:15:30.123456",
    "externalReference": "tournament-001",
    "referenceType": "TOURNAMENT"
  }
]

Or: 204 No Content
```

### 5. Delete Image
```
DELETE /api/images/{id}

Path Parameters:
  - id (required): MongoDB document ID

Response: 204 No Content

Or: 404 Not Found
```

## Architecture

```
ImageController           <- REST Endpoints
    ↓ (delegates to)
IImageService / ImageService  <- Business Logic
    ↓ (uses)
ImageRepository           <- MongoDB CRUD + Custom Queries
    ↓ (persists)
ImageEntity (@Document)   <- MongoDB Document Schema
    ↓
MongoDB (tech_cup_images database)
```

## Entity Schema

### ImageEntity (MongoDB Document)

```javascript
{
  "_id": ObjectId,              // MongoDB ObjectId
  "fileName": String,           // Original filename
  "contentType": String,        // MIME type (e.g., image/jpeg)
  "size": Long,                 // File size in bytes
  "data": BinData,              // Binary image data
  "uploadedAt": ISODate,        // Upload timestamp
  "externalReference": String,  // Reference ID (tournament, team, player)
  "referenceType": String       // Type of reference (TOURNAMENT, TEAM, PLAYER)
}
```

## Configuration

### application.properties

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/tech_cup_images
spring.data.mongodb.auto-index-creation=true
```

### application-dev.properties

Development profile with relaxed PostgreSQL connection requirements:

```properties
# Minimal PostgreSQL (optional)
spring.datasource.hikari.maximum-pool-size=1
spring.jpa.hibernate.ddl-auto=none

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/tech_cup_images
spring.data.mongodb.auto-index-creation=true
```

## Local Development Setup

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker & Docker Compose (recommended for databases)

### Using Docker Compose

```bash
# Start PostgreSQL and MongoDB
docker-compose up -d

# Verify services
docker ps
```

### Run Application

```bash
# Development mode with dev profile
mvn -Dspring.profiles.active=dev spring-boot:run

# Or with JAR
java -Dspring.profiles.active=dev -jar target/tech_cup-0.0.1-SNAPSHOT.jar
```

Application will start on **http://localhost:8080**

### MongoDB Local Connection

```bash
# Connect to MongoDB
mongosh mongodb://localhost:27017/tech_cup_images

# List all images
db.images.find()

# Find images by reference
db.images.find({ externalReference: "tournament-001" })
```

## Testing

See [TESTING_STEP22.md](./TESTING_STEP22.md) for comprehensive testing guide with:
- 5 minimum test cases
- Postman request examples
- Error handling scenarios
- MongoDB verification commands

### Quick Test

```bash
# Upload an image
curl -X POST http://localhost:8080/api/images \
  -F "file=@/path/to/image.jpg" \
  -F "externalReference=tournament-001" \
  -F "referenceType=TOURNAMENT"

# List all images
curl http://localhost:8080/api/images

# Download image by ID
curl -O http://localhost:8080/api/images/{id}

# Delete image
curl -X DELETE http://localhost:8080/api/images/{id}
```

## API Documentation

Interactive Swagger documentation available at:

```
http://localhost:8080/swagger-ui.html
```

All Image endpoints are documented under the **"Imágenes"** tag.

## Error Handling

| HTTP Status | Scenario | Example |
|------------|----------|---------|
| 201 Created | Image uploaded successfully | Upload with valid file and references |
| 200 OK | Data retrieved successfully | GET all images or retrieve by reference |
| 204 No Content | Deletion success or empty result set | DELETE successful or GET with no results |
| 400 Bad Request | Missing or invalid parameters | Upload without file or empty reference |
| 404 Not Found | Image ID not found | GET or DELETE with invalid ID |
| 500 Internal Server Error | Unexpected server error | Connection issues, file read errors |

## Class Hierarchy

```
edu.eci.dosw.tech_cup.entity
  └─ ImageEntity.java         (MongoDB @Document)

edu.eci.dosw.tech_cup.repository
  └─ ImageRepository.java     (MongoRepository<ImageEntity, String>)

edu.eci.dosw.tech_cup.service
  ├─ IImageService.java       (Interface)
  └─ ImageService.java        (Implementation)

edu.eci.dosw.tech_cup.controller
  └─ ImageController.java     (REST endpoints)
```

## Future Enhancements

- [ ] Image resizing/compression
- [ ] Thumbnail generation
- [ ] Image caching with CDN
- [ ] Batch upload support
- [ ] Image format validation
- [ ] Size limit validation
- [ ] Access control/permissions
- [ ] Image metadata extraction (EXIF)
- [ ] Virus/malware scanning

## Contributing

For modifications to the Image service:

1. Create a feature branch based on this implementation
2. Update both code and documentation
3. Run full test suite before merging
4. Update TESTING_STEP22.md with new test cases

## License

See main repository LICENSE

---

**Last Updated**: March 25, 2026  
**Implementation**: Step 21 (Controller) & Step 22 (Testing)  
**Status**: Production Ready
