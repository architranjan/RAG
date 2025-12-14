📄 PDF RAG System with Quiz Generation

A Retrieval-Augmented Generation (RAG) based web application that allows users to upload PDF documents, ask questions directly from the PDF content, and generate MCQ-based quizzes from the same document.

The system combines semantic search using embeddings with LLM-powered answer and quiz generation, ensuring responses are grounded strictly in the uploaded document.

🚀 Features

📤 Upload PDF documents

🔍 Ask natural language questions from the PDF

🧠 Context-aware answers using RAG

📝 Generate quizzes

5 MCQ questions based on the PDF content

Each question has 4 options

Instant evaluation (correct/incorrect)

🎯 Answers strictly based on PDF content

⚡ Fast semantic search using vector embeddings

🧩 System Architecture
React Frontend
      |
      v
Spring Boot Backend (RAG Orchestrator)
      |
      |-- PostgreSQL + pgvector (PDF chunks + embeddings)
      |
      |-- Python Flask Service (Local)
      |      └── SentenceTransformer (all-MiniLM-L6-v2)
      |
      v
LLM (Answer & Quiz Generation)

🛠️ Tech Stack
Frontend

React.js

Fetch API for backend communication

Simple and clean UI for upload, chat, and quiz

Backend

Spring Boot

REST APIs for:

PDF upload

Question answering

Quiz generation

PostgreSQL for data storage

pgvector for similarity search

Embedding Service

Python Flask

SentenceTransformers

SentenceTransformer("all-MiniLM-L6-v2")


Runs locally as a microservice

📚 How It Works (RAG Flow)

PDF Upload

PDF is uploaded from React UI

Spring Boot extracts text

Text is split into smaller chunks

Embedding Generation

Each chunk is sent to the Flask service

Flask generates embeddings using:

all-MiniLM-L6-v2


Embeddings are stored in PostgreSQL using pgvector

Question Answering

User asks a question

Question embedding is generated

Top-K relevant chunks are fetched using vector similarity

Retrieved chunks are sent as context to the LLM

Final answer is generated only from the PDF context

Quiz Generation

Relevant chunks are retrieved from the PDF

LLM generates:

5 MCQ questions

4 options per question

Correct answer index

Frontend handles quiz interaction and scoring

📝 Quiz Format (API Response Example)
{
  "quiz": [
    {
      "question": "What is a black hole?",
      "options": [
        "A dying star",
        "A region with extreme gravity",
        "A galaxy",
        "A comet"
      ],
      "correctAnswer": 1
    }
  ]
}

🗄️ Database Design (Simplified)

pdf

id

filename

pdf_chunk

id

content

embedding (vector)

pdf_id (FK)

▶️ Running the Project
1️⃣ Start Embedding Service (Flask)
python app.py


Runs on http://localhost:5000

2️⃣ Start Backend (Spring Boot)
mvn spring-boot:run


Runs on http://localhost:8080

3️⃣ Start Frontend (React)
npm install
npm start


Runs on http://localhost:3000

🎯 Key Highlights

Fully document-grounded responses

Local embedding generation (no paid API dependency)

Clean separation of services (React, Spring Boot, Flask)

Scalable vector search using pgvector

Interview-ready RAG implementation

🔮 Future Enhancements

Multiple PDF support

User authentication

Timed quizzes

Difficulty-based quiz generation

Support for DOCX and TXT files

Streaming responses for chat

👨‍💻 Author

Archit Ranjan
Built as a learning-focused project to understand RAG systems, embeddings, and real-world AI application design.
