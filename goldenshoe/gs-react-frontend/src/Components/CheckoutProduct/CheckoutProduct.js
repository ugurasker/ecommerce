import React, {useState} from 'react'
import "./CheckoutProduct.css"
import {useStateValue} from "../../StateProvider"

function CheckoutProduct({id, image, title, stock, color, size, price, rating}) {

    const [{basket}, dispatch] = useStateValue();
    const [Quantity, setQuantity] = useState([]);


    const removeFromBasket = () => {
        dispatch({
            type: "REMOVE_FROM_BASKET",
            id: id,
        })
    }

    const addToBasket = (q) => {
        dispatch({
            type: 'ADD_TO_BASKET',
            item: {
                id: id,
                title: title,
                image: image,
                color: color,
                size: size,
                stock: stock,
                quantity: parseInt(q, 10),
                price: (price * q),
                rating: rating,
            }
        })
    }

    const changeQuantity = (event) => {
        let quantity = event.target.value;
        addToBasket(quantity);
    }
    return (
        <div className="checkoutProduct col-sm-6">
            <img className="checkoutProduct__image" src={image}/>

            <div className="checkoutProduct__info">
                <p className="checkoutProduct__title">{title}</p>
                <p className="checkoutProduct__details">Color : {color}</p>
                <p className="checkoutProduct__details">Size : {size}</p>
                <div className="form-group ">
                    <label htmlFor="Quantity">Select a quantity </label>
                    <select className="form-control form-control-sm" onChange={changeQuantity} id="Quantity">
                        <option value="">Select a Quantity</option>
                        {Array.from(Array(stock), (e, i) =>
                            <option key={i}>{i + 1}</option>)}
                    </select>
                </div>
                <p className="checkoutProduct__price">
                    <small>€</small>
                    <strong>{price}</strong>
                </p>
                <div className="checkoutProduct__rating">
                    {Array(rating).fill().map((_, i) => (<p key={i}>⭐</p>))}
                </div>
                <button className="btn btn-warning" onClick={removeFromBasket}>Remove from Basket</button>
            </div>
        </div>
    )
}

export default CheckoutProduct
