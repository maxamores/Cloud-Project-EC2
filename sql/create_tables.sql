CREATE TABLE Blobs
(
    user_id varchar(255) NOT NULL,
    blob_key varchar(255) NOT NULL,
    google_url varchar(255) NOT NULL,
    amazon_url varchar(255),
    ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, blob_key)
); 

CREATE TABLE Last_Update
(
	ts TIMESTAMP  NOT NULL,
	PRIMARY KEY (ts)
); 

CREATE TABLE Tags
(
    user_id varchar(255) NOT NULL,
	blob_key varchar(255) NOT NULL,
	tag varchar(255) NOT NULL,
	FOREIGN KEY (user_id, blob_key) REFERENCES Blobs(user_id, blob_key)
);

