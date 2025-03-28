# locker
config document locker

### useage
```
curl https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main/ruff.toml > ruff.toml
```
- reference: https://github.com/astral-sh/ruff

### easy-start-up

```bash
lck(){
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
        echo ""
        echo "##### Usage #####"
        echo "lck chromaConfig.yml"
        echo "lck -d chromadb"
        echo "##### Usage #####"
        return 1
    fi
    
    if [ -z "$1" ]; then
        echo "Usage: lck <filename | docker> <docker:filename>"
        return 1
    fi

    if [[ "$1" == "--docker" || "$1" == "-d" ]]; then
        if [ -z "$2" ]; then
            echo "Not specified Docker"
            return 1
        else
            local dir="$2"
            echo "$dir"
            mkdir "$dir"
            curl https://github.com/totohoon02/locker/blob/main/docker/$dir/docker-compose.yml >> ./"$dir"/docker-compose.yml
        fi
    else
        curl https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main/"$1" >> "$1" 
    fi
}

lck ruff.toml
lck -d chromadb
```
