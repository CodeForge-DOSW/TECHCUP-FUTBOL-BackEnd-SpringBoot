# Step 22: Image Microservice Testing Guide

## Overview
This document provides comprehensive testing instructions for the Image microservice REST API implemented in Step 21.

## Prerequisites

### Setup Local Services (Docker)

Ensure Docker and Docker Compose are installed, then run:

```bash
docker-compose up -d
```

This will start:
- **PostgreSQL** on `localhost:5432` (postgres/postgres)
- **MongoDB** on `localhost:27017` (tech_cup_images database)

Wait for both services to be healthy (10-15 seconds).

### Run the Application

```bash
# Option 1: Using Maven
mvn -Dspring.profiles.active=dev spring-boot:run

# Option 2: Using JAR
java -Dspring.profiles.active=dev -jar target/tech_cup-0.0.1-SNAPSHOT.jar
```

Application will start on `http://localhost:8080`

## 5 Minimum Test Cases

### Test Case 1: Upload Image (POST /api/images)

**Purpose**: Verify that the application can accept and store an image file with metadata.

**Request Details**:
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/images`
- **Headers**:
  - `Content-Type: multipart/form-data`
- **Body Parameters**:
  - `file` (form-data, type: File) – Select any image file (JPG, PNG, etc.)
  - `externalReference` (form-data, type: Text) – `tournament-001`
  - `referenceType` (form-data, type: Text) – `TOURNAMENT`

**Expected Response**:
- **Status Code**: `201 Created`
- **Response Body**:
  ```json
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

**How to Test in Postman**:
1. Create a new **POST** request
2. Set URL to `http://localhost:8080/api/images`
3. Go to **Body** tab, select **form-data**
4. Add:
   - Key: `file`, Type: **File**, Value: (select an image)
   - Key: `externalReference`, Type: **Text**, Value: `tournament-001`
   - Key: `referenceType`, Type: **Text**, Value: `TOURNAMENT`
5. Click **Send**
6. Save the returned `id` for use in subsequent tests

---

### Test Case 2: List All Images (GET /api/images)

**Purpose**: Verify that the application can retrieve a list of all uploaded images.

**Request Details**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/images`
- **Headers**: None required

**Expected Response**:
- **Status Code**: `200 OK` (or `204 No Content` if no images exist)
- **Response Body** (200 OK):
  ```json
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
  ```

**How to Test in Postman**:
1. Create a new **GET** request
2. Set URL to `http://localhost:8080/api/images`
3. Click **Send**
4. Verify you receive either a 200 OK with a list or 204 No Content

---

### Test Case 3: Retrieve Image by ID (GET /api/images/{id})

**Purpose**: Verify that the application can retrieve and return the binary image data correctly.

**Request Details**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/images/{id}`
  - Replace `{id}` with the ID obtained from Test Case 1
- **Headers**: None required (Content-Type is set automatically)

**Expected Response**:
- **Status Code**: `200 OK`
- **Headers**:
  - `Content-Type`: `image/jpeg` (or appropriate MIME type)
  - `Content-Disposition`: `inline; filename="image.jpg"`
- **Body**: Binary image data (viewable in browser or image viewer)

**How to Test in Postman**:
1. Create a new **GET** request
2. Set URL to `http://localhost:8080/api/images/507f1f77bcf86cd799439011` (use the ID from Test Case 1)
3. Go to **Response** section (if you expect binary data, switch to **Preview** tab)
4. Click **Send**
5. Image should display or be downloadable

**Browser Alternative**:
- Simply navigate to `http://localhost:8080/api/images/507f1f77bcf86cd799439011` in your browser
- The image should display inline if browseable format (JPEG, PNG, GIF)

---

### Test Case 4: Delete Image (DELETE /api/images/{id})

**Purpose**: Verify that the application can remove images from storage.

**Request Details**:
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/api/images/{id}`
  - Replace `{id}` with the ID obtained from Test Case 1
- **Headers**: None required

**Expected Response**:
- **Status Code**: `204 No Content` (if deletion succeeded)
- **Body**: Empty

**How to Test in Postman**:
1. Create a new **DELETE** request
2. Set URL to `http://localhost:8080/api/images/507f1f77bcf86cd799439011` (use the ID from Test Case 1)
3. Click **Send**
4. Expect 204 No Content
5. To verify deletion, call Test Case 1 (GET /api/images) again to confirm the image is gone

---

### Test Case 5: List Images by External Reference (GET /api/images/reference/{externalReference})

