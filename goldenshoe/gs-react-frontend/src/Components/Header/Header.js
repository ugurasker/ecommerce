import React from 'react'
import './Header.css'
import logo from "../../images/logo2.jpg";
import SearchIcon from '@mui/icons-material/Search';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import {Link} from "react-router-dom";
import {useStateValue} from "../../StateProvider";
import service from "../../service/HttpService";
import {useHistory} from "react-router";
import {toast, ToastContainer} from "react-toastify";

function Header() {

    const [{basket}, dispatch] = useStateValue();
    let history = useHistory();

    const fetchAllproductsByCategory = (event) => {
        let category = event.target.value;
        if (category != '')
            service.getProductsByCategory(category)
                .then((res) => {
                    dispatch({
                        type: 'ADD_PRODUCTS',
                        payload: res.data,
                    })
                })
                .catch((err) => {
                    console.log('errorrrrr', err)
                });
    }

    const logout = () => {
        localStorage.removeItem("connected");
        toast.success("logout successfully", {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 1000
        });
        setTimeout(() => {
            history.push('/')
        }, 1000);
    }

    return (
        <div className="header">
            <Link to="/">
                <img className="header__logo" src={logo} alt=""/>
            </Link>

            <div className="header__search">
                <input className="header__searchInput" type="text"/>
                <SearchIcon className="header__searchIcon"/>
            </div>
            <div className="mr-2 ml-2">
                <select className="custom-select custom-select-sm" onChange={fetchAllproductsByCategory}>
                    <option defaultValue='' value=''>Select a Category</option>
                    <option value='men'>Men</option>
                    <option value='women'>Women</option>
                    <option value='kids'>Kids</option>
                </select>
            </div>

            <div className="header__nav">
                <Link to="/Login">
                    <div className="header__option">
               <span className="header__optionLineOne">
               Hello Guest
               </span>
                        <span className="header__optionLineTwo">
               Sign In
               </span>
                    </div>
                </Link>
                <div className="header__option">
            <span className="header__optionLineOne">
               Returns
               </span>
                    <span className="header__optionLineTwo">
               & Orders
               </span>
                </div>
                <div className="header__option">
            <span className="header__optionLineOne">
               My
               </span>
                    <span className="header__optionLineTwo">
               Account
               </span>

                </div>
                <div className="header__option logout" onClick={logout}>
                    <span className="header__optionLineOne"> </span>
                    <span className="header__optionLineTwo"> Logout </span>
                </div>
                <Link to="/checkout">
                    <div className="header__optionBasket">
                        <ShoppingBasketIcon/>
                        <span className="header__optionLineTwo header__basketCount">
                   {basket?.length}
               </span>
                    </div>
                </Link>

            </div>
            <ToastContainer/>
        </div>
    )
}

export default Header
