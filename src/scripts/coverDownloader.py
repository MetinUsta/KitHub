import requests
import csv
from multiprocessing.pool import ThreadPool
import threading

folderName = "bookCovers/"

def download_image(info):
    url, isbn = info.split(",")
    response = requests.get(url).content
    fileName = folderName + isbn + ".jpg"
    with open(fileName, 'wb') as handler:
        handler.write(response)

urls = []
isbns = []
infos = []
with open("Books.csv", 'r', encoding='utf8') as csvFile:
    
    if csvFile == None:
        print("a")

    reader = csv.reader(csvFile)
    for i, row in enumerate(reader):
        URL = "http://covers.openlibrary.org/b/isbn/" + row[5] + "-L.jpg"
        infos.append(URL + "," + row[5])
        # urls.append(URL)
        # isbns.append(row[5])

# result = ThreadPool(16).imap_unordered(download_image, info)
threads = [threading.Thread(target=download_image, args=(info,)) for info in infos]

for thread in threads:
    thread.start()

# response = requests.get("http://covers.openlibrary.org/b/isbn/0385472579-S.jpg").content

# with open("bookCovers/image_name.jpg", 'wb') as handler:
#     handler.write(response)