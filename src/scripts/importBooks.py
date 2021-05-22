#!/usr/bin/env python3
import csv
import sqlite3
from sqlite3 import Error
from sys import argv, exit


def toRaw(string):
    return fr"{string}"


def connectToDatabase(dbPath):
    conn = None
    try:
        conn = sqlite3.connect(dbPath)
        return conn
    except Error as e:
        print(e)

    return conn


if __name__ == '__main__':
    if len(argv) != 3:
        print(f'usage: python {argv[0]} path_to_csv path_to_db')
        exit(1)

    inputPath = argv[1]
    dbPath = argv[2]
    dbPath = toRaw(dbPath)
    conn = connectToDatabase(dbPath)
    if conn is None:
        print('error: Could not import the books!')
        exit(1)

    with open(inputPath, "r", newline='') as readFile, conn:
        reader = csv.DictReader(readFile)

        fillBooksSql = ''' INSERT OR IGNORE INTO Books (BookId, Title, Author, PublishDate, PageCount, Isbn13, Overview)
                           VALUES(?, ?, ?, ?, ?, ?, ?) '''
        fillBookGenresSql = ''' INSERT INTO BookGenres (BookId, Genre)
                                VALUES(?, ?)'''

        cursor = conn.cursor()
        lastBookId = cursor.execute('SELECT BookId FROM Books ORDER BY BookId DESC LIMIT 1').fetchone()
        if lastBookId is None:
            lastBookId = 1
        else:
            lastBookId = int(lastBookId[0]) + 1

        for row in reader:
            cursor.execute(fillBooksSql, (lastBookId, row['Title'], row['Author'], row['PublishDate'], row['PageCount'], row['Isbn13'], row['Overview']))
            genres = row['Genres'].split(',')
            for genre in genres:
                cursor.execute(fillBookGenresSql, (lastBookId, genre.strip()))
            lastBookId += 1
