# MVC Rest API with Spring Boot and MongoDB
This is a RESTful API that provides endpoints to perform CRUD operations on the Testimonial resource. The API is built with Spring Boot and MongoDB, and follows the MVC (Model-View-Controller) architecture pattern.

## API Endpoints
The following are the available endpoints of the API:

## GET /rest/v1/testimonial/all
This endpoint retrieves all the testimonials in the database.

## GET /rest/v1/testimonial/approved/threeRandom
This endpoint retrieves three random approved testimonials from the database.

## GET /rest/v1/testimonial/{uid}
This endpoint retrieves a testimonial by its unique identifier (uid).

## PUT /rest/v1/testimonial/create
This endpoint creates a new testimonial with the data provided in the request body. The UID field should not be included in the request body.

## POST /rest/v1/testimonial/update/{uid}
This endpoint updates an existing testimonial with the data provided in the request body.

---

## Code Overview
The API is implemented in Java, and uses Spring Boot as the framework for building web applications. MongoDB is used as the database to store the testimonials.

The TestimonialController class is the main controller that handles the RESTful endpoints. The class is annotated with @RestController and @RequestMapping("/rest/v1/testimonial") to define the base URL for the API.



TestimonialDAO: This is a data access object that provides methods to interact with the MongoDB database.
MongoOperations: This is a higher-level interface that provides advanced MongoDB operations.
Endpoint Implementation
The API endpoints are implemented as methods in the TestimonialController class. Each method is annotated with the corresponding HTTP method and URL endpoint.

The getAllTestimonials() method retrieves all the testimonials in the database and returns them as a list wrapped in a ResponseEntity object.

The getThreeRandomTestimonials() method retrieves three random approved testimonials from the database and returns them as a list wrapped in a ResponseEntity object.

The getTestimonialById() method retrieves a testimonial by its UID and returns it wrapped in a ResponseEntity object.

The createTestimonial() method creates a new testimonial with the data provided in the request body. The UID field is generated automatically and not included in the request body. The method then calls createOrUpdateTestimonial() to create the testimonial in the database.

The createOrUpdateTestimonial() method updates an existing testimonial or creates a new one, depending on whether the UID already exists in the database. The method returns a ResponseEntity object with a message and the saved entity.

# Conclusion
In conclusion, this is an MVC REST API for managing testimonials. It includes CRUD operations and endpoints for retrieving all testimonials, retrieving a testimonial by ID, retrieving three random approved testimonials, creating a testimonial, and updating a testimonial. The API is built with Spring Boot, MongoDB, and uses the MVC architecture.

As of 3/27/2023, this API only has implementation for testimonials, but future features will include user tracking and contact message submission.
