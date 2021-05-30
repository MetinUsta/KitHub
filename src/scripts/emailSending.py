import email, smtplib, ssl
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives.asymmetric import rsa

import sys
import os

from email import encoders
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

import sqlite3
from sqlite3 import Error

def create_connection(dbFile):
    conn = None
    try:
        conn = sqlite3.connect(dbFile)
    except Error as e:
        print(e)
    
    return conn

def selectBook(conn, bookISBN):
    cursor = conn.cursor()
    queryString = "SELECT Title FROM Books WHERE Isbn13 = ?"
    cursor.execute(queryString, (bookISBN, ))
    bookInfo = cursor.fetchall()
    return bookInfo

def getPrivateKey(conn):
    cursor = conn.cursor()
    queryString = "SELECT EmailPrivateKey FROM Libraries WHERE LibraryId = 1"
    cursor.execute(queryString)
    privateKey = cursor.fetchall()
    return privateKey[0]


dbPath = "src/databases/libraryManagement.db"
conn = create_connection(dbPath)

if conn == None:
    print("Couldn't connect to the database!")
    exit(1)

bookISBN = sys.argv[1]

bookName = selectBook(conn, bookISBN)

subject = "Kutuphane Bilgi Sistemi Kitap konumu"
body = "Bugün içerisinde ödünç aldığınız \"" + (bookName[0])[0] + "\" kitabının kütüphanedeki konumu ektedir. İyi okumalar dileriz."
sender_email = "infokutuphanebilgisistemi@gmail.com"
receiver_email = sys.argv[2]
encryptedPassword = b'\xaa\xaf~W\xc2\xc9{l\x88{\xc7T\xeb\x07\xf7pN\x8b\x03 \x8a\xee\x1f\x9d\xff\x14\x97\xcc\xf5Z\x90n\x060I\xa0l\xac\x01\x17.\x1a\xde\xbca\xc44\xa8\xc3y\xde\xbcSw\x8a$\x96\xcdyn2\'\xc2-\xc5l\x81\xa3\x1anBf\x8b\xc3a\xd7\x9b\xa7\xa3\xbbZ\xda\x88\xc1\x17MiG\x8d\xc4\x892\x0f"m\xa3\x96FL\xda\xd3\xd0*\xf2\xec\xad\xd2\xdcY:X\xe3\\k+\x06\xb8\n\xee\xd8O\xe1Z\xd4y\xe9\xd1P;\xc9]\x08\xd7+L\r\x86\x96\xda&\x9fOX\x86\xdd\'\xff!F\xf8PNv\xc9$\x90\x8f\x14\x0c5\x87\xf4\x86\xb3\xdd\xfb\x83\xa1\x7fl\x14\xabal1x\xbci \x01\xbd\xb4\xaa\x8b\x83N\x19\xa2\xa8b\x08W\x1b\x0e\xed\xc2@\xab\x0c\xb25\xf4$t\x86m\x11,z\xd0\xcf\xf7\x96y;\xe5\x15/\xc2m\xa9e\xdd\xb2\xfc\xfc{\xbe]\xdb\x89P~\x14\x80\x0c\x7f\tlU#\xc8\xae\x06\xce\x9b\xfa`\xce\xa9P\xf0\xd7\x13\xdf\xfc'

private_key = serialization.load_pem_private_key(
    getPrivateKey(conn)[0].encode(),
    password=None,
    backend=default_backend()
)

decryptedPassword = private_key.decrypt(
    encryptedPassword,
    padding.OAEP(
        mgf=padding.MGF1(algorithm=hashes.SHA256()),
        algorithm=hashes.SHA256(),
        label=None
    )
)

password = decryptedPassword.decode()

# Create a multipart message and set headers
message = MIMEMultipart()
message["From"] = sender_email
message["To"] = receiver_email
message["Subject"] = subject
message["Bcc"] = receiver_email  # Recommended for mass emails

# Add body to email
message.attach(MIMEText(body, "plain"))

filename = "result.png"  # In same directory as script

# Open PDF file in binary mode
with open(filename, "rb") as attachment:
    # Add file as application/octet-stream
    # Email client can usually download this automatically as attachment
    part = MIMEBase("application", "octet-stream")
    part.set_payload(attachment.read())

# Encode file in ASCII characters to send by email    
encoders.encode_base64(part)

# Add header as key/value pair to attachment part
part.add_header(
    "Content-Disposition",
    f"attachment; filename= {filename}",
)

# Add attachment to message and convert message to string
message.attach(part)
text = message.as_string()

# Log in to server using secure context and send email
context = ssl.create_default_context()
with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
    server.login(sender_email, password)
    server.sendmail(sender_email, receiver_email, text)

os.remove("result.png")