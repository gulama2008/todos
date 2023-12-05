import { Category } from "../context/TodosContextProvider";

export class CategoryService {
    public static async get(): Promise<Category[]> {
        const response = await fetch("http://localhost:8080/categories");
        return await response.json();
  }
}