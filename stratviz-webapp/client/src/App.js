import Graph from "./components/Graph";
import Header from "./components/Header";
import UploadCSV from "./components/UploadCSV"

function App() {
  return (
    <div className="App">
      <Header />
      <p>This is our Application</p>
      <div className="sameRow">
        <Graph />
        <UploadCSV />
      </div>
      
    </div>
  );
}

export default App;
