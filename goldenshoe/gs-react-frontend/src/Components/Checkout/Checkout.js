import React from 'react'
import "./Checkout.css"
import Exclusive1 from "../../images/exclusive2.jpg"
import Subtotal from '../Subtotal/Subtotal'
import {useStateValue} from "../../StateProvider"
import CheckoutProduct from '../CheckoutProduct/CheckoutProduct';

function Checkout() {
    const [{basket}, dispatch] = useStateValue();

    return (
        <div className="container mt-4">
            <div className="row">
                <img
                    className="checkout__ad"
                    src={Exclusive1}
                    alt=""/>
            </div>
            <div className="row">
                <div className="col-sm-9">
                    <h2 className="checkout__title">
                        Your shopping Basket
                    </h2>
                    <div className="row">
                        {basket.map(item => (item.id ? <CheckoutProduct
                                key={item.id}
                                id={item.id}
                                title={item.title}
                                color={item.color}
                                size={item.size}
                                quantity={item.quantity}
                                stock={item.stock}
                                image={item.image}
                                price={item.price}
                                rating={item.rating}
                            /> : null
                        ))}
                    </div>
                </div>
                <div className="col-sm-3 mt-5">
                    <Subtotal/>
                </div>
            </div>
        </div>)
}

export default Checkout
