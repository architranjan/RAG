# 📄 PDF RAG System with Quiz Generation

A **Retrieval-Augmented Generation (RAG)** based web application that allows users to upload PDF documents, ask questions directly from the PDF content, and generate MCQ-based quizzes from the same document.

This system ensures that all answers and quizzes are generated **strictly from the uploaded PDF**, making it reliable for educational and document-based learning use cases.

---

## 🚀 Features

- 📤 Upload PDF documents  
- 🔍 Ask natural language questions from the PDF  
- 🧠 Context-aware answers using RAG  
- 📝 Quiz generation from PDF content  
  - 5 MCQ questions  
  - 4 options per question  
  - Instant correct/incorrect evaluation  
- 🎯 Answers grounded strictly in document context  
- ⚡ Fast semantic search using vector embeddings  

---



## 🛠️ Tech Stack

### Frontend
- React.js
- Fetch API for backend communication

### Backend
- Spring Boot
- REST APIs for:
  - PDF upload
  - Question answering
  - Quiz generation
- PostgreSQL
- pgvector extension for similarity search

### Embedding Service
- Python Flask
- SentenceTransformers
```python
SentenceTransformer("all-MiniLM-L6-v2")
