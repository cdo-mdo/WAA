import { useState } from "react";
import "./App.css";

function App() {
  const [todoTasks, setTodoTasks] = useState([
    { id: 1, name: "Learn React" },
    { id: 2, name: "Practice JavaScript" },
    { id: 3, name: "Do homework" },
  ]);

  const [doneTasks, setDoneTasks] = useState([]);

  // Move from Todo → Done
  const moveToDone = (task) => {
    setTodoTasks(todoTasks.filter((t) => t.id !== task.id));
    setDoneTasks([...doneTasks, task]);
  };

  // Move from Done → Todo
  const moveToTodo = (task) => {
    setDoneTasks(doneTasks.filter((t) => t.id !== task.id));
    setTodoTasks([...todoTasks, task]);
  };

  return (
      <div className="container">
        <h1>Task Manager</h1>

        <div className="lists">
          {/* TODO LIST */}
          <div className="list">
            <h2>Todo Tasks</h2>
            <ul>
              {todoTasks.map((task) => (
                  <li key={task.id}>
                    {task.id}. {task.name}
                    <button onClick={() => moveToDone(task)}>✔ Done</button>
                  </li>
              ))}
            </ul>
          </div>

          {/* DONE LIST */}
          <div className="list">
            <h2>Done Tasks</h2>
            <ul>
              {doneTasks.map((task) => (
                  <li key={task.id}>
                    {task.id}. {task.name}
                    <button onClick={() => moveToTodo(task)}>↩ Undo</button>
                  </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
  );
}

export default App;
