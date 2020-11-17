import React, {Component} from "react";
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import {saveBook} from "../../actions/BookAction";
import PropTypes from "prop-types"

class AddBook extends Component {
    constructor() {
        super();
        this.state = {
            name: "",
            author: "",
            description: "",
            writtenYear: "",
            amount: 1,
            attachmentId: "",
            file: {},
            errors: {},
        };
    }

    onSubmit = (e) => {
        console.log("onsubmit")
        e.preventDefault();
        let newBook = {
            name: this.state.name,
            author: this.state.author,
            description: this.state.description,
            writtenYear: this.state.writtenYear,
            amount: this.state.amount,
            attachmentId: this.state.attachmentId
        }
        this.props.saveBook(newBook, this.state.file, this.props.history)
    };

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    };
    onFileChange = (e) => {
        this.setState({file: e.target.files[0]})
    }

    componentWillReceiveProps(newProps) {
        if (newProps.errors) {
            this.setState({errors: newProps.errors})
        }
    }

    render() {
        return (
            <div>
                <div className="add-PBI">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-6 m-auto">
                                <Link
                                    to={`/admin`}
                                    className="btn btn-light"
                                >
                                    Go back
                                </Link>
                                <h4 className="display-4 text-center">Add book</h4>
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        <input
                                            required
                                            type="text"
                                            className={`${this.state.errors.name && this.state.name === "" ? "form-control form-control-lg is-invalid" : "form-control form-control-lg"}`}
                                            name="name"
                                            placeholder="Book name"
                                            value={this.state.name}
                                            onChange={this.onChange}
                                        />
                                        <div className="invalid-feedback">{this.state.errors.name}</div>
                                    </div>
                                    <div className="form-group">
                                        <input
                                            required
                                            type="text"
                                            className={`${this.state.errors.author && this.state.author === "" ? "form-control form-control-lg is-invalid" : "form-control form-control-lg"}`}
                                            name="author"
                                            placeholder="Author"
                                            value={this.state.author}
                                            onChange={this.onChange}
                                        />
                                        <div className="invalid-feedback">{this.state.errors.author}</div>
                                    </div>
                                    <div className="form-group">
                                        <textarea
                                            required
                                            className="form-control form-control-lg"
                                            placeholder="Description"
                                            name="description"
                                            value={this.state.description}
                                            onChange={this.onChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input
                                            required
                                            type="date"
                                            className="form-control form-control-lg"
                                            name="writtenYear"
                                            value={this.state.writtenYear}
                                            onChange={this.onChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input
                                            required
                                            type="number"
                                            min="1"
                                            className="form-control form-control-lg"
                                            name="amount"
                                            placeholder="Amount"
                                            value={this.state.amount}
                                            onChange={this.onChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <input type="file" name={"file"}
                                               onChange={this.onFileChange}/>
                                    </div>
                                    <input
                                        type="submit"
                                        className="btn btn-primary btn-block mt-4"
                                    />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

AddBook.propTypes = {
    saveBook: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = (state) => ({
    errors: state.errors
});

export default connect(mapStateToProps, {saveBook})(AddBook);
