import { useContext } from "react";
import styles from "./TodosContainer.module.scss";
import { Todo, TodosContext } from "../../context/TodosContextProvider";
import TodoItem from "../../components/Todo/TodoItem";
import NewTodo from "../../components/NewTodo/NewTodo";
const TodosContainer = () => {
  const { todos, showNewTodo, setShowNewTodo } = useContext(TodosContext);
  const handleAddNew = () => {
    setShowNewTodo(true);
  };

  return (
    <div className={styles.container}>
      <div className={styles.inner_container}>
        <div className={styles.title}>My TODO List</div>

        <button onClick={handleAddNew} className={styles.add}>Add new</button>
        {showNewTodo && <NewTodo />}
        {todos.map((todo: Todo) => {
          return <TodoItem todo={todo} key={todo.id} />;
        })}
      </div>
    </div>
  );
};

export default TodosContainer;
