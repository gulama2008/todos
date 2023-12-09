import { useContext, useEffect, useState } from "react";
import styles from "./SideBarContainer.module.scss";
import {
  Category,
  Todo,
  TodosContext,
} from "../../context/TodosContextProvider";
import SideBarItem from "../../components/SideBarItem/SideBarItem";
import { CategoryService } from "../../services/categories-service";
import add from "../../assets/cross.png";
const SideBarContainer = () => {
  const {
    categories,
    todos,
    incompletedNum,
    completedNum,
    changeCategories,
    setChangeCategories,
    currentCategory,
    setCurrentCategory,
  } = useContext(TodosContext);
  const [cateNums, setCateNums] = useState<number[]>([]);
  const [showAddCategory, setShowAddCategory] = useState<boolean>(false);
  const [categoryName, setCategoryName] = useState<string>("");
  useEffect(() => {
    console.log(categories);

    const cateNums = categories.map((category: Category) => {
      const num = todos.reduce((a: number, b: Todo) => {
        if (b.category.id === category.id) {
          return ++a;
        }
        return a;
      }, 0);
      return num;
    });
    setCateNums(cateNums);
  }, [todos, categories]);

  const handleChange = (e: any) => {
    setCategoryName(e.target.value);
  };

  const handleAddNewCategory = () => {
    console.log(categoryName);

    if (categoryName == "") {
      console.log("testtest");

      setShowAddCategory(false);
    } else {
      const data = {
        name: categoryName,
      };
      CategoryService.createCategory(data)
        .then(() => {
          setChangeCategories(changeCategories + 1);
          setCategoryName("");
          setShowAddCategory(false);
        })
        .catch((err) => console.log(err));
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.category_container}>
        <div className={styles.title}>
          <div>Categories</div>

          <div
            onClick={() => {
              setShowAddCategory(true);
            }}
            className={styles.add_container}
          >
            <img src={add} alt="" className={styles.add} />
          </div>
          {/* <div className={styles.add}>+</div> */}
        </div>

        {showAddCategory && (
          <input
            type="text"
            onBlur={handleAddNewCategory}
            onChange={handleChange}
            value={categoryName}
          />
        )}
        <div className={styles.content}>
          <SideBarItem title="All" number={todos.length} index={-1} />
          {categories.map((category: Category, index: number) => {
            return (
              <SideBarItem
                title={category.name}
                index={index}
                number={cateNums[index]}
                key={category.id}
              />
            );
          })}
        </div>
      </div>
      <div>
        <div className={styles.title}>Status</div>
        <div className={styles.content}>
          <SideBarItem title="In Progress" number={incompletedNum} index={-2} />
          <SideBarItem title="Completed" number={completedNum} index={-3} />
        </div>
      </div>
    </div>
  );
};

export default SideBarContainer;
