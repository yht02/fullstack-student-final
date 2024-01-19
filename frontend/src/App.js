import Navbar from "./components/Navbar.jsx";
import CreateStudent from "./students/CreateStudent.jsx";
import EditStudent from "./students/EditStudent.jsx";
import ViewStudent from "./students/ViewStudent.jsx";
import Home from "./pages/Home.jsx";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";



function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route exact path="/createstudent" element={<CreateStudent />} />
          <Route exact path="/editstudent/:id" element={<EditStudent />} />
          <Route exact path="/viewstudent/:id" element={<ViewStudent />} />
        </Routes>
      </Router>

    </div>
  );
}

export default App;
