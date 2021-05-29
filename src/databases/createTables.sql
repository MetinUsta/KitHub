PRAGMA foreign_keys = ON;

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
    PublishDate text not null,
    PageCount integer not null,
    Isbn13 text not null unique,
    Overview text not null
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

create table if not exists BookGenres (
    BookId integer not null,
    Genre text not null,
    primary key(BookId, Genre)
);

create table if not exists BookLoans (
    UserId integer references Users(UserId)
        on update cascade
        on delete cascade,
    BookCopyId integer references BookCopies(BookCopyId)
        on update cascade
        on delete cascade,
    LoanDate text not null,
    IsReturned integer not null default 0,
    primary key (UserId, BookCopyId, LoanDate)
);

create table if not exists Comments (
    UserId integer references Users(UserId)
        on update cascade
        on delete cascade,
    BookId integer references Books(BookId)
        on update cascade
        on delete cascade,
    Comment text not null,
    primary key (UserId, BookId)
);

create table if not exists BookCopies (
    BookCopyId integer primary key,
    BookId integer references Books(BookId)
        on update cascade
        on delete cascade,
    LibraryId integer references Libraries(LibraryId)
        on update cascade
        on delete cascade,
    CopyCount integer not null default 1,
    unique(BookId, LibraryId)
);

insert or ignore into Users (Name, Surname, Email, Password)
values
    ("admin", "admin", "admin@sistemanalizi.com", "15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225");