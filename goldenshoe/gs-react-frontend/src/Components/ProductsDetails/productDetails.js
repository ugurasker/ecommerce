import React, {useEffect, useState} from 'react'
import {useStateValue} from "../../StateProvider";
import service from "../../service/HttpService";
import {Link} from "react-router-dom";

function ProductDetails() {

    const [{currentProduct}, dispatch] = useStateValue();

    const [colors, setColors] = useState([]);
    const [sizes, setSizes] = useState([]);
    const [Quantity, setQuantity] = useState([]);
    const [selectedColor, setSelectedColor] = useState([]);
    const [selectedSize, setSelectedSize] = useState([]);

    const getProductColors = () => {
        service
            .getProductColors(currentProduct.id)
            .then((res) => {
                setColors(res.data);
            })
            .catch((err) => {
                console.log('erreur')
            });
    }
    useEffect(() => {
        getProductColors()
    }, [true]);

    const addToBasket = () => {
        dispatch({
            type: 'ADD_TO_BASKET',
            item: {
                id: currentProduct.id,
                title: currentProduct.title,
                image: currentProduct.image,
                color: selectedColor,
                size: selectedSize,
                stock: Quantity,
                quantity: 0,
                price: currentProduct.price,
                rating: currentProduct.rating,
            },
        })
    }
    const changeColor = (event) => {
        let color = event.target.value;
        setSelectedColor(color);
        setSelectedSize('');
        service.getProductSizeByColors(currentProduct.id, color)
            .then((res) => {
                setSizes(res.data)
            })
            .catch((err) => {
                console.log('erreur')
            });
    }

    const changeSize = (event) => {
        let size = event.target.value;
        setSelectedSize(size);
        service.getProductQuantityBySizeAndColors(currentProduct.id, selectedColor, size)
            .then((res) => {
                setQuantity(res.data)
            })
            .catch((err) => {
                console.log('erreur')
            });
    }
    return (
        <div className="container">
            <h3 className="col-9 text-center mt-2">Details of product {currentProduct.title}</h3>
            <div className="row">
                <div className="col-4">
                    <img src={currentProduct.image} className="img-fluid" alt=""/>
                </div>
                <div className="col-5">
                    <form>
                        <div className="form-group ">
                            <div className="form-group ">
                                <label htmlFor="Color">Color</label>
                                <select className="form-control form-control-sm" onChange={changeColor} id="Color">
                                    <option value="">Select a Color</option>
                                    {colors?.map((c, index) =>
                                        <option value={c} key={index}>{c}</option>)}
                                </select>
                            </div>
                            {
                                (selectedColor == "") ? null :
                                    <div className="form-group ">
                                        <label htmlFor="Size">Size</label>
                                        <select className="form-control form-control-sm" onChange={changeSize}
                                                id="Size">
                                            <option value="">Select a Size</option>
                                            {sizes?.map((s, index) =>
                                                <option value={s} key={index}>{s}</option>)}
                                        </select>
                                    </div>
                            }
                        </div>
                    </form>
                    {
                        (selectedSize == "") ? null :
                            <div className="row">
                                <button className="col-5 btn-sm mr-2 btn mb-2 yellow"
                                        onClick={addToBasket}>Add to Basket
                                </button>
                                <Link to="/">
                                    <button className="col- btn btn-sm mb-2 gray">Continue shopping</button>
                                </Link>
                            </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default ProductDetails
