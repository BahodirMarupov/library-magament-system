import './App.css';
import jwt_decode from "jwt-decode";
import { Provider } from 'react-redux'
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Header from "./component/layout/Header";
import Footer from "./component/layout/Footer";
import store from "./store";
import setJWToken from "./utils/setJWToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/AuthAction";
import Login from "./component/auth/Login";
import Register from "./component/auth/Register";
import Landing from "./component/layout/Landing";
import PrivateRoute from "./component/common/PrivateRoute";
import Rent from './component/rent/Rent';
import BookInfo from './component/book/BookInfo';
import NotFound from "./component/exceptions/NotFound";
import Admin from "./component/admin/Admin";
import AddBook from './component/admin/AddBook';
import Wrong from "./component/exceptions/Wrong";

const token = localStorage.jwtToken;
if (token) {
    setJWToken(token);
    const decoded = jwt_decode(token)
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decoded
    })

    const currentTime = Date.now() / 1000;

    if (currentTime > decoded.exp) {
        store.dispatch(logout());
        window.location.href = "/";
    }
}

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <div className="App">
                    <Header />
                    <Route exact path="/" component={Landing} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/register" component={Register} />
                    <Route exact path="/add-book" component={AddBook} />
                    <Route exact path="/book/:id" component={BookInfo} />
                    <Route exact path="/notFound" component={NotFound} />
                    <Route exact path="/wrong" component={Wrong} />
                    <Switch>
                        <PrivateRoute exact path="/user/:username" component={Rent} />
                    </Switch>
                    <Switch>
                        <PrivateRoute exact path="/admin" component={Admin} />
                    </Switch>
                    {/*<Route exact path="/addProject" component={AddProject} />*/}
                    {/*<Route exact path="/updateProject/:id" component={UpdateProject} />*/}
                    {/*<Route exact path="/projectBoard/:id" component={ProjectBoard} />*/}
                    {/*<Route exact path="/addProjectTask/:id/" component={AddProjectTask} />*/}
                    {/*<Route exact path="/updateProjectTask/:id/:task_id" component={UpdateProjectTask} />*/}
                    <Footer />
                </div>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
