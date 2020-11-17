import React, {Component} from "react";
import {Link} from "react-router-dom";
import {connect} from 'react-redux'
import PropTypes from 'prop-types'
import {logout} from "../../actions/AuthAction";

class Header extends Component {

    logout = () => {
        this.props.logout();
        window.location.href = "/"
    }

    signUpChecking = (security) => {

        const {user, validToken} = security

        if (validToken) {
            if (user.role[0].roleName==="ROLE_USER") {
                return (<Link className="nav-link" to={`/user/${user.username}`}>
                        <i className="fas fa-user-circle mr-1"></i>
                        My books
                    </Link>
                )
            }
            else{
                return <Link className="nav-link" to="/admin">Rental management</Link>
            }

        } else {
            return (<Link className="nav-link " to="/register">
                Sign Up
            </Link>)
        }
    }

    signInChecking = (security) => {

        if (security.validToken) {
            return (
                <Link className="nav-link bg-red" onClick={this.logout} to="logout">
                    Logout
                </Link>
            )
        }

        return (
            <Link className="nav-link" to="/login">
                Login
            </Link>
        )
    }

    render() {
        return (
            <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
                <div className="container col-7">
                    <Link className="navbar-brand bold" to="/">
                        LIBRARY
                    </Link>

                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                {this.signUpChecking(this.props.security)}
                            </li>
                            <li className="nav-item">
                                {this.signInChecking(this.props.security)}
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

Header.propTypes = {
    security: PropTypes.object.isRequired,
    logout: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({
    security: state.security
})

export default connect(mapStateToProps, {logout})(Header);
