# pm-core
The core project for Profile Match

## build / deploiment

mvn package
mvn wildfly-swarm:package

Ensuite au choix:
* déployer le war pm-core.war dans un serveur d'application
* lancer le jar : java -Dswarm.ds.connection.url="jdbc:mysql://localhost:3306/IMP?useSSL=false" -jar pm-core-swarm.jar qui contient lui même tout ce qu'il faut

Pour une raison que j'ignore, hibernate s'obstine à utiliser l'URL de connexion par default, il faut donc spécifier l'url de connection en paramètre de la commande comme au dessus.

## créer une branche de feature
Voir page confluence pour les normes des branches et les noms à utiliser !

https://profile-match.atlassian.net/wiki/display/IM/Bien+commencer+le+projet
