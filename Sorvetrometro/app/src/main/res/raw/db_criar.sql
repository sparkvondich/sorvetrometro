CREATE TABLE sorveteria (
id integer NOT NULL PRIMARY KEY,
nome varchar(255) NOT NULL
);
CREATE TABLE sorvete (
id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
sorveteria_id integer NOT NULL,
nome varchar(255) NOT NULL,
FOREIGN KEY (sorveteria_id) REFERENCES sorveteria (id)
);