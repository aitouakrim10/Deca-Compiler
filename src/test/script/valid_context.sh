
#!/bin/sh
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "debut des tests valides partie B"
for i in ./src/test/deca/context/valid/created/objet/*.deca
do
    filename=$(basename "$i")
    output=$(decac -v "$i" 2>&1)
    if [ -z "$output" ]; then
        echo "$filename passed"
    else
        echo "$filename failed"
        exit 1
    fi

done

for i in ./src/test/deca/context/valid/created/sans_objet/*.deca
do
    filename=$(basename "$i")
    output=$(decac -v "$i" 2>&1)
    if [ -z "$output" ]; then
        echo "$filename passed"
    else
        echo "$filename failed"
        exit 1
    fi

done
exit 0
