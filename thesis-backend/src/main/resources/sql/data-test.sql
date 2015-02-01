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
  node_id bigint,
  execution_date TIMESTAMP,
  content VARCHAR(255),
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

----------------------------------------
--- TEST data for H2 in-memory DB  -----
----------------------------------------
insert into experiment (id, version, name, description, start_time, end_time) values (1 , 0, 'Test experiment 1', 'Test experiment description 1', '2015-10-10 08:00:00', '2015-10-10 08:20:00');
insert into experiment (id, version, name, description, start_time, end_time) values (2 , 0, 'Test experiment 2', 'Test experiment description 2', '2014-10-10 14:20:00', '2014-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (3 , 0, 'Test experiment 3', 'Test experiment description 3', '2014-10-12 14:20:00', '2014-10-12 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (4 , 0, 'Test experiment 4', 'Test experiment description 4', '2014-04-07 14:20:00', '2014-04-07 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (5 , 0, 'Test experiment 5', 'Test experiment description 5', '2013-10-10 14:20:00', '2013-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (6 , 0, 'Test experiment 6', 'Test experiment description 6', '2012-10-10 14:20:00', '2012-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (7 , 0, 'Test experiment 7', 'Test experiment description 7', '2014-11-10 14:20:00', '2014-11-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (8 , 0, 'Test experiment 8', 'Test experiment description 8', '2014-10-11 14:20:00', '2014-10-11 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (9 , 0, 'Test experiment 9', 'Test experiment description 9', '2014-01-01 14:20:00', '2014-01-01 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (10 , 0, 'Test experiment 10', 'Test experiment description 10', '2013-11-11 14:20:00', '2013-11-11 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (11 , 0, 'Test experiment 11', 'Test experiment description 11', '2014-09-10 14:20:00', '2014-09-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (12 , 0, 'Test experiment 12', 'Test experiment description 12', '2014-12-12 14:20:00', '2014-12-12 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (13 , 0, 'Test experiment 13', 'Test experiment description 13', '2014-10-13 14:20:00', '2014-10-13 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (14 , 0, 'Test experiment 14', 'Test experiment description 14', '2014-10-14 14:20:00', '2014-10-14 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (15 , 0, 'Test experiment 15', 'Test experiment description 15', '2014-10-15 14:20:00', '2014-10-15 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (16 , 0, 'Test experiment 16', 'Test experiment description 16', '2014-10-16 14:20:00', '2014-10-16 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (17 , 0, 'Test experiment 17', 'Test experiment description 17', '2014-10-17 14:20:00', '2014-10-17 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (18 , 0, 'Test experiment 18', 'Test experiment description 18', '2014-10-18 14:20:00', '2014-10-18 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (19 , 0, 'Test experiment 19', 'Test experiment description 19', '2014-10-19 14:20:00', '2014-10-19 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (20 , 0, 'Test experiment 20', 'Test experiment description 20', '2014-10-21 14:20:00', '2014-10-21 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (21 , 0, 'Test experiment 21', 'Test experiment description 21', '2014-10-22 14:20:00', '2014-10-22 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (22 , 0, 'Test experiment 22', 'Test experiment description 22', '2014-10-23 14:20:00', '2014-10-23 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (23 , 0, 'Test experiment 23', 'Test experiment description 23', '2014-10-01 14:20:00', '2014-10-01 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (24 , 0, 'Test experiment 24', 'Test experiment description 24', '2014-10-27 14:20:00', '2014-10-27 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (25 , 0, 'Test experiment 25', 'Test experiment description 25', '2014-10-05 14:20:00', '2014-10-05 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (26 , 0, 'Test experiment 26', 'Test experiment description 26', '2014-10-11 14:20:00', '2014-10-11 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (27 , 0, 'Test experiment 27', 'Test experiment description 27', '2011-10-10 14:20:00', '2011-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (28 , 0, 'Test experiment 28', 'Test experiment description 28', '2010-10-10 14:20:00', '2010-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (29 , 0, 'Test experiment 29', 'Test experiment description 29', '2009-10-10 14:20:00', '2009-10-10 14:23:14');
insert into experiment (id, version, name, description, start_time, end_time) values (30 , 0, 'Test experiment 30', 'Test experiment description 30', '2008-10-10 14:20:00', '2008-10-10 14:23:14');

insert into node (id, version, name, description) values (1, 0, 'N001', 'Node description 1');
insert into node (id, version, name, description) values (2, 0, 'N002', 'Node description 2');
insert into node (id, version, name, description) values (3, 0, 'N003', 'Node description 3');
insert into node (id, version, name, description) values (4, 0, 'N004', 'Node description 4');
insert into node (id, version, name, description) values (5, 0, 'N005', 'Node description 5');

insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (1, 0, 1, 1, '2014-10-10 08:00:01', null);
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (2, 0, 1, 2, '2014-10-10 08:00:01', '2014-10-10 08:19:00');
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (3, 0, 2, 3, '2014-10-10 14:20:00', null);
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (4, 0, 2, 4, '2014-10-10 14:20:00', '2014-10-10 14:21:33');
insert into experiment_2_node (id, version, experiment_id, node_id, addition_time, removal_time) values (5, 0, 2, 5, '2014-10-10 14:20:00', '2014-10-10 14:21:58');

insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (1, 0, 1, 2, '2014-10-10 08:00:01', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (2, 0, 3, 4, '2014-10-10 14:22:22', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (3, 0, 3, 5, '2014-10-10 14:20:33', null);
insert into node_2_node (id, version, first_node_id, second_node_id, connection_time, disconnection_time) values (4, 0, 4, 5, '2014-10-10 14:21:14', null);

insert into query (id, version, node_id, execution_date, content) values (1, 0, 1, '2014-10-10 08:00:01', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, version, node_id, execution_date, content) values (2, 0, 1, '2014-10-10 08:00:01', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, version, node_id, execution_date, content) values (3, 0, 1, '2014-10-10 08:00:01', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, version, node_id, execution_date, content) values (4, 0, 1, '2014-10-10 08:00:01', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, version, node_id, execution_date, content) values (5, 0, 1, '2014-10-10 08:00:01', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');

insert into query_attribute (id, version, query_id, key, value) values (1, 0, 1, 'Test attribute key 1', 'Test attribute value 1');
insert into query_attribute (id, version, query_id, key, value) values (2, 0, 1, 'Test attribute key 2', 'Test attribute value 2');
insert into query_attribute (id, version, query_id, key, value) values (3, 0, 2, 'Test attribute key 3', 'Test attribute value 3');
insert into query_attribute (id, version, query_id, key, value) values (4, 0, 2, 'Test attribute key 4', 'Test attribute value 4');
insert into query_attribute (id, version, query_id, key, value) values (5, 0, 3, 'Test attribute key 5', 'Test attribute value 5');
insert into query_attribute (id, version, query_id, key, value) values (6, 0, 3, 'Test attribute key 6', 'Test attribute value 6');
insert into query_attribute (id, version, query_id, key, value) values (7, 0, 4, 'Test attribute key 7', 'Test attribute value 7');
insert into query_attribute (id, version, query_id, key, value) values (8, 0, 4, 'Test attribute key 8', 'Test attribute value 8');
insert into query_attribute (id, version, query_id, key, value) values (9, 0, 5, 'Test attribute key 9', 'Test attribute value 9');
insert into query_attribute (id, version, query_id, key, value) values (10, 0, 5, 'Test attribute key 10', 'Test attribute value 10');
