import React from 'react'
import "./Subtotal.css"
import CurrencyFormat from 'react-currency-format'
import {useStateValue} from "../../StateProvider"
import {getBasketTotal} from "../../reducer";
import {toast, ToastContainer} from "react-toastify";
import {useHistory} from "react-router-dom";

function Subtotal() {
    const [{basket, connectedUser}, dispatch] = useStateValue();
    let history = useHistory();

    const proceed = () => {
        console.log(basket)
        if (localStorage.getItem("connected") == "true" || connectedUser) {
            toast.success('order created successfully', {
                position: "top-center",
                autoClose: 3000
            });
            setTimeout(() => {
                history.push('/')
            }, 3000);
            clearBasket();
        } else {
            toast.error('you must Logged in', {
                position: "top-center",
                autoClose: 1500
            });
            setTimeout(() => {
                history.push('/login')
            }, 1500);
        }
    }

    const clearBasket = () => {
        dispatch({
            type: 'CLEAR_BASKET',
            item: {},
        })
    }
    return (
        <div className="subtotal">
            <ToastContainer/>
            <CurrencyFormat
                renderText={(value) => (
                    <>
                        <p>
                            Subtotal({basket.length} items):
                            <strong> {value}</strong>
                        </p>
                        <small className="subtotal__gift">
                            <input type="checkbox"/>This order
                            contains a gift
                        </small>
                    </>
                )}
                decimalScale={2}
                value={getBasketTotal(basket)}
                displayType={"text"}
                thousandSeparator={true}
                prefix={"€"}
            />

            <button className="btn btn-warning mt-2" onClick={proceed}>Proceed to Checkout</button>

        </div>
    )
}

export default Subtotal
