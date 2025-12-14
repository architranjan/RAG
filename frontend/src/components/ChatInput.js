function ChatInput({ question, setQuestion, send }) {
  return (
    <div className="input-box">
      <input
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
        placeholder="Ask something from PDF..."
        onKeyDown={(e) => e.key === "Enter" && send()}
      />
      <button onClick={send}>Send</button>
    </div>
  );
}

export default ChatInput;