**Purpose**: Verify that the application can filter and retrieve images by their external reference (e.g., tournament ID, team ID).

**Request Details**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/images/reference/{externalReference}`
  - Replace `{externalReference}` with the reference value (e.g., `tournament-001`)
- **Headers**: None required

**Expected Response**:
- **Status Code**: `200 OK` (or `204 No Content` if no images match)
- **Response Body** (200 OK):
  ```json
  [
    {
      "id": "507f1f77bcf86cd799439012",
      "fileName": "team_logo.png",
      "contentType": "image/png",
      "size": 128956,
      "uploadedAt": "2026-03-25T13:20:15.654321",
      "externalReference": "tournament-001",
      "referenceType": "TOURNAMENT"
    }
  ]
  ```

**How to Test in Postman**:
1. First, upload another image (Test Case 1) with the SAME `externalReference` (e.g., `tournament-001`)
2. Create a new **GET** request
3. Set URL to `http://localhost:8080/api/images/reference/tournament-001`
4. Click **Send**
5. You should receive a list of all images with that external reference

---

## Additional Validation Scenarios

### Scenario A: Upload with Different Reference Types

Upload images with different reference types to verify filtering:

```bash
# Image 1 - Tournament
POST http://localhost:8080/api/images
file: image1.jpg
externalReference: tournament-001
referenceType: TOURNAMENT

# Image 2 - Team
POST http://localhost:8080/api/images
file: image2.jpg
externalReference: team-042
referenceType: TEAM

# Image 3 - Player
POST http://localhost:8080/api/images
file: image3.jpg
externalReference: player-777
referenceType: PLAYER
```

Then verify:
- `GET /api/images` returns all 3
- `GET /api/images/reference/tournament-001` returns only Image 1
- `GET /api/images/reference/team-042` returns only Image 2

### Scenario B: Error Handling

Test error cases:

1. **Upload without file**:
   - POST to `/api/images` without `file` parameter
   - Expected: `400 Bad Request`

2. **Upload with missing externalReference**:
   - POST to `/api/images` with `file` and `referenceType` but no `externalReference`
   - Expected: `400 Bad Request`

3. **Retrieve non-existent image**:
   - GET `/api/images/invalid-mongo-id`
   - Expected: `404 Not Found`

4. **Delete non-existent image**:
   - DELETE `/api/images/invalid-mongo-id`
   - Expected: `404 Not Found`

---

## Swagger Documentation

If Swagger/OpenAPI is enabled, documentation is available at:

```
http://localhost:8080/swagger-ui.html
```

All Image endpoints are documented under the **"Imágenes"** tag.

---

## MongoDB Verification

To verify data in MongoDB directly:

```bash
# Connect to MongoDB
mongosh mongodb://localhost:27017/tech_cup_images

# List all images
db.images.find()

# Find images by external reference
db.images.find({ externalReference: "tournament-001" })

# Count documents
db.images.countDocuments()

# Delete all images (for cleanup)
db.images.deleteMany({})
```

---

## Cleanup

**Stop Services**:
```bash
docker-compose down
```

**Remove Data Volumes**:
```bash
docker-compose down -v
```

---

## Expected Outcomes

✅ **All 5 test cases pass**  
✅ **Images are correctly stored in MongoDB**  
✅ **Binary data is retrieved with correct Content-Type**  
✅ **Content-Disposition header enables inline viewing**  
✅ **External reference filtering works correctly**  
✅ **Deletion removes images from database**  
✅ **Error cases return appropriate HTTP status codes**  

---

## Troubleshooting

### MongoDB Connection Refused
- Ensure `docker-compose up -d` completed successfully
- Check MongoDB is running: `docker ps | grep mongodb`
- Verify port 27017 is available

### PostgreSQL Connection Issues
- These can be ignored if testing only image endpoints
- If needed, ensure PostgreSQL is running or use `-Dspring.profiles.active=dev` to suppress startup errors

### Image Upload Failed with 400 Bad Request
- Verify all three form parameters are provided: `file`, `externalReference`, `referenceType`
- Ensure `Content-Type` in Postman is set to `multipart/form-data`

### Cannot View Retrieved Image
- Verify the image format is web-compatible (JPEG, PNG, GIF, WebP)
- In Postman, use the **Preview** tab for binary responses
- Browser testing works better for inline viewing

---

## Next Steps

After verification:
1. Commit Postman collection or test results
2. Update main README with image service documentation
3. Prepare for merge to develop branch
