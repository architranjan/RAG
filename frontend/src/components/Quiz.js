import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

function Quiz() {
  const [quiz, setQuiz] = useState(null);
  const [selected, setSelected] = useState({});
  const [score, setScore] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const generateQuiz = async () => {
    setLoading(true);
    setQuiz(null);
    setSelected({});
    setScore(null);

    try {
      const res = await fetch("http://localhost:8080/api/quiz");
      if (!res.ok) throw new Error();

      const data = await res.json();
      setQuiz(data);
    } catch {
      toast.error("Failed to generate quiz");
    } finally {
      setLoading(false);
    }
  };

  const handleSelect = (qIndex, optIndex) => {
    if (selected[qIndex] !== undefined) return;
    setSelected((prev) => ({ ...prev, [qIndex]: optIndex }));
  };

  const submitQuiz = () => {
    let s = 0;
    quiz.questions.forEach((q, i) => {
      if (selected[i] === q.correctAnswer) s++;
    });
    setScore(s);
  };

  return (
    <div className="quiz-box">
      <button onClick={() => navigate("/chat")}>
        ← Back to Chat
      </button>

      <h2>📝 Quiz</h2>

      <button onClick={generateQuiz} disabled={loading}>
        {loading ? "Generating..." : "Generate Quiz"}
      </button>

      {quiz &&
        quiz.questions.map((q, i) => (
          <div key={i} className="quiz-question">
            <h4>{i + 1}. {q.question}</h4>

            {q.options.map((opt, idx) => {
              let cls = "quiz-option";
              if (selected[i] !== undefined) {
                if (idx === q.correctAnswer) cls += " correct";
                else if (idx === selected[i]) cls += " wrong";
              }

              return (
                <button
                  key={idx}
                  className={cls}
                  onClick={() => handleSelect(i, idx)}
                >
                  {opt}
                </button>
              );
            })}
          </div>
        ))}

      {quiz && (
        <button className="submit-btn" onClick={submitQuiz}>
          Submit Quiz
        </button>
      )}

      {score !== null && (
        <h3 className="score">
          Score: {score} / {quiz.questions.length}
        </h3>
      )}
    </div>
  );
}

export default Quiz;
