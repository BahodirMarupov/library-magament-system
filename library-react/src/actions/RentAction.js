import Axios from "axios"
import {GET_RENT, GET_RENTS} from "./types"
import {getAllBooks} from "./BookAction";

export const getRents = () => dispatch => {
    Axios
        .get("http://localhost/api/rent/myBooks")
        .then(res => {
            dispatch({
                type: GET_RENTS,
                payload: res.data
            })
        }).catch(e => {
        console.log(e);
    })
}
export const getRentsForAdmin = () => dispatch => {
    Axios
        .get("api/rent")
        .then(res => {
            dispatch({
                type: GET_RENTS,
                payload: res.data
            })
        }).catch(e => {
        console.log(e);
    })
}

export const deleteRent = (id) => dispatch => {
    Axios.delete("/api/rent/" + id).then(() => {
        dispatch(getRents())
    }).catch(
        e => {
            console.log(e);
        }
    )
}

export const getRent = (id) => dispatch => {
    Axios.get("/api/rent/" + id).then(res =>
        dispatch({
            type: GET_RENT,
            payload: res.data
        })).catch(e => {
        console.log(e);
    })
}

export const setRent = (id) => dispatch => {
    Axios.post("/api/rent/" + id).then(() => {
        dispatch(getAllBooks());
        dispatch(getRents())
    }).catch(
        e => {
            console.log(e);
        }
    )
}