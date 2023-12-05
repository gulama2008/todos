import "./App.css";
import SideBarContainer from "./containers/SideBarContainer/SideBarContainer";
import TodosContainer from "./containers/TodosContainer/TodosContainer";
import TodosContextProvider from "./context/TodosContextProvider";

function App() {
  return (
    <TodosContextProvider>
      <div id="container">
        <SideBarContainer />
        <TodosContainer />
      </div>
    </TodosContextProvider>
  );
}

export default App;
