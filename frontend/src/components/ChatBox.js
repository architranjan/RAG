import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Message from "./Message";
import ChatInput from "./ChatInput";

function ChatBox({ messages, loading, askQuestion }) {
  const [question, setQuestion] = useState("");
  const navigate = useNavigate();

  const send = () => {
    askQuestion(question);
    setQuestion("");
  };

  return (
    <div className="chat-box">
      <button onClick={() => navigate("/quiz")}>
        Go to Quiz
      </button>

      <div className="messages">
        {messages.map((msg, i) => (
          <Message key={i} role={msg.role} text={msg.text} />
        ))}
        {loading && <p className="thinking">Thinking...</p>}
      </div>

      <ChatInput
        question={question}
        setQuestion={setQuestion}
        send={send}
      />
    </div>
  );
}

export default ChatBox;
