#!/bin/bash

# Start Ollama in the background.
/bin/ollama serve &
# Record Process ID.
pid=$!

# Pause for Ollama to start.
sleep 5

# embedding Model(cached)
echo "🔴 Pull Embedding Model..."
ollama pull jeffh/intfloat-multilingual-e5-large-instruct:f16
echo "🟢 Done!"

# Wait for Ollama process to finish.
wait $pid
