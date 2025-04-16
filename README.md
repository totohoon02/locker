# locker
Config document locker

### Useage
```
curl https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main/ruff.toml > ruff.toml
```

### easy-start-up

```bash
lck(){
    # help
    if [[ "$1" == "--help" || "$1" == "-h" ]]; then
        echo "locker/"
        echo "      |--chromaConfig.yml"
        echo "      |--elasticConfig.yml"
        echo "      |--ollamaConfig.yml"
        echo "      |--sqliteConfig.yml"
        echo "      |--swaggerConfig.yml"
        echo "      |--ruff.toml"
        echo "      |--docker"
        echo "         |--chromadb"
        echo "         |--elastic8"
        echo "         |--nginx"
        echo "         |--ollama"
        echo "         |--airflow"
        echo "         |--mysql"
        echo "         |--mongodb"
        echo "         |--n8n"
        echo "         |--kafka"
        echo "         |--postgre"
        echo "      |--java"
        echo "         |--SwaggerConfig.java"
        echo ""
        echo "##### Usage #####"
        echo "lck chroma"       // chromaConfig.yml
        echo "lck -d chromadb"  // chroma docker-compose.yml
        echo "lck -j swagger"  // SwaggerConfig.java
        echo "##### Usage #####"
        return 1
    fi

    # No input    
    if [ -z "$1" ]; then
    echo "Usage: lck <filename | --docker/-d> [docker:filename]"
    return 1
    fi

    # get file
    local baseUrl="https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main"
    
    if [[ "$1" == "--docker" || "$1" == "-d" ]]; then

        if [ -z "$2" ]; then
            echo "Not specified Docker"
            return 1
        else
            local dir="$2"
            mkdir "$dir"
            curl -sSL "$baseUrl/docker/$dir/docker-compose.yml" -o "./$dir/docker-compose.yml"
        fi

    elif [[ "$1" == "--java" || "$1" == "-j" ]]; then
        local dir="$2"
        dir="$(tr '[:lower:]' '[:upper:]' <<< "${dir:0:1}")${dir:1}"
        curl -sSL "$baseUrl/java/${dir}Config.java" -o "${dir}Config.java"

    else
        local dir="$1"
        dir=$(echo "$dir" | tr '[:upper:]' '[:lower:]')
        curl -sSL "$baseUrl/${dir}Config.yml" -o "${dir}Config.yml"
    fi
}

lck ruff.toml
lck -d chromadb
```
