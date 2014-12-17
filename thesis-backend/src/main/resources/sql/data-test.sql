----------------------------------------
--- TEST data for H2 in-memory DB  -----
----------------------------------------

CREATE TABLE experiment
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  name varchar(255),
  description varchar(255),
  start_time TIMESTAMP,
  end_time TIMESTAMP
);

CREATE TABLE node
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  name varchar(255),
  description varchar(255),
);

CREATE TABLE experiment_2_node
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  experiment_id bigint,
  node_id bigint,
  addition_time TIMESTAMP,
  removal_time TIMESTAMP,
  FOREIGN KEY (experiment_id) REFERENCES experiment(id),
  FOREIGN KEY (node_id) REFERENCES node(id)
);

CREATE TABLE node_2_node
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  first_node_id bigint,
  second_node_id bigint,
  connection_time TIMESTAMP,
  disconnection_time TIMESTAMP,
  FOREIGN KEY (first_node_id) REFERENCES node(id),
  FOREIGN KEY (second_node_id) REFERENCES node(id)
);

CREATE TABLE query
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  experiment_id bigint,
  node_id bigint,
  add_date TIMESTAMP,
  content VARCHAR(255),
  FOREIGN KEY (experiment_id) REFERENCES experiment(id),
  FOREIGN KEY (node_id) REFERENCES node(id)
);

CREATE TABLE query_attribute
(
  id bigint PRIMARY KEY auto_increment,
  version INT,
  query_id BIGINT,
  key varchar(255),
  value varchar(255),
  FOREIGN KEY (query_id) REFERENCES query(id)
);

insert into experiment (id, version, name, description, start_time, end_time) values (1 , 0, 'Test experiment 1', 'Test description 1', '2014-10-10 08:00:00', '2014-10-10 08:20:00');
insert into experiment (id, version, name, description, start_time, end_time) values (2 , 0, 'Test experiment 2', 'Test description 2', '2014-10-10 14:20:00', '2014-10-10 14:23:14');

insert into node (id, version, name, description) values (1, 0, 'Test node 1', 'Node description 1');
insert into node (id, version, name, description) values (2, 0, 'Test node 2', 'Node description 2');
insert into node (id, version, name, description) values (3, 0, 'Test node 3', 'Node description 3');
insert into node (id, version, name, description) values (4, 0, 'Test node 4', 'Node description 4');
insert into node (id, version, name, description) values (5, 0, 'Test node 5', 'Node description 5');

insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (1, 0, 1, 1, '2014-10-10 08:00:01', null);
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (2, 0, 1, 2, '2014-10-10 08:00:01', '2014-10-10 08:19:00');
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (3, 0, 2, 3, '2014-10-10 14:20:00', null);
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (4, 0, 2, 4, '2014-10-10 14:20:00', '2014-10-10 14:21:33');
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (5, 0, 2, 5, '2014-10-10 14:20:00', '2014-10-10 14:21:58');

insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (1, 0, 1, 2, '2014-10-10 08:00:01', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (2, 0, 3, 4, '2014-10-10 14:22:22', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (3, 0, 3, 5, '2014-10-10 14:20:33', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (4, 0, 4, 5, '2014-10-10 14:21:14', null);