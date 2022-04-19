import axiosInstance from "./axiosInstance";

const BASE_URL = "http://localhost:8090";

class HttpService {

    login(userInfo) {
        return axiosInstance.post(BASE_URL + "/auth/login", userInfo);
    }

    register(userInfo) {
        return axiosInstance.post(BASE_URL + "/auth/register", userInfo);
    }

    getOrders() {
        return axiosInstance.get(BASE_URL + "/orders")
    }

    getOrdersByCustomerId(customerId) {
        return fetch(BASE_URL + `?customer_id=${customerId}`)
            .then(res => res.json());
    }

    getProducts() {
        return axiosInstance.get(BASE_URL + "/products/all")
    }

    getProductsByCategory(cat) {
        return axiosInstance.get(BASE_URL + "/products/all?category=" + cat)
    }

    getProductColors(id) {
        return axiosInstance.get(BASE_URL + "/productVariant/colors?product_id=" + id)
    }

    getProductSizeByColors(id, color) {
        return axiosInstance.get(BASE_URL + "/productVariant/sizes?product_id=" + id + "&color=" + color);
    }

    getProductQuantityBySizeAndColors(id, color, size) {
        return axiosInstance.get(BASE_URL + "/productVariant/quantity?product_id=" + id + "&color=" + color + "&size=" + size);
    }

    /* postOrder(order) {
         return fetch(BASE_URL + "/orders", {
             method: 'POST',
             body: JSON.stringify(order),
             headers: {
                 'Content-Type': 'application/json'
             }
         })
             .then(res => res.json());
     }*/

    updateOrderDispatchStatus(orderId, updatedOrder) {
        return fetch(BASE_URL + `/${orderId}/dispatched`, {
            method: 'PUT',
            body: JSON.stringify(updatedOrder),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => res.json());
    }

    deleteUser(id) {
        return axiosInstance.delete(BASE_URL + "/user/delete/" + id);
    }
}

export default new HttpService();
