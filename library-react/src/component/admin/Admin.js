import React, {Component} from 'react';
import PropTypes from "prop-types"
import {connect} from "react-redux";
import {deleteRent, getRentsForAdmin} from "../../actions/RentAction";
import isEmpty from "../../validation/is-empty";
import Moment from "react-moment";
import {Link} from "react-router-dom";

class Admin extends Component {
    removeRent(id) {
        this.props.deleteRent(id)
    }

    componentDidMount() {
        this.props.getRentsForAdmin()
    }

    render() {
        const {rents} = this.props.rents
        let rentItems;
        if (!isEmpty(rents)) {
            rentItems = rents.map(rent => (
                <tr>
                    <td>{rent.user.username}</td>
                    <td>{rent.bookDto.name}</td>
                    <td>{rent.bookDto.author}</td>
                    <td>{rent.bookDto.writtenYear}</td>
                    <td><Moment format="YYYY/MM/DD">{rent.createdAt}</Moment></td>
                    <td>
                        <button className="btn btn-danger" type="button"
                                onClick={(event) => this.removeRent(rent.id)}>Take
                            back
                        </button>
                    </td>
                </tr>
            ))
        }

        return (
            <div className={"container"}>
                <div className="row">
                    <div className="col-md">
                        <Link to="/add-book" className={"btn btn-info"}>Add Book</Link>
                        <h1 className={"text-center"}>User Rents</h1>
                        <table className="table table-hover text-center">
                            <thead>
                            <tr>
                                <th scope="col">User</th>
                                <th scope="col">Book name</th>
                                <th scope="col">Book Author</th>
                                <th scope="col">Written year</th>
                                <th scope="col">Rented time</th>
                                <th scope="col"/>
                            </tr>
                            </thead>
                            <tbody>
                            {rentItems}
                            </tbody>
                        </table>
                    </div>
                </div>


            </div>
        );
    }
}

Admin.propTypes = {
    rents: PropTypes.array.isRequired,
    getRentsForAdmin: PropTypes.func.isRequired,
    deleteRent: PropTypes.func.isRequired,

}

const mapStateToProps = (state) => ({
    rents: state.rents
})
export default connect(mapStateToProps, {getRentsForAdmin, deleteRent})(Admin);