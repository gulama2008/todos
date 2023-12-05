import { Todo } from "../context/TodosContextProvider";

export class TodoService { 
    public static async get(): Promise<Todo[]> { 
        const response = await fetch("http://localhost:8080/todos");
        return await response.json();
    }
}