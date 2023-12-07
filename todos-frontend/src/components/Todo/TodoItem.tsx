import React, { useContext, useEffect, useState } from "react";
import {
  Category,
  Todo,
  TodosContext,
} from "../../context/TodosContextProvider";
import { TodoService } from "../../services/todos-service";
export interface TodoProps {
  todo: Todo;
}
const TodoItem = ({ todo }: TodoProps) => {
  const { categories,changeTodos,setChangeTodos } = useContext(TodosContext);
  const [todoContent, setTodoContent] = useState<string>("");
  const [todoCategory, setTodoCategory] = useState<number>(todo.category.id);
  useEffect(() => {
    setTodoContent(todo.content);
    setTodoCategory(todo.category.id);
  }, []);
  const handleContentChange = (e: any) => {
    setTodoContent(e.target.value);
  };
    const handleCategoryChange = (e: any) => {
      setTodoCategory(e.target.value);
    };
  
  const handleDelete = () => { 
    TodoService.deleteTodo(todo.id)
      .then(() => setChangeTodos(changeTodos-1))
    .catch(e=>console.log(e))
  }
  
  return (
    <div>
      <input value={todoContent} onChange={handleContentChange} />
      <select onChange={handleCategoryChange} value={todoCategory}>
        {categories.map((category: Category) => {
          return <option value={category.id} key={category.id}>{category.name}</option>;
        })}
      </select>
      <input type="checkbox" />
      <button>copy</button>
      <button onClick={handleDelete}>delete</button>
    </div>
  );
};

export default TodoItem;
