import React, {Component} from 'react'
import PropTypes from 'prop-types'
import isEmpty from '../../validation/is-empty';
import RentItem from './RentItem';
import {connect} from "react-redux";
import {getRents} from "../../actions/RentAction";

class Rent extends Component {

    componentDidMount() {
        const username = this.props.match.params.username;
        const {user, validToken} = this.props.security
        if (validToken) {
            console.log(typeof username, user.username === username, typeof user.username)
            if (!(user.username === (username))) {
                this.props.history.push("/notFound")
            } else
                this.props.getRents()
        }
    }

    render() {

        const {rents} = this.props.rents
        let rentItems;
        if (!isEmpty(rents)) {
            rentItems = rents.map(item => <RentItem rent={item}/>)
        }


        return (
            <div className="container">
                <div className="row">
                    <div className="col-md">
                        <table className="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Author</th>
                                <th scope="col">writter year</th>
                                <th scope="col">rented time</th>
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
        )
    }
}

Rent
    .propTypes = {
    rents: PropTypes.array.isRequired,
    getRents: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired,

}

const
    mapStateToProps = (state) => ({
        rents: state.rents,
        security: state.security
    })

export default connect(mapStateToProps, {getRents})(Rent)
;