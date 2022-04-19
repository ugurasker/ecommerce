export const initialState = {
    basket: [],
    products: [],
    currentProduct: null,
    connectedUser: null
}

//Selector
export const getBasketTotal = (basket) =>
    basket?.reduce((amount, item) => item.price + amount, 0);

const reducer = (state, action) => {
    switch (action.type) {

        case 'ADD_CONNECTED_USER':
            return {
                ...state,
                connectedUser: action.payload
            }

        case 'ADD_TO_BASKET':
            const i = state.basket.findIndex(
                (basketItem) => ((basketItem.id == action.item.id) && (basketItem.color == action.item.color) && (basketItem.size == action.item.size))
            )
            if (i >= 0) {
                return {...state}
            } else {
                return {
                    ...state,
                    basket: [...state.basket, action.item]
                }
            }

        case 'ADD_CURRENT_PRODUCT':
            return {
                ...state,
                currentProduct: action.payload
            }
        case 'ADD_PRODUCTS':
            return {
                ...state,
                products: action.payload
            }

        case 'CLEAR_BASKET':
            return {
                ...state,
                basket: []
            }

        case'REMOVE_FROM_BASKET':
            const index = state.basket.findIndex(
                (basketItem) => basketItem.id === action.id
            )
            let newBasket = [...state.basket];

            if (index >= 0) {
                newBasket.splice(index, 1);

            } else {
                console.warn(
                    'Can not remove product (id: ${action.id}) as it is not in the basket'
                )
            }
            return {
                ...state,
                basket: newBasket
            }

        default:
            return state;
    }
}

export default reducer;
