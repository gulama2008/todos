import { createContext, useEffect, useState } from "react";
import todosList from "../data/todos.json";
import categoryList from "../data/categories.json";
import { TodoService } from "../services/todos-service";
import { CategoryService } from "../services/categories-service";
export const TodosContext = createContext<any>(null);
export interface Todo {
  id: number;
  content: string;
  isArchived: boolean;
  isCompleted: boolean;
  category: Category;
}
export interface Category {
  id: number;
  name: string;
}
const TodosContextProvider = ({ children }: any) => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [categories, setCategories] = useState<Category[]>([]);
  const [completedNum, setCompletedNum] = useState<number>(0);
  const [incompletedNum, setIncompletedNum] = useState<number>(0);
  const [showNewTodo, setShowNewTodo] = useState<boolean>(false);
  useEffect(() => {
    TodoService.get()
      .then((res) => {
        setTodos(res);
      })
      .catch((e) => console.log(e));

    CategoryService.get()
      .then((res) => {
        setCategories(res);
      })
      .catch((e) => console.log(e));
  }, []);
  useEffect(() => {
    const completedNum = todos.reduce((a: number, b: Todo) => {
      if (b.isCompleted) {
        return ++a;
      }
      return a;
    }, 0);
    setCompletedNum(completedNum);
    setIncompletedNum(todos.length - completedNum);
  }, [todos]);

  return (
    <TodosContext.Provider
      value={{
        todos,
        setTodos,
        categories,
        setCategories,
        completedNum,
        incompletedNum,
        showNewTodo,
        setShowNewTodo,
      }}
    >
      {children}
    </TodosContext.Provider>
  );
};

export default TodosContextProvider;
