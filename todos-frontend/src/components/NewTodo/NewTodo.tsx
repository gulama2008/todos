import { useContext, useState } from "react";
import { Category, TodosContext } from "../../context/TodosContextProvider";
import { TodoService } from "../../services/todos-service";

const NewTodo = () => {
  const { categories, setShowNewTodo, changeTodos, setChangeTodos } =
    useContext(TodosContext);
  const [content, setContent] = useState<string>("");
  const [category, setCategory] = useState<number>(0);
  const handleCancel = () => {
    setShowNewTodo(false);
  };
  const handleAdd = () => {
    const data = {
      content: content,
      categoryId: category,
    };
    TodoService.createTodo(data)
      .then(() => setChangeTodos(changeTodos + 1))
      .catch((e) => console.log(e));
    setShowNewTodo(false);
  };
  return (
    <div>
      <input
        type="text"
        value={content}
        onChange={(e) => {
          setContent(e.target.value);
        }}
      />
      <select
        name=""
        id=""
        value={category}
        onChange={(e) => {
          setCategory(parseInt(e.target.value));
        }}
      >
        <option value="">Please select a category</option>
        {categories.map((category: Category) => {
          return (
            <option value={category.id} key={category.id}>
              {category.name}
            </option>
          );
        })}
      </select>
      <button onClick={handleAdd}>Save</button>
      <button onClick={handleCancel}>Cancel</button>
    </div>
  );
};

export default NewTodo;
