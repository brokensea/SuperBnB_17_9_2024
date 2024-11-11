SuperBnB Projekt

Ein Ferienwohnung-Buchungssystem, das Funktionen für die Benutzerverwaltung, Buchung von Ferienwohnungen und
Zugriffskontrolle über Rollen bietet.

Projektstruktur

Das Projekt besteht aus:

Backend: Spring Boot-basierter RESTful-Service.

Frontend: Für Benutzerinteraktionen und Anzeige von Daten (nicht im Detail dargestellt).

Datenbank: Verwendet PostgreSQL und ist über Docker-Container verbunden.

Authentifizierung und Zugriffskontrolle

Dieses Projekt verwendet Spring Security für die Authentifizierung und Zugriffskontrolle basierend auf Benutzerrollen (
USER, ADMIN).

Sicherheitskonfiguration

JWT-basierte Authentifizierung mit RSA-Schlüsseln für Signatur und Validierung.

CORS-Konfiguration erlaubt spezifische Anfragen und Header.

Zugriffsbeschränkungen basierend auf den Benutzerrollen und Methodenautorität (@PreAuthorize).

Endpunkte für Authentifizierung

Anmeldung (/api/v1/auth/signin) - Authentifiziert Benutzer und stellt JWT bereit.

Registrierung (/api/v1/auth/signup) - Erstellt einen neuen Benutzer.

Abmeldung (/api/v1/auth/logout) - Invalide Session beenden.

Benutzerverwaltung

Nur Administratoren (ADMIN) dürfen folgende Benutzeraktionen ausführen:

Alle Benutzer auflisten (GET /api/v1/users)

Benutzer löschen (DELETE /api/v1/users/{id})

Buchungsmanagement

Buchungsaktionen umfassen:

Alle Buchungen anzeigen (GET /api/v1/bookings) - Nur für Administratoren.

Neue Buchung erstellen (POST /api/v1/bookings)

Buchung stornieren (DELETE /api/v1/bookings/{id})

Ferienwohnungenverwaltung

Verwaltung von Ferienwohnungen mit folgenden Aktionen:

Alle Ferienwohnungen anzeigen (GET /api/v1/properties)

Neue Ferienwohnung hinzufügen (POST /api/v1/properties) - Nur Administratoren.

Ferienwohnung aktualisieren (PUT /api/v1/properties/{id}) - Nur Administratoren.

Ferienwohnung löschen (DELETE /api/v1/properties/{id}) - Nur Administratoren.

Sicherheitseinstellungen

JWT wird mit RSA-Schlüsseln signiert.

Benutzerrollen und Berechtigungen werden mittels @PreAuthorize-Annotation verwaltet.

Passwortsicherheit durch BCrypt-Kodierung.

Englisch:
SuperBnB Backend README
Project Overview
SuperBnB Backend is a Spring Boot application designed to handle user authentication, property management, and booking
functionalities for a vacation rental platform, similar to Airbnb. The backend includes role-based access control,
JWT-based authentication, and secure password encryption.

Technologies Used

Java (Java 22)

Spring Boot (version 3+)

Spring Security

Spring Data JPA

JWT for token-based authentication

PostgreSQL (or compatible SQL database)

Docker (optional, for containerized deployment)

Maven (for dependency management)

Project Structure

config: Configuration files, including security, CORS, and RSA key properties.

controller: REST controllers handling HTTP requests for authentication, users, properties, and bookings.

entities: Entity classes representing database models.

repositories: JPA repositories for database access.

services: Business logic for authentication, user, property, and booking management.

dtos: Data Transfer Objects for requests and responses.

Key Components

Authentication and Authorization

UserDetailsService: Custom service to load users by email from BenutzerRepository.

BCryptPasswordEncoder: Used for secure password encoding.

JWT Authentication: JWT tokens are generated and validated using RSA keys. JWT tokens manage user sessions in a
stateless manner.

Role-Based Access Control: ADMIN and USER roles control access to specific API endpoints.

Security Configuration

Security is configured in SecurityConfiguration.java:

CORS and CSRF policies are set to allow cross-origin requests and disable CSRF.

JWT tokens are used as bearer tokens in OAuth2 resource server configuration.

Specific endpoints are accessible based on user roles (e.g., only ADMIN can access certain APIs).

Endpoints

Authentication (/api/v1/auth)

POST /signup: Register a new user.

POST /signin: Authenticate and generate a JWT token.

GET /logout: Invalidate the session.

User Management (/api/v1/users)

GET /: List all users (ADMIN only).

DELETE /{id}: Delete a user by ID (ADMIN only).
Property Management (/api/v1/properties)

GET /: List all properties.

POST /: Add a new property (ADMIN only).

PUT /{id}: Update a property (ADMIN only).

DELETE /{id}: Delete a property (ADMIN only).
Booking Management (/api/v1/bookings)

GET /: List all bookings (ADMIN only).

POST /: Create a new booking.

DELETE /{id}: Cancel a booking.

Configuration

Application Properties

Configure the following in application.properties or through environment variables:

