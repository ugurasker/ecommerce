import React from 'react'
import "./Product.css"
import {useStateValue} from "../../StateProvider";
import {Link} from "react-router-dom";

function Product({id, title, image, price, rating}) {
    const [{basket}, dispatch] = useStateValue();

    const addCurrentProduct = () => {
        dispatch({
            type: 'ADD_CURRENT_PRODUCT',
            payload: {
                id: id,
                title: title,
                image: image,
                price: price,
                rating: rating,
            },
        })
    }
    return (
        <div className="product">
            <div className="product__info">
                <p>{title}</p>
                <p className="product__price">
                    <small>$</small>
                    <strong>{price}</strong>
                </p>
                <div className="product__rating">
                    {Array(rating).fill().map((_, i) => (<p key={i}>⭐</p>))}
                </div>
            </div>
            <img src={image} alt=""/>
            <div className="row">
                <Link to={'/products/' + id}>
                    <button
                        className="btn-group mr-2 btn btn-sm btn-warning" onClick={addCurrentProduct}>See Details
                    </button>
                </Link>
            </div>
        </div>
    )
}

export default Product
