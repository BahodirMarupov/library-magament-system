import React, {Component} from 'react'
import PropTypes from "prop-types";
import {deleteRent} from "../../actions/RentAction";
import {connect} from "react-redux";
import Moment from "react-moment"
class RentItem extends Component {
    removeRent(id) {
        this.props.deleteRent(id)
    }

    render() {
        const {rent} = this.props;
        return (
            <tr>
                <td>{rent.bookDto.name}</td>
                <td>{rent.bookDto.author}</td>
                <td>{rent.bookDto.writtenYear}</td>
                <td><Moment format="YYYY/MM/DD">{rent.createdAt}</Moment></td>
                <td>
                    <button className="btn btn-danger" type="button" onClick={(event)=> this.removeRent(rent.id)}>give
                        back
                    </button>
                </td>
            </tr>
        )
    }
}

RentItem.propTypes = {
    deleteRent: PropTypes.func.isRequired
}
export default connect(null, {deleteRent})(RentItem)
