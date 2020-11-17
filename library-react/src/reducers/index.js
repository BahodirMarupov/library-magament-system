import { combineReducers } from "redux";
import bookReducer from "./bookReducer";
import errorReducers from "./errorReducers";
import rentReducer from "./rentReducer";
import securityReducers from "./securityReducers";

export default combineReducers({
  errors: errorReducers,
  rents: rentReducer,
  books: bookReducer,
  security: securityReducers
});
