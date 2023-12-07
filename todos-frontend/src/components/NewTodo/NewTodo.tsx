import { useContext } from "react";
import { Category, TodosContext } from "../../context/TodosContextProvider";
import { TodoService } from "../../services/todos-service";

const NewTodo = () => {
    const { categories, setShowNewTodo } = useContext(TodosContext);
    const handleCancel = () => { 
        setShowNewTodo(false);
    }
    const handleAdd = () => { 
        const data = {
            content: "Buy iron vitamin c",
            categoryId: 3
        }
        TodoService.createTodo(data)
            .then(res => console.log(res))
        .catch(e=>console.log(e)
        )
        
    }
    return (
    <div>
      <input type="text" />
      <select name="" id="">
        <option value="">Please select a category</option>
        {categories.map((category: Category) => {
          return <option value={category.id}>{category.name}</option>;
        })}
      </select>
          <input type="checkbox" />
          <button onClick={handleAdd}>Add</button>
          <button onClick={handleCancel}>Cancel</button>
    </div>
  );
};

export default NewTodo;
