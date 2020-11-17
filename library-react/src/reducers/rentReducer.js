import {DELETE_RENT, GET_RENT, GET_RENTS,} from "../actions/types";

const initialState = {
    rents: [],
    rent: {},
};

export default (state = initialState, {type, payload}) => {
    switch (type) {
        case GET_RENTS:
            return {...state, rents: payload};

        case GET_RENT:
            return {...state, rent: payload};

        case DELETE_RENT:
            return {
                ...state,
                rents: state.rents.filter(
                    (e) => e.id !== payload
                ),
            };
        default:
            return state;
    }
};
  