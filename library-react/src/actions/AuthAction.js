import setJWToken from "../utils/setJWToken";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import jwt_decode from "jwt-decode";
import axios from "axios";

export const login = (userData, history) => dispatch => {
    axios
        .post("/api/auth/login", userData)
        .then(res => {
            const {token,type} = res.data;
            // Set token to ls
            localStorage.setItem("jwtToken", type+token);
            // Set token to Auth header
            setJWToken(type+token);
            // Decode token to get user data
            const decode = jwt_decode(token);
            // Set current user
            dispatch(setCurrentUser(decode));
            history.push("/")

        })
        .catch(err =>
            dispatch({
                type: GET_ERRORS,
                payload: err.response.data
            })
        );
};
export const setCurrentUser = decode => {
    return {
        type: SET_CURRENT_USER,
        payload: decode
    };
};
export const logout = () => dispatch => {
    // Remove token from localStorage
    localStorage.removeItem("jwtToken");
    // Remove auth header from future request
    setJWToken(false);
    // Set current user to {} which will set isAuthenticated to false
    dispatch(setCurrentUser({}));
};

export const createNewUser = (user, history) => dispatch => {
    axios
        .post("/api/auth/register", user)
        .then(res => {
            const {token,type} = res.data;
            // Set token to ls
            localStorage.setItem("jwtToken", type+token);
            // Set token to Auth header
            setJWToken(type+token);
            // Decode token to get user data
            const decode = jwt_decode(token);
            console.log(decode);
            // Set current user
            dispatch(setCurrentUser(decode));
            history.push("/")
        })
        .catch(err => {
            console.log(err)
            dispatch({
                type: GET_ERRORS,
                payload: err.response.data
            })
        })

}
