import React, { useContext, useEffect, useState } from "react";
import {
  Category,
  Todo,
  TodosContext,
} from "../../context/TodosContextProvider";
export interface TodoProps {
  todo: Todo;
}
const TodoItem = ({ todo }: TodoProps) => {
  const { categories } = useContext(TodosContext);
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
  return (
    <div>
      <input value={todoContent} onChange={handleContentChange} />
      <select onChange={handleCategoryChange} value={todoCategory}>
        {categories.map((category: Category) => {
          return <option value={category.id}>{category.name}</option>;
        })}
      </select>
      <input type="checkbox" />
      <button>copy</button>
      <button>delete</button>
    </div>
  );
};

export default TodoItem;
