import React, { Component } from 'react';
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getAllBooks } from "../../actions/BookAction";
import isEmpty from "../../validation/is-empty";
import Book from "../book/Book";
import { getRents } from '../../actions/RentAction';

class Landing extends Component {


    componentDidMount() {
        this.props.getAllBooks(0);
        if (this.props.security.validToken) {
            this.props.getRents()
        }
    }

    onChange = (event, page) => {
        this.props.getAllBooks(page - 1);
    }

    render() {
        const books = this.props.books;
        const rents = this.props.rents;
        const {user,validToken} = this.props.security;
        let book;
        if (!isEmpty(books)) {
            book = <Book books={books} rents={rents} user={user} validToken={validToken} onChange={this.onChange.bind(this)} />
        }
        return (
            <div className={"container"}>
                <div className="row">
                    <div className="col-md">{book}</div>
                </div>

            </div>
        );
    }
}

Landing.propTypes = {
    books: PropTypes.object.isRequired,
    rents: PropTypes.array.isRequired,
    getAllBooks: PropTypes.func.isRequired,
    getRents: PropTypes.func.isRequired
}
const mapStateToProps = (state) => ({
    books: state.books.books,
    rents: state.rents.rents,
    security: state.security
})

export default connect(mapStateToProps, { getAllBooks, getRents })(Landing);