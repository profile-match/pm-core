# pm-core
The core project for Profile Match

## build / deploiment

mvn package

Il faut ensuite deployer le war target/pm-core.war

Le serveur sur lequel est deployé le war doit avoir une datasource dont le JNDI est java:/MySqlDSIMP

Le paquetage en jar avec wildfly-swarm ne marche pas encore.

## créer une branche de feature
Voir page confluence pour les normes des branches et les noms à utiliser !

https://profile-match.atlassian.net/wiki/display/IM/Bien+commencer+le+projet
