import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Home from "./components/Home";
import { Container } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import Register from "./components/Register";
import Login from "./components/Login";
import { MyCartContext, MyDispatchContext, MyUserContext } from "./configs/Contexts";
import { useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import Cart from "./components/Cart";
import MyCartReducer from "./reducers/MyCartReducer";

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [cart, cartDispatch] = useReducer(MyCartReducer, 0);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <MyCartContext.Provider value={[cart, cartDispatch]}>
            <BrowserRouter>
              <Header />
            
              <Container>
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/register" element={<Register />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/cart" element={<Cart />} />
                </Routes>
              </Container>

              <Footer />
            </BrowserRouter>
          </MyCartContext.Provider>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
    
  );
}

export default App;