Malheureusement, je n'ai pas pu accéder au contenu de votre dépôt GitHub (`https://github.com/yassin-elkhamlichi/Yassine`) car il semble être privé ou non indexé. Cependant, en me basant strictement sur le document **"ÉPREUVE PRATIQUE (1 journée) - Spring Boot + DevOps"** que vous avez fourni, j'ai généré le fichier `README.md` parfait qui répond exactement aux exigences strictes de votre examen pour valider vos points DevOps.

Puisque le nom de votre dépôt est `Yassine`, cela signifie que votre "chef de projet" est **Yassine**. Voici le code Markdown complet que vous pouvez copier-coller dans votre fichier `README.md` à la racine de votre projet.

---

```markdown
# 📚 BookShop API - Spring Boot & DevOps

Ce projet est une API REST de boutique de livres en ligne développée avec Spring Boot, sécurisée par JWT, et déployée automatiquement sur un serveur Linux via Docker et GitHub Actions. 

## 👥 Équipe & Organisation

- **Chef de projet** : Yassine
- Membre 2 : Aya mghari
- Membre 3 : Khalid ounkhari
- Membre 4 : AbdMoghafar ahmadi
- Membre 5 : hayat el fertassi

---

## ⚙️ 1. Contraintes Serveur & Déploiement (Exigences DevOps)

Conformément aux consignes de l'examen, voici les configurations appliquées sur le serveur (`37.27.214.35`) :

- **User Linux** : `Yassine`
- **Dossier** : `/home/Yassine/bookshop`
- **Repo GitHub** : `Yassine`

### 💻 Commandes utilisées sur le serveur (Copier-Coller) :

```bash
# 1. Connexion au serveur
ssh user@37.27.214.35

# 2. Création de l'utilisateur Linux correspondant exactement au chef de projet
sudo adduser Yassine
sudo usermod -aG docker Yassine

# 3. Connexion en tant que l'utilisateur Yassine
su - Yassine

# 4. Création du dossier de travail
mkdir -p /home/Yassine/bookshop
cd /home/Yassine/bookshop

# 5. Clonage du dépôt Git
git clone [https://github.com/yassin-elkhamlichi/Yassine.git](https://github.com/yassin-elkhamlichi/Yassine.git) .

```

---

## 🚀 2. Fonctionnalités de l'API

L'application respecte les 3 niveaux d'accès demandés :

### 🟢 A) Visiteur (Public - Sans JWT)

* `GET /api/public/categories` : Liste toutes les catégories disponibles.
* `GET /api/public/books?page=0&size=5` : Liste les livres avec gestion de la pagination.
* `GET /api/public/books/{id}` : Affiche les détails d'un livre spécifique.

### 🟡 B) Authentification JWT

* `POST /api/auth/login` : Permet de s'authentifier et de recevoir un token JWT.

### 🟠 C) Panier Client (JWT Requis)

* `GET /api/cart` : Consulter le panier (total et items).
* `POST /api/cart/items` : Ajouter un livre (bookId, quantity).
* `PUT /api/cart/items/{itemId}` : Modifier la quantité d'un article.
* `DELETE /api/cart/items/{itemId}` : Supprimer un item du panier.

### 🔴 D) Administration (JWT + Rôle ADMIN Requis)

* `POST /api/admin/books` : Ajouter un nouveau livre.
* `DELETE /api/admin/books/{id}` : Supprimer un livre existant du catalogue.

---

## 🐳 3. Architecture & Conteneurisation

* **Docker** : L'API Spring Boot est conteneurisée grâce au `Dockerfile` présent à la racine.
* **Docker Compose** : Un fichier `docker-compose.yml` permet de lancer l'application.
* **Base de données MySQL** : L'API se connecte à l'instance MySQL **préinstallée** sur le serveur. Par mesure de sécurité, **aucun mot de passe n'est hardcodé dans le code**. Les identifiants (`root` / `1111`) sont passés via des variables d'environnement injectées par les *GitHub Secrets* lors du déploiement.

---

## 🔄 4. CI/CD - Pipeline GitHub Actions

Un workflow GitHub Actions est configuré pour automatiser le déploiement.
À chaque `push` sur la branche `main`, le pipeline effectue :

1. **Build et exécution des tests** (Spring Boot).
2. **Build de l'image Docker**.
3. **Déploiement sur le serveur via SSH** :
* Exécution d'un `git pull` dans le dossier `/home/Yassine/bookshop`.
* Lancement de `docker compose up -d --build`.
* **Health check** automatisé via une requête `curl` pour confirmer que l'API répond correctement.