#!/bin/sh
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "Debut des tests invalides partie A"

for i in ./src/test/deca/syntax/invalid/created/sans_objet/*.deca
do
    
    output=$(test_synt "$i" 2>&1)
    filename=$(basename "$i")
    if echo "$output" | grep -q -E "$i:[1-9][0-9]*:.*"; then
        # Vérifie si la sortie contient un numéro de ligne non nul
        echo "Echec attendu pour test_synt dans $filename"
    elif echo "$output" | grep -q -E "$i:0:.*"; then
        echo "Erreur renvoyé à la ligne 0 dans  $filename"
        exit 1;
    elif echo "$output" | grep -q -E ".*Exception.*(.*\.java:[0-9]*)"; then
        echo "Exception non rattrapé dans  $filename"
        exit 1;
       
    else
        echo "Succes inattendu pour test_synt dans $filename"
        exit 1
    fi
done

for i in ./src/test/deca/syntax/invalid/created/objet/*.deca
do
    
    output=$(test_synt "$i" 2>&1)
    filename=$(basename "$i")
    if echo "$output" | grep -q -E "$i:[1-9][0-9]*:.*"; then
        # Vérifie si la sortie contient un numéro de ligne non nul
        echo "Echec attendu pour test_synt dans $filename"
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

echo "Debut des tests valides partie A"


for i in ./src/test/deca/syntax/valid/created/sans_objet/*.deca
do
    
    filename=$(basename "$i")
    if test_synt "$i" 2>&1 | \
    grep -q -e ':[0-9][0-9]*:'
    then
        echo "$filename failed"
        exit 1
    else
        echo "$filename passed"
fi

done


for i in ./src/test/deca/syntax/valid/created/objet/*.deca
do
    
    filename=$(basename "$i")
    if test_synt "$i" 2>&1 | \
    grep -q -e ':[0-9][0-9]*:'
    then
        echo "Echec inattendu pour test_synt"
        exit 1
    else
        echo "$filename passed"
fi

done



exit 0