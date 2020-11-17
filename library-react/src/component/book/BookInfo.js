import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { getBook } from '../../actions/BookAction'

class BookInfo extends Component {
    static propTypes = {
        getBook: PropTypes.func.isRequired,
        book: PropTypes.object.isRequired
    }

    componentDidMount(){
        this.props.getBook(this.props.match.params.id)
    }

    render() {
        const {book}=this.props;
        return (
            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <img className="card-img-top img-fluid mr-3 mb-3"
                            src={"http://localhost/api/img/download/" + book.attachmentId}
                            alt="picture" />
                    </div>
                    <div className="col-8">
                    <h3 className="text-center">{book.name}</h3>
                    <p className="bold text-center">Written by {book.author} in {book.writtenYear}</p>
                    <p>   {book.description}</p>
                    </div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    book: state.books.book
})

export default connect(mapStateToProps, { getBook })(BookInfo);