create table book (
	id int primary key,
    cover_url varchar(100),
    title varchar (100),
    author varchar(100),
    category varchar(50),
    branch_id INT
);

create table patron (
	username varchar(40) primary key,
    first_name varchar(20),
    last_name varchar(20),
    email varchar (40),
    password VARCHAR (20),
    dob date
);

create table loan (
	book_id int,
    patron_username varchar (40),
    start_date date,
    due_date date,
    cost decimal (6,2),
    primary key(book_id, patron_username),
    foreign key(book_id) references book(id) on delete cascade,
	foreign key(patron_username) references patron(username) on delete cascade
);

create table hold (
	book_id int,
    patron_username varchar (40),
    start_date date,
    primary key (book_id, patron_username),
    foreign key(book_id) references book(id) on delete cascade,
    foreign key(patron_username) references patron(username) on delete cascade
);

CREATE TABLE employee (
  emp_id INT PRIMARY KEY,
  first_name VARCHAR(40),
  last_name VARCHAR(40),
  dob DATE,
  sex VARCHAR(1),
  salary INT,
  super_id INT,
  branch_id INT
);

CREATE TABLE branch (
  branch_id INT PRIMARY KEY,
  branch_name VARCHAR(40),
  location VARCHAR (40),
  mgr_id INT,
  FOREIGN KEY(mgr_id) REFERENCES employee(emp_id) ON DELETE SET NULL
);

update branch
set location = '175 5th Ave, New York, NY 10010'
where branch_id = 100;

alter table employee
add foreign key(super_id)
references employee(emp_id)
on delete set null;

alter table employee
add foreign key(branch_id)
references branch(branch_id)
on delete set null;

select * from patron;
