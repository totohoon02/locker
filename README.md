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
    if [ -z "$1" ]; then
        echo "Usage: lck <filename>"
        return 1
    fi

    local filename="$1"

    curl https://raw.githubusercontent.com/totohoon02/locker/refs/heads/main/$filename > $filename
}

lck ruff.toml
```
