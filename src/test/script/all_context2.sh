#!/bin/sh
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "Debut des tests invalides partie B version 2"

for i in ./src/test/deca/context/invalid/created/objet/*.deca
do
    filename=$(basename "$i")
    expected_line=$(sed -n 's/.*Ligne \([0-9]\+\) :.*/\1/p' "$i")  # Extrait le numéro de ligne depuis le fichier source
    output=$(test_context "$i" 2>&1)  # Capture la sortie de la commande test_context

    if echo "$output" | grep -q -E "$i:$expected_line:.*"; then
        # Vérifie si la sortie contient le numéro de ligne attendu
        echo "Echec attendu pour test_context dans $filename"
    else
        # Affiche un message d'erreur avec le numéro de ligne attendu
        echo "Une erreur etait attendue à la ligne $expected_line du fichier $filename"
        exit 1
    fi
done

exit 0
