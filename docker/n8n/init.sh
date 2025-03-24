#!/bin/bash

# Start Ollama in the background.
/bin/ollama serve &
# Record Process ID.
pid=$!

# Pause for Ollama to start.
sleep 5

# pull chat model
echo "🔴 Pull Chat Model..."
ollama pull gemma2:2b
echo "🟢 Done!"

# Wait for Ollama process to finish.
wait $pid