create table tbloans (amount decimal(10,2) not null, status TINYINT(1) DEFAULT 1, date_created DATETIME DEFAULT CURRENT_TIMESTAMP, date_updated DATETIME ON UPDATE CURRENT_TIMESTAMP, loan_creation_date datetime(6), loan_topup_date datetime(6), id BINARY(16) not null, email VARCHAR(50) not null, id_number varchar(255), loan_status varchar(255), msisdn varchar(255) not null, repayment_status varchar(255), primary key (id)) engine=InnoDB;
alter table tbloans add constraint UK_dgusxrkre3ohdg5jo0mhprqid unique (email);
alter table tbloans add constraint UK_7k329rrtsr29kt7vbpksd95fb unique (msisdn);
