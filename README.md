# java-crypto2018
# M1 - 2017/2018 - Projet Cryptographie et jeu de cartes

Git commandes de base :
==

Récupérer le projet sous Git :
-
```
git clone https://github.com/Redkinoko/java-crypto2018
```

Vérifier l'intégrité des fichiers et voir ses modifs :
-
```
git status
```

Annuler les ajouts non commits :
-
```
git checkout .
```

Récupérer la dernière MAJ :
-
```
git pull origin master
```

Envoyer des données :
-
Procédure à suivre pour éviter d'écraser les autres contributions :
```
git add --all
git commit -a -m "VOTRE_DESCRIPTION_PERSONNALISE"
git pull origin master
git push origin master 
```

Vérifier ses branches locales :
-
```
git branch
```

Créer une nouvelle branche local :
-
```
git branch NOM_DE_BRANCHE
```

Changer de branche :
-
```
git checkout NOM_DE_BRANCHE
```

Synchroniser les branches :
-
```
git fetch origin NOM_BRANCH
```
ou
```
git fetch --all
```
