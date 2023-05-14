Bookstore API

Instructions:

Create a RESTFUL API to store and read book information from MySQL database. The table layouts as following: 

1. book 
   Id - Long primary key 
   ISBN - ISBN of book 
   TITLE - Book Title 
   Year -  Year of publication
   Price - Price of Book 
   Genre - Genre of Book

2. Author
   
   Id - Long primary key auto increment
   Name - Author Name
   Date - Date of birth of Author
   Book_ISBN - ISBN of the Book

   There is a relationship between Book and Author linked by Book_ISBN, 1 book may have different authors and a author may have different books.

3. Users (to store API User information)
   
   Id - Long primary key
   Email - User's email address
   Username - Username 
   Password - store user's password information
   Phone - user's contact information
   Enable - a flag to indicate whether user are actived

4. ROLE (to store User Role information)

   Id - Int primary key Auto increment
   Name - Role Name eg: ADMIN, CHECKER 
5. User_Role (store user's role information)

   Id - long primary key AUTO increment
   user_id - linked to Users table id
   role_id - linked to Roles table id

   The User_Role entity is a join table that maps the many-to-many relationship between Users and Roles. It has two many-to-one relationships to the Users and Roles.

  in DB folder which contains 2 sub-folder, one is migrate folder which contains the script only used to create table. the other one is seeds folder which contains the sql to initiate the table data

  Retrieve the Data 
     1. GET /api/v1/books/{authorName}  returns a JSON object list of all the books include their authors 
     2. GET /api/v1/books/{title}       return particular Book based on given book title

  Manage the data

     1. PUT    /api/v1/books/{isbn}  update book information (book price, title, years of publication, etc) basec on given isbn
     2. POST   /api/v1/books/        save a new book based on given request body
     3. DELETE /api/v1/books/{isbn}  delete an existing book from database by given isbn

  The system will have authentication in place. this is implemented by Spring Security and JSON Web Token

     1. the user with any of the role 'CHECKER','ADMIN' role has the access to Add, Update, Query API service
     2. Only the user with 'ADMIN' role can access DELETE API service.
