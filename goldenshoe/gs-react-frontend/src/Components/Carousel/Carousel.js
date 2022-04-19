import {useEffect, useState} from "react";
import Banner1 from "../../images/carousel/1.png";
import Banner2 from "../../images/carousel/2.png";
import Banner3 from "../../images/carousel/3.png";
import {Carousel} from "react-bootstrap";
import "./Carousel.css";


const AppCarousel = () => {

    const [products, setproducts] = useState([]);

    useEffect(() => {

    }, [false]);


    return (
        <Carousel interval={2000} className="mt-2">
            <Carousel.Item>
                <img
                    className="d-block w-100 carousel"
                    src={Banner1}
                    alt="First slide"
                />
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100 carousel"
                    src={Banner2}
                    alt="Second slide"
                />
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100 carousel"
                    src={Banner3}
                    alt="Third slide"
                />
            </Carousel.Item>
        </Carousel>
    );
};

export default AppCarousel;
