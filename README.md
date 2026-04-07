<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEDOC Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
            background-color: #f9f9f9;
            color: #333;
        }
        h1, h2, h3 {
            color: #2c3e50;
        }
        hr {
            margin: 20px 0;
            border: 0;
            border-top: 1px solid #ccc;
        }
        ul {
            margin: 10px 0 10px 20px;
        }
    </style>
</head>
<body>

<h1>🎓 CEDOC Management System</h1>

<h2>📌 Description</h2>
<p>Cette application web permet la gestion du processus de candidature au Centre d’Études Doctorales (CEDOC) de l’INPT.<br>
Elle facilite la gestion des sujets.</p>

<hr>

<h2>🚀 Fonctionnalités principales</h2>

<h3>👤 Authentification & Sécurité</h3>
<ul>
    <li>Authentification stateless avec JWT</li>
    <li>Sécurisation des endpoints avec Spring Security</li>
    <li>Gestion des rôles :
        <ul>
            <li>CANDIDAT</li>
            <li>PROFESSEUR</li>
            <li>CHEF_EQUIPE</li>
            <li>CHEF_CEDOC</li>
        </ul>
    </li>
</ul>

<h3>🧑‍🎓 Gestion des candidats</h3>
<ul>
    <li>Inscription des candidats avec création d’un compte utilisateur</li>
    <li>Gestion des informations personnelles et académiques</li>
    <li>Upload de documents (CV, diplômes, relevés)</li>
</ul>

<h3>📚 Gestion des sujets</h3>
<ul>
    <li>Création de sujets par les professeurs</li>
    <li>Association des sujets à une équipe</li>
    <li>Validation des sujets par le chef d’équipe</li>
</ul>

<h3>📝 Gestion des candidatures</h3>
<ul>
    <li>Soumission de candidature par un candidat</li>
    <li>Sélection de 3 sujets avec ordre de préférence</li>
    <li>Suivi de l’état de la candidature :
        <ul>
            <li>EN_ATTENTE</li>
            <li>VALIDÉE</li>
            <li>REFUSÉE</li>
        </ul>
    </li>
</ul>

<h3>👥 Gestion des équipes</h3>
<ul>
    <li>Association des professeurs à des équipes</li>
    <li>Gestion des chefs d’équipe</li>
</ul>

<hr>

<h2>🏗️ Architecture</h2>
<ul>
    <li>Backend : Spring Boot</li>
    <li>Sécurité : Spring Security + JWT</li>
    <li>Base de données : MySQL</li>
    <li>ORM : JPA / Hibernate</li>
    <li>API : RESTful</li>
</ul>

<hr>

<h2>🔐 Authentification</h2>
<ul>
    <li>Login via email et mot de passe</li>
    <li>Génération d’un token JWT</li>
    <li>Utilisation du token dans les requêtes</li>
</ul>

</body>
</html>
