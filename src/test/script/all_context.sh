#!/bin/sh
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "Debut des tests invalides partie B"

for i in ./src/test/deca/context/invalid/created/sans_objet/*.deca
do
    
    output=$(test_context "$i" 2>&1)
    filename=$(basename "$i")
    if echo "$output" | grep -q -E "$i:[1-9][0-9]*:.*"; then
        # Vérifie si la sortie contient un numéro de ligne non nul
        echo "Echec attendu pour test_context dans $filename"
    elif echo "$output" | grep -q -E "$i:0:.*"; then
        echo "Erreur renvoyé à la ligne 0 dans  $filename"
        exit 1;
    elif echo "$output" | grep -q -E ".*Exception.*(.*\.java:[0-9]*)"; then
        echo "Exception non rattrapé dans  $filename"
        exit 1;
       
    else
        echo "Succes inattendu pour test_context dans $filename"
        exit 1
    fi
done
exit 0