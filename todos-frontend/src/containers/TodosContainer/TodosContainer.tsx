import { useContext } from "react"
import styles from "./TodosContainer.module.scss"
import { Todo, TodosContext } from "../../context/TodosContextProvider"
import TodoItem from "../../components/Todo/TodoItem";
import NewTodo from "../../components/NewTodo/NewTodo";
const TodosContainer = () => {
  const { todos, setTodos, showNewTodo, setShowNewTodo } =
    useContext(TodosContext);
  const handleAddNew = () => { 
    // const newTodo = {
    //   content: "",
    //   isArchived: false,
    //   isCompleted: false,
    //   category: null
    // }
    // const newTodoList = [newTodo, ...todos];
    // setTodos(newTodoList);
    setShowNewTodo(true);
  }


  return (
    <div className={styles.container}>
      <div>My TODO List</div>

      <button onClick={handleAddNew}>Add new</button>
      {showNewTodo&&<NewTodo />}
      {todos.map((todo:Todo) => { return <TodoItem todo={todo}/>})}
    </div>
  )
}

export default TodosContainer