import styles from "./SideBarItem.module.scss"
export interface SideBarItemProps { 
    title: string,
    number:number
}

const SideBarItem = ({ title,number}:SideBarItemProps) => {
  return (
      <div className={styles.container}>
          <div>{title}</div>
          <div>{ number}</div>
    </div>
  )
}

export default SideBarItem