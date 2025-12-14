from sentence_transformers import SentenceTransformer
from flask import Flask, request, jsonify

app = Flask(__name__)
model = SentenceTransformer("all-MiniLM-L6-v2")

@app.route("/embed", methods=["POST"])
def embed():
    texts = request.json["texts"]
    embeddings = model.encode(texts).tolist()
    return jsonify(embeddings)

app.run(port=5000)
