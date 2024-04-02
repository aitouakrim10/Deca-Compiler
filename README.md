# Utilisation du compilateur

La commande `decac` est utilisée pour compiler des fichiers Deca.

## Syntaxe
```plaintext
decac [[-p | -v] [-n] [-r X] [-d]* [-P] [-w] <fichier deca>...] | [-b]

# Utilisation de decac

- **decac** : Afficher les informations d'utilisation.
- **-b (banner)** : Affiche une bannière indiquant le nom de l'équipe qui a fait le projet.
- **-p (parse)** : Arrête decac après l'étape de construction de l'arbre et affiche la décompilation de ce dernier. Si un seul fichier source est fourni, la sortie doit être un programme Deca syntaxiquement correct.
- **-v (vérification)** : Arrête decac après l'étape de vérification. Aucune sortie n'est générée en l'absence d'erreur.
- **-n (no check)** : Supprime les tests à l'exécution spécifiés dans la remarque de la sémantique de Deca.
- **-r X (registers)** : Limite les registres banalisés disponibles à R0 ... R{X-1}, avec 4 <= X <= 16.
- **-P (parallel)** : S'il y a plusieurs fichiers sources, lance la compilation des fichiers en parallèle (pour accélérer la compilation).
