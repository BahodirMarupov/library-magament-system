import React, { Component } from 'react'
import BookItem from './BookItem';
import { Pagination } from '@material-ui/lab';

class Book extends Component {

    render() {
        let { books, rents, onChange, validToken, user } = this.props;
        let items;
        let rentItems;
        if (books.content) {
            if (books.content.length > 0) {
                items = books.content.map((book) => {
                    if (rents.length > 0) {
                        rentItems = rents.filter((e) => e.bookDto.id === book.id);
                        if (rentItems.length > 0) {
                            return <BookItem book={book} isRent={true} validToken={validToken} user={user} />
                        }
                        else {
                            return <BookItem book={book} isRent={false} validToken={validToken} user={user} />
                        }
                    }
                    else{
                        return <BookItem book={book} isRent={false} validToken={validToken} user={user} />
                    }

                }
                );
            }
        }

        return (
            <div className={"text-center"}>
                <div className={"row"}>{items}</div>
                <Pagination count={books.totalPages} onChange={onChange} />
            </div>
        )
    }

}


export default (Book);
