# ENI-Encheres
application ENI-Encheres du projet JAVA EE
Questions :

+ La liste des enchères en cous lorsque l'on est déconnecter est-elle triée ? Si oui, par quel critère ? 
= oui tri des plus récentes en premier.

+ Quelles sécurités pour le mdp ? (caractères spéciaux, etc..)
= 12 char min,  une min, une maj, un chiffre et un char spé

+ A partir de combien de temps l'enchere terminée est-elle archivée ?
= 1 mois historisé

+ parametre de confidentialité lors de la visualisation d'un profil : qu'affiche-t-on ? ( profil privé, obligation d'etre connecter pour acceder aux infos,...) 
= non connecté = rien (impossible de cliquer sur un pseudo). si connecté, accès seulement à la ville et au cp. Si enchère remporté, accès à tout son profil

+ durée max/min d'une enchere ? - valeur par défaut ?
= min 1jour (default value) et max 7 jours

+ quelles actions a prevoir apres la suppression d'un compte ?
= les encheres pas commencées sont suppr. les encheres en cours sont annulées et passées au statut terminée le dernier encherisseur est recrédité et l'enchere est historisée. toutes les terminées sont historisées. ses offres sont annulées et la derniere offre est remise (de l'ancienne personne)

page de présentation des encheres, pas d'adresse de retrait si l'enchere est en cours et si on est pas le gagnant.

Si suppression on doit verifier que l'ancien encherisseur à les points nécessaires pour bid.
