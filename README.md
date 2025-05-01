# locker
Config document locker

### Useage
```
curl https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main/ruff.toml > ruff.toml
```

### easy-start-up

```bash
lck(){
    # get file
    local baseUrl="https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main"
    
    # help
    if [[ "$1" == "--help" || "$1" == "-h" ]]; then
        curl $baseUrl/help.txt
        return 1
    fi

    # No input    
    if [ -z "$1" ]; then
        echo "Usage: lck <filename | --docker/-d> [docker:filename]"
        return 1
    fi

    # docker
    if [[ "$1" == "--docker" || "$1" == "-d" ]]; then
        if [ -z "$2" ]; then
            echo "Not specified Docker"
            return 1
        fi
        local dir="$2"
        mkdir "$dir"
        curl -sSL "$baseUrl/docker/$dir/docker-compose.yml" -o "./$dir/docker-compose.yml"

    # Java config
    elif [[ "$1" == "--java" || "$1" == "-j" ]]; then
        if [ -z "$2" ]; then
            echo "Not specified Java"
            return 1
        fi
        
        local dir="$2"
        dir="$(tr '[:lower:]' '[:upper:]' <<< "${dir:0:1}")${dir:1}"
        curl -sSL "$baseUrl/java/${dir}Config.java" -o "${dir}Config.java"

    # config.yml
    else
        local dir="$1"
        dir=$(echo "$dir" | tr '[:upper:]' '[:lower:]')
        curl -sSL "$baseUrl/${dir}Config.yml" -o "${dir}Config.yml"
    fi
}

lck ruff.toml
lck -d chromadb
```

### Chrome Extension version
- [link](https://chromewebstore.google.com/detail/locker-config-document-re/oeipihefcncbcljghboadfhhogplmdip?authuser=0&hl=ko)
