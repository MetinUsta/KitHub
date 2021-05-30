import sqlite3
from sqlite3 import Error
import sys
import cv2

def create_connection(dbFile):
    conn = None
    try:
        conn = sqlite3.connect(dbFile)
    except Error as e:
        print(e)
    
    return conn

def selectLibrary(conn, libraryId):
    cursor = conn.cursor()
    queryString = "SELECT StartPointX, StartPointY, RowCount, ColumnCount, ShelfWidth, ShelfHeight, ShelfHorizontalGap, ShelfVerticalGap FROM Libraries WHERE LibraryId = ?"
    cursor.execute(queryString, (libraryId, ))
    libraryInfo = cursor.fetchall()
    return libraryInfo

def findBook(isbnTwoDigit):
    Buffer = image.copy()
    start_point, end_point = coordinatesDict[isbnTwoDigit % (ROWS*COLUMNS)]
    Buffer = cv2.rectangle(Buffer, start_point, end_point, COLOR, THICKNESS)
    return Buffer

dbPath = "src/databases/libraryManagement.db"
conn = create_connection(dbPath)
libraryId = sys.argv[1]
bookISBN = sys.argv[2]


if conn == None:
    print("Couldn't connect to the database!")
    exit(1)

libraryInfo = selectLibrary(conn, libraryId)[0]

libraryPlanNames = ["Beyazit.png", "IstanbulUni.png", "Beykoz.png", "YildizUni.png", "MarmaraUni.png"]
planPath = "src/scripts/LibraryPlans/" + libraryPlanNames[int(libraryId)-1]
image = cv2.imread(planPath)

START_POINT = (libraryInfo[0], libraryInfo[1])
COLOR = (0, 0, 255) #BGR
THICKNESS = 3
ROWS = libraryInfo[2]
COLUMNS = libraryInfo[3]
SHELF_WIDTH = libraryInfo[4]
SHELF_HEIGHT = libraryInfo[5]
SHELF_HORIZANTAL_GAP = libraryInfo[6]
SHELF_VERTICAL_GAP = libraryInfo[7]
isbnTwoDigit = int(bookISBN[-2:])

coordinatesDict = {}
count = 0
for i in range(ROWS):
    for j in range(COLUMNS):
        start_x = START_POINT[0] + j * (SHELF_WIDTH + SHELF_HORIZANTAL_GAP)
        start_y = START_POINT[1] + i * (SHELF_HEIGHT + SHELF_VERTICAL_GAP)
        coordinatesDict[count] = [(start_x, start_y), (start_x + SHELF_WIDTH, start_y + SHELF_HEIGHT)]
        count += 1

cv2.imwrite("result.png", findBook(isbnTwoDigit))