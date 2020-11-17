import Axios from "axios";
import axios from "axios";
import {GET_BOOK, GET_BOOKS, GET_ERRORS} from "./types";
import isEmpty from "../validation/is-empty";

export const getAllBooks = (page = 0) => dispatch => {

    axios
        .get("/api/book?page=" + page)
        .then(res => {
            dispatch({
                type: GET_BOOKS,
                payload: res.data
            })
        })
        .catch(err => {
            console.log(err)
        })
}

export const getBook = (id) => dispatch => {
    Axios.get("/api/book/" + id)
        .then((res) => {
            dispatch({
                type: GET_BOOK,
                payload: res.data
            })
        })
        .catch(err => {
            console.log(err);
        })
}

export const deleteBook = (id) => dispatch => {
    Axios.delete("/api/book/" + id)
        .then(() => {
            dispatch(getAllBooks())
        })
        .catch(err => {
            console.log(err);
        })
}

export const saveBook = (book, attach, history) => dispatch => {
    if (attach instanceof File) {
        const file = new FormData();
        file.append('file', attach)
        Axios.post("/api/img/upload", file)
            .then(res => {
                book.attachmentId = res.data[0]
                console.log(book)
                Axios.post("/api/book", book)
                    .then(() => {
                        dispatch({
                            type: GET_ERRORS,
                            payload: {}
                        });
                        history.push("/admin");
                    })
                    .catch(error => {
                        dispatch({
                            type: GET_ERRORS,
                            payload: error.response.data
                        });
                    })
            }).catch(err => {
            history.push("/wrong")
            console.log(err);
        })
    }
}
