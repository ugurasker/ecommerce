import {useEffect} from "react";
import Product from "../Product/Product";
import "./Home.css";
import AppCarousel from "../Carousel/Carousel";
import {useStateValue} from "../../StateProvider";
import httpService from "../../service/HttpService";

const Home = () => {

    const [{products}, dispatch] = useStateValue();

    const fetchAllproducts = async () => {
        httpService.getProducts().then(
            res => {
                dispatch({
                    type: 'ADD_PRODUCTS',
                    payload: res.data,
                })
            }).catch(
            err => {
                console.log("error")
            })
    };

    useEffect(() => {
        fetchAllproducts();
    }, [false]);


    return (
        <div className="home">
            <div className="container-fluid">
                <AppCarousel></AppCarousel>
                <div className="row">
                    {products?.map((p, index) => {
                        return (
                            <div key={p.id} className="col-3">
                                <Product id={p.id} title={p.brand} price={p.price} image={p.imageUrl} rating={5}/>
                            </div>
                        );
                    })}
                </div>
                {products.length == 0 ? <h1 className="text-center">No products found</h1> : null}
            </div>
        </div>
    );
};

export default Home;
