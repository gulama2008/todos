import { Todo } from "../context/TodosContextProvider";
export interface NewTodoParams {
  content: string;
  categoryId: number;
}
export class TodoService {
  public static async get(): Promise<Todo[]> {
    const response = await fetch("http://localhost:8080/todos");
    return await response.json();
  }

  public static async createTodo(data: NewTodoParams): Promise<Todo> {
    const response = await fetch("http://localhost:8080/todos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
      if (!response.ok) { 
          throw new Error("Could not create todo")
      }
      return response.json();
  }
    
    public static async deleteTodo(id: number){ 
        const response = await fetch(`http://localhost:8080/todos/${id}`, {
          method: "DELETE",
        });
        if (!response.ok) {
          throw new Error("Could not delete");
        }
    }
}
