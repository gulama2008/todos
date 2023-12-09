import { useContext } from "react"
import styles from "./SideBarItem.module.scss"
import { TodosContext } from "../../context/TodosContextProvider"
export interface SideBarItemProps { 
  title: string,
  index?:number,
    number:number
}

const SideBarItem = ({ title, number, index }: SideBarItemProps) => {
  const { activeSideBarItem, setActiveSideBarItem } = useContext(TodosContext);
  const handleClick = () => { 
    setActiveSideBarItem(index)
  }
  let containerClasses = styles.container;
  if (activeSideBarItem === index) {
    containerClasses += ` ${styles.active}`;
  }
  return (
    <div className={containerClasses} onClick={handleClick}>
      <div>{title}</div>
      <div>{number}</div>
    </div>
  );
}

export default SideBarItem