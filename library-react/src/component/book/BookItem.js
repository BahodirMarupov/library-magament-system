import React, {Component} from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types'
import {connect} from 'react-redux';
import {setRent} from '../../actions/RentAction';
import '../../bookItem.css';

class BookItem extends Component {


    getRentButton = (isRent, validToken, user, id, amount) => {
        if (amount > 0) {
            if (validToken) {
                if (user.role[0].roleName === "ROLE_USER") {
                    if (isRent) {
                        return <div type="button" className="btn btn-primary mx-2 disabled">Rented</div>
                    } else {
                        return <button type="button" onClick={() => this.props.setRent(id)}
                                       className="btn btn-primary mx-2">Rent</button>
                    }
                }
                return "";
            }
            return <Link to="/login" className="btn btn-primary mx-2">Rent</Link>;
        }
        return ""
    }

    render() {
        const {book, isRent, user, validToken} = this.props;
        const imgCss = {
            hight: "100px"
        }
        return (
            <div className={"col-sm-3 mb-2"}>
                <div className="card ">
                    <img className="card-img-top img-fluid img-file-style"
                         src={"http://localhost/api/img/download/" + book.attachmentId}
                         alt="picture"/>
                    <div className="card-body">
                        <h5 className="card-title">{book.name}</h5>
                        <p className="card-text">Written by {book.author} ({book.writtenYear} year)</p>
                        <p className="card-text"> {book.amount} left in library</p>
                        <Link to={"/book/" + book.id} className="btn btn-success mx-2">View</Link>
                        {this.getRentButton(isRent, validToken, user, book.id, book.amount)}
                    </div>
                </div>
            </div>
        );
    }

}

BookItem.propTpes = {
    setRent: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({})

export default connect(mapStateToProps, {setRent})(BookItem);

