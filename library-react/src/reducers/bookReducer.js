import {GET_BOOK, GET_BOOKS,} from "../actions/types";

const initialState = {
    books: {},
    book: {},
};

export default (state = initialState, {type, payload}) => {
    switch (type) {
        case GET_BOOKS:
            return {...state, books: payload};

        case GET_BOOK:
            return {...state, book: payload};

        default:
            return state;
    }
};
  