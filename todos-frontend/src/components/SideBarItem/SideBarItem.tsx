import { useContext } from "react";
import styles from "./SideBarItem.module.scss";
import { Category, TodosContext } from "../../context/TodosContextProvider";
import { CategoryService } from "../../services/categories-service";
import delete1 from "../../assets/delete1.png"
export interface SideBarItemProps {
  category?: Category;
  title: string;
  index?: number;
  number: number;
}

const SideBarItem = ({ category, title, number, index }: SideBarItemProps) => {
  const {
    activeSideBarItem,
    setActiveSideBarItem,
    changeCategories,
    setChangeCategories,
  } = useContext(TodosContext);
  const handleClick = () => {
    setActiveSideBarItem(index);
  };

  let containerClasses = styles.container;
  if (activeSideBarItem === index) {
    containerClasses += ` ${styles.active}`;
  }
  const handleDelete = () => {
    CategoryService.deleteCategory(category!.id)
      .then(() => {
        setActiveSideBarItem(-1);
        setChangeCategories(changeCategories - 1);
      })
      .catch((e) => console.log(e));
  };
  return (
    <div className={containerClasses} onClick={handleClick}>
      <div className={styles.title}>{title}</div>
      <div className={styles.number}>{number}</div>
      {category && (
        <img src={delete1} onClick={handleDelete} className={ styles.delete} />
      )}
    </div>
  );
};

export default SideBarItem;
