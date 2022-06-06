import Graph from "./components/Graph";
import Header from "./components/Header";
import Configuration from "./components/Configuration";

function App() {
  return (
    <div className="App">
      <Header />
      <p>This is our Application</p>
      <div className="Row">
      <Graph />
      <Configuration />      
      </div>
    </div>
  );
}

export default App;
