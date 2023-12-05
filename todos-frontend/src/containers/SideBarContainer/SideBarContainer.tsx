import { useContext, useEffect, useState } from "react"
import styles from "./SideBarContainer.module.scss"
import { Category, Todo, TodosContext } from "../../context/TodosContextProvider"
import SideBarItem from "../../components/SideBarItem/SideBarItem";

const SideBarContainer = () => {
  const { categories, todos, incompletedNum, completedNum } =
    useContext(TodosContext);
  const [cateNums, setCateNums] = useState<number[]>([]);
  useEffect(() => { 
    const cateNums = categories.map((category: Category) => { 
      const num = todos.reduce((a: number, b: Todo) => {
        if (b.category.id === category.id) { 
          return ++a;
        }
        return a;
      }, 0)
      return num;
    })
    setCateNums(cateNums);
  },[todos])
  return (
    <div className={styles.container}>
      <div>
        <div>Categories</div>
        {categories.map((category: Category,index:number) => { return <SideBarItem title={category.name } number={cateNums[index]} key={category.id}/>})}
      </div>
      <div>
        <div>Status</div>
        <SideBarItem title="In Progress" number={incompletedNum}/>
        <SideBarItem title="Completed" number={completedNum}/>
      </div>
    </div>
  );
}

export default SideBarContainer