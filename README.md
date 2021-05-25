# Sistem Analizi ve Tasarımı Proje

## Projeye Katkı Sağlayanlar

* 19011017 - Emircan Yılmaz
* 19011056 - Mehmet Yiğit
* 19011033 - Erkan Vatan
* 19011003 - Metin Usta
* 19011030 - Emirhan Durusoy
* 18011052 - Faruk Veli Özdemir

## Proje Konusu

Kütüphaneler arası ortak bir veri tabanı ile kitaplara erişimi kolay hale getiren bir merkezi bilgi uygulaması.

## Database Methods
### User Methods
* getUserInfo(email, hashedPassword)
* getUserInfo(userId)
* addNewUser(name, surname, email, hashedPassword)
* userLoanBook(userId, bookId, libraryId, loanDate)
* userReturnBook(userId, bookCopyId, returnDate)
* getLoanedBooks(userId)
* getReturnedBooks(userId)
* getUserComments(userId)

### Comment Methods
* addNewComment(userId, bookId, comment)

### Book Methods
* getBookInfo(bookId)
* getBookFromIsbn(isbn13)
* getBookFromCopyId(bookCopyId)
* getBooksFromGenre(genre)
* getCommentsOfBook(bookId)
* getLibrariesOfBook(bookId)
* getGenresOfBook(bookId)
* donateBook(title, author, publishDate, pageCount, isbn13, overview, genres, libraryId)
* getSearchedBooks(searchStr)
* getUniqueGenres()

### Library Methods
* getLibraryContactInfo(libraryId)
* getLibraryShelfInfo(libraryId)
* getLibraries()
