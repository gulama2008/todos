import { createContext, useState } from 'react'
import todosList from "../data/todos.json";
import categoryList from "../data/categories.json"
export const TodosContext = createContext<any>(null);
export interface Todo {
  id: number;
  content: string;
  isArchived: boolean;
  isCompleted: boolean;
  category: string;
}
export interface Category { 
    id: number;
    name:string
}
const TodosContextProvider = ({ children }: any) => {
    const [todos, setTodos] = useState<Todo[]>(todosList);
    const [categories, setCategories] = useState<Category[]>(categoryList);
  return (
    <TodosContext.Provider
      value={{
        todos,
        setTodos,
        categories,
        setCategories,
      }}
    >
      {children}
    </TodosContext.Provider>
  );
}

export default TodosContextProvider