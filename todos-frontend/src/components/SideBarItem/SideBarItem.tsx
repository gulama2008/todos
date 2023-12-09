import { useContext } from "react"
import styles from "./SideBarItem.module.scss"
import { TodosContext } from "../../context/TodosContextProvider"
export interface SideBarItemProps { 
    title: string,
    number:number
}

const SideBarItem = ({ title, number }: SideBarItemProps) => {
  const { } = useContext(TodosContext);
  return (
      <div className={styles.container}>
          <div>{title}</div>
          <div>{ number}</div>
    </div>
  )
}

export default SideBarItem