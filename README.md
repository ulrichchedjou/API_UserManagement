# API de Gestion des Utilisateurs

## Prérequis
- Java 17+
- Maven

## Installation
1. Cloner le dépôt :
````bash git clone https://github.com/ulrichchedjou/API_UserManagement.git````

## Lancer l'API 
``mvn spring-boot:run``

## Test d'authentification
1. Enregistrement
   ``POST /api/v1/auth/register
      Body:
      {
          "name": "John Doe",
          "email": "john@example.com",
          "password": "password123"
      }``
2. Connexion
   ``POST /api/v1/auth/login
      Body:
      {
          "email": "john@example.com",
          "password": "password123"
      }
      Response:
      {
          "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
      }``
3. Utiliser le token :

Ajouter l'entête suivant aux requêtes protégées :   
``Authorization: Bearer <token>`` Nb: le token sera généré automatiquement

### Documentation API

Accéder à Swagger UI : ``http://localhost:3300/swagger-ui.html``

Pour tester l'API :
1. Enregistrez un utilisateur via `/api/v1/auth/register`
2. Connectez-vous pour obtenir un JWT via `/api/v1/auth/login`
3. Utilisez le token JWT dans l'en-tête Authorization pour accéder aux autres endpoints

Cette implémentation inclut toutes les fonctionnalités demandées avec une sécurité JWT robuste et une documentation Swagger complète.
