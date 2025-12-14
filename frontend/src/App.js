import { useState } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import UploadPdf from "./components/UploadPdf";
import ChatBox from "./components/ChatBox";
import Quiz from "./components/Quiz";
import "./styles.css";

function App() {
  const [file, setFile] = useState(null);
  const [uploaded, setUploaded] = useState(false);
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(false);

  const uploadPdf = async () => {
    if (!file) {
      toast.warning("Please select a PDF");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const res = await fetch("http://localhost:8080/api/upload", {
        method: "POST",
        body: formData,
      });

      if (!res.ok) throw new Error();
      toast.success("PDF uploaded successfully");
      setUploaded(true);
    } catch {
      toast.error("Upload failed");
    }
  };

  const askQuestion = async (question) => {
    if (!question.trim()) return;

    setMessages((prev) => [...prev, { role: "user", text: question }]);
    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/query", {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: question,
      });

      const answer = await res.text();
      setMessages((prev) => [...prev, { role: "bot", text: answer }]);
    } catch {
      toast.error("Error getting answer");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app">
      <h2>📄 PDF RAG Assistant</h2>

      <Routes>
        <Route
          path="/"
          element={
            uploaded ? (
              <Navigate to="/chat" />
            ) : (
              <UploadPdf
                file={file}
                setFile={setFile}
                uploadPdf={uploadPdf}
              />
            )
          }
        />

        <Route
          path="/chat"
          element={
            uploaded ? (
              <ChatBox
                messages={messages}
                loading={loading}
                askQuestion={askQuestion}
              />
            ) : (
              <Navigate to="/" />
            )
          }
        />

        <Route
          path="/quiz"
          element={uploaded ? <Quiz /> : <Navigate to="/" />}
        />
      </Routes>

      <ToastContainer position="top-right" autoClose={2500} />
    </div>
  );
}

export default App;
