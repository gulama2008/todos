import { useContext } from "react"
import styles from "./TodosContainer.module.scss"
import { TodosContext } from "../../context/TodosContextProvider"
const TodosContainer = () => {
  const { todos } = useContext(TodosContext);
  return (
    <div className={styles.container}>
      <div>My TODO List</div>
    </div>
  )
}

export default TodosContainer