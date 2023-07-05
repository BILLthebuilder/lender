-- we don't know how to generate root <with-no-name> (class Root) :(
grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin, telemetry_log_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

create table tbloans
(
    amount             decimal(10, 2)                       not null,
    status             tinyint(1) default 1                 null,
    date_created       datetime   default CURRENT_TIMESTAMP null,
    date_updated       datetime                             null on update CURRENT_TIMESTAMP,
    loan_creation_date datetime(6)                          null,
    loan_topup_date    datetime(6)                          null,
    id                 binary(16)                           not null
        primary key,
    email              varchar(50)                          not null,
    id_number          varchar(255)                         null,
    loan_status        varchar(255)                         null,
    msisdn             varchar(255)                         not null,
    repayment_status   varchar(255)                         null,
    constraint UK_7k329rrtsr29kt7vbpksd95fb
        unique (msisdn),
    constraint UK_dgusxrkre3ohdg5jo0mhprqid
        unique (email)
);