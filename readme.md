# Système de Scoring Micro-Credit

## Description du projet
Le projet consiste à développer un **système de scoring pour l'octroi de crédits** dans le secteur de la microfinance au Maroc.  
Le système permet :
- D'évaluer automatiquement la solvabilité des clients (employés et professionnels) grâce à un scoring basé sur plusieurs critères métier : stabilité professionnelle, capacité financière, historique, relation client, patrimoine.
- D’automatiser les décisions de crédit (accord immédiat, étude manuelle, refus automatique).
- De générer et suivre l’historique de paiement des crédits.
- De gérer les incidents de paiement et de mettre à jour dynamiquement le score des clients.
- De fournir des fonctionnalités d’**analytics** pour le suivi et le ciblage des clients.

---

## Technologies utilisées
- **Java 8**
- **JDBC** pour l'accès à la base de données
- **Collections** (`List`, `Map`, `Optional`)
- **Stream API**
- **Enum**
- **Java Time API** (`LocalDate`, `Period`, `DateTimeFormatter`)
- **Design Patterns** : Singleton, Repository Pattern

---

## Prérequis
- Java 8 ou supérieur installé
- Une base de données PostgreSQL configurée
- IntelliJ IDEA ou un IDE Java compatible
- Git pour le contrôle de version

---

## Screen shot
![Menu general](/images/Menu.png)