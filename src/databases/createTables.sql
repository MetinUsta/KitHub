PRAGMA foreign_keys = 1;

create table if not exists Users (
    UserId integer primary key,
    Name text not null,
    Surname text not null,
    Email text not null unique,
    Password text not null,
    LateReturnStatus integer not null default 0
);

create table if not exists Books (
    BookId integer primary key,
    Title text not null,
    Author text not null,
    PublishDate text,
    PageCount integer,
    Edition text,
    Publisher text,
    Isbn13 text not null,
    Overview text
);

create table if not exists Libraries (
    LibraryId integer primary key,
    Name text not null,
    Address text not null,
    PhoneNumber text not null,
    Email text not null,
    EmailPrivateKey text not null,
    StartPointX integer not null,
    StartPointY integer not null,
    RowCount integer not null,
    ColumnCount integer not null,
    ShelfWidth integer not null,
    ShelfHeight integer not null,
    ShelfHorizontalGap integer not null,
    ShelfVerticalGap integer not null
);

create table if not exists Genres (
    GenreId integer primary key,
    Title text not null
);

create table if not exists Queue (
    UserId integer references Users
        on update cascade
        on delete cascade,
    BookId integer references Books
        on update cascade
        on delete cascade,
    QueueOrder integer not null,
    primary key (UserId, BookId)
);

create table if not exists BookLoans (
    UserId integer references Users
        on update cascade
        on delete cascade,
    BookCopyId integer references BookCopies
        on update cascade
        on delete cascade,
    LoanDate text not null,
    IsReturned integer not null default 0,
    primary key (UserId, BookCopyId)
);

create table if not exists Comments (
    UserId integer references Users
        on update cascade
        on delete cascade,
    BookId integer references Books
        on update cascade
        on delete cascade,
    Comment text not null,
    primary key (UserId, BookId)
);

create table if not exists BookCopies (
    BookCopyId integer primary key,
    BookId integer references Books
        on update cascade
        on delete cascade,
    LibraryId integer references Libraries
        on update cascade
        on delete cascade,
    CopyCount integer not null default 0
);

create table if not exists BooksGenres (
    BookId integer references Books
        on update cascade
        on delete cascade,
    GenreId integer references Genres
        on update cascade
        on delete cascade,
    primary key (BookId, GenreId)
);
