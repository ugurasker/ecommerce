import React from "react"
import './App.css';
import Header from './Components/Header/Header';
import Home from "./Components/Home/Home";
import Checkout from "./Components/Checkout/Checkout"
import Login from "./Components/Login/Login"
import Register from "./Components/Register/Register"
import {BrowserRouter, Route, Switch} from "react-router-dom";
import {Redirect} from "react-router";
import ProductDetails from "./Components/ProductsDetails/productDetails";
import {useStateValue} from "./StateProvider";

function App() {
    const [{currentProduct, connectedUser}, dispatch] = useStateValue();

    return (
        <BrowserRouter>
            <div className="App">
                <Header/>
                <Switch>
                    <Route path="/Login">
                        <Login/>
                    </Route>
                    <Route exact path="/">
                        <Home/>
                    </Route>
                    <Route exact path="/products/:id">
                        {(currentProduct == null) ? <Redirect to="/"/> : <ProductDetails/>}
                    </Route>
                    <Route path="/checkout">
                        <Checkout/>
                    </Route>
                    <Route path="/Register">
                        <Register/>
                    </Route>
                </Switch>
            </div>
        </BrowserRouter>
    );

}

export default App;
