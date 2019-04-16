Dans cet application, nous allons vous montrer comment intégrer JSF à Spring avec en utilisant:



1 - JSF XML faces-config.xml
2 - Annotations de spring

Conditions préalables:

1- JSF 2.2.8
2- Spring 3.1.2
3- Maven 3
4- IntelliJ IDEA 2017.3 x64
5- Tomcat 8 ou 9 Cela servira de serveur d'application
6- Notions postgresql de base
7- Notions en HTML et CSS

Avant de commencer, nous supposons que vous avez configuré votre base de données et vos serveurs.

1. Créer notre connexion Ressources:

La première chose à faire est de créer une ressource de connexion qui nous permettra de relier notre base de données à notre application.

Nous allons ouvrir notre console d’administration du serveur Tomcat en tapant cette URL dans notre navigateur.
Voici l'URL de la base de données: http: // localhost: 8080

Passez à l'étape suivante et entrez le nom d'utilisateur, le mot de passe et l'URL de votre serveur de base de données. 
Voici le db.properties de la base de données: 

db.username=
db.password=
db.url=jdbc:postgresql://localhost:5432/data
db.driver=org.postgresql.Driver
jpa.showSql=true

Nous avons fini de créer notre ressource de connexion.
Ensuite, nous créons une connexion entre notre IDE et notre base de données.

Jetez un coup d'oeil à votre base de données, votre base de données «database» contient trois table :

«outils» à 2 colonnes
«caracteristique» à 2 colonnes
«outilscaracteristique»  à 3 colonnes

N'oublie pas de créer le sequence qui vous permet d'incrémenter le "id" 
la valeur par defaut de "id" de la table : nextval('nom_de_sequence'::regclass)

-------------------------------------------------------------

2. Créer votre projet :

Ouvrez l'IDE et créez une nouvelle application.
dans ce cas on va utiliser la methode MVC, donc vous devez creer 3 couche + couche de managed bean

3. Créer vos classes d'entités:

créez maintenant vos classes d'entité à partir de votr base de données.
Générez votre classe dans un nouveau package appelé «model», il contient des requêtes nommées, 
des variables d'entité avec leurs contraintes, un constructeur, des getters et des setters et quelques fonctions.


 

