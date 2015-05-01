CREATE TABLE experiment
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  name varchar(255),
  description varchar(255),
  start_time TIMESTAMP,
  end_time TIMESTAMP
);

CREATE TABLE node
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  external_id varchar(255) NOT NULL UNIQUE,
  name varchar(255),
  description varchar(255)
);

CREATE TABLE experiment_2_node
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  experiment_id BIGINT,
  node_id BIGINT,
  addition_time TIMESTAMP NOT NULL,
  removal_time TIMESTAMP,
  FOREIGN KEY (experiment_id) REFERENCES experiment(id),
  FOREIGN KEY (node_id) REFERENCES node(id)
);

CREATE TABLE node_2_node
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  first_node_id BIGINT,
  second_node_id BIGINT,
  experiment_id BIGINT,
  connection_time TIMESTAMP NOT NULL,
  disconnection_time TIMESTAMP,
  FOREIGN KEY (first_node_id) REFERENCES node(id),
  FOREIGN KEY (second_node_id) REFERENCES node(id),
  FOREIGN KEY (experiment_id) REFERENCES experiment(id)
);

CREATE TABLE query
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  experiment_id BIGINT,
  node_id BIGINT,
  deployment_time TIMESTAMP NOT NULL,
  content VARCHAR(255) NOT NULL,
  FOREIGN KEY (experiment_id) REFERENCES experiment(id),
  FOREIGN KEY (node_id) REFERENCES node(id)
);

CREATE TABLE query_attribute
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  query_id BIGINT,
  key varchar(255) NOT NULL,
  value varchar(255) NOT NULL,
  FOREIGN KEY (query_id) REFERENCES query(id)
);

CREATE TABLE query_execution
(
  id BIGINT PRIMARY KEY IDENTITY,
  version integer DEFAULT 0 NOT NULL,
  query_id BIGINT,
  execution_time TIMESTAMP,
  FOREIGN KEY (query_id) REFERENCES query(id)
);

-------------------------------------------------------------------------------------
--- Sample data for H2 in-memory DB used for integration test and for development ---
-------------------------------------------------------------------------------------
insert into experiment (id, name, description, start_time, end_time) values (1 , 'Test experiment 1', 'Test experiment description 1', '2015-10-10 08:00:00.070', '2015-10-10 09:20:00.123');
insert into experiment (id, name, description, start_time, end_time) values (2 , 'Test experiment 2', 'Test experiment description 2', '2014-10-10 14:20:00.070', '2014-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (3 , 'Test experiment 3', 'Test experiment description 3', '2014-10-12 14:20:00.070', '2014-10-12 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (4 , 'Test experiment 4', 'Test experiment description 4', '2014-04-07 14:20:00.070', '2014-04-07 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (5 , 'Test experiment 5', 'Test experiment description 5', '2013-10-10 14:20:00.070', '2013-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (6 , 'Test experiment 6', 'Test experiment description 6', '2012-10-10 14:20:00.070', '2012-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (7 , 'Test experiment 7', 'Test experiment description 7', '2014-11-10 14:20:00.078', '2014-11-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (8 , 'Test experiment 8', 'Test experiment description 8', '2014-10-11 14:20:00.078', '2014-10-11 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (9 , 'Test experiment 9', 'Test experiment description 9', '2014-01-01 14:20:00.078', '2014-01-01 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (10 , 'Test experiment 10', 'Test experiment description 10', '2013-11-11 14:20:00.078', '2013-11-11 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (11 , 'Test experiment 11', 'Test experiment description 11', '2014-09-10 14:20:00.078', '2014-09-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (12 , 'Test experiment 12', 'Test experiment description 12', '2014-12-12 14:20:00.078', '2014-12-12 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (13 , 'Test experiment 13', 'Test experiment description 13', '2014-10-13 14:20:00.078', '2014-10-13 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (14 , 'Test experiment 14', 'Test experiment description 14', '2014-10-14 14:20:00.078', '2014-10-14 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (15 , 'Test experiment 15', 'Test experiment description 15', '2014-10-15 14:20:00.078', '2014-10-15 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (16 , 'Test experiment 16', 'Test experiment description 16', '2014-10-16 14:20:00.078', '2014-10-16 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (17 , 'Test experiment 17', 'Test experiment description 17', '2014-10-17 14:20:00.078', '2014-10-17 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (18 , 'Test experiment 18', 'Test experiment description 18', '2014-10-18 14:20:00.078', '2014-10-18 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (19 , 'Test experiment 19', 'Test experiment description 19', '2014-10-19 14:20:00.078', '2014-10-19 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (20 , 'Test experiment 20', 'Test experiment description 20', '2014-10-21 14:20:00.078', '2014-10-21 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (21 , 'Test experiment 21', 'Test experiment description 21', '2014-10-22 14:20:00.078', '2014-10-22 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (22 , 'Test experiment 22', 'Test experiment description 22', '2014-10-23 14:20:00.078', '2014-10-23 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (23 , 'Test experiment 23', 'Test experiment description 23', '2014-10-01 14:20:00.078', '2014-10-01 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (24 , 'Test experiment 24', 'Test experiment description 24', '2014-10-27 14:20:00.078', '2014-10-27 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (25 , 'Test experiment 25', 'Test experiment description 25', '2014-10-05 14:20:00.078', '2014-10-05 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (26 , 'Test experiment 26', 'Test experiment description 26', '2014-10-11 14:20:00.078', '2014-10-11 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (27 , 'Test experiment 27', 'Test experiment description 27', '2011-10-10 14:20:00.078', '2011-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (28 , 'Test experiment 28', 'Test experiment description 28', '2010-10-10 14:20:00.078', '2010-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (29 , 'Test experiment 29', 'Test experiment description 29', '2009-10-10 14:20:00.078', '2009-10-10 14:23:14.078');
insert into experiment (id, name, description, start_time, end_time) values (30 , 'Test experiment 30', 'Test experiment description 30', '2008-10-10 14:20:00.078', '2008-10-10 14:23:14.078');

insert into node (id, external_id, name, description) values (1, 'PC001', 'N001', 'Node description 1');
insert into node (id, external_id, name, description) values (2, 'PC002', 'N002', 'Node description 2');
insert into node (id, external_id, name, description) values (3, 'PC003', 'N003', 'Node description 3');
insert into node (id, external_id, name, description) values (4, 'PC004', 'N004', 'Node description 4');
insert into node (id, external_id, name, description) values (5, 'PC005', 'N005', 'Node description 5');
insert into node (id, external_id, name, description) values (6, 'PC006', 'N006', 'Node description 6');
insert into node (id, external_id, name, description) values (7, 'PC007', 'N007', 'Node description 7');
insert into node (id, external_id, name, description) values (8, 'PC008', 'N008', 'Node description 8');
insert into node (id, external_id, name, description) values (9, 'PC009', 'N009', 'Node description 9');
insert into node (id, external_id, name, description) values (10, 'PC010', 'N010', 'Node description 10');
insert into node (id, external_id, name, description) values (11, 'PC011', 'N011', 'Node description 11');
insert into node (id, external_id, name, description) values (12, 'PC012', 'N012', 'Node description 12');
insert into node (id, external_id, name, description) values (13, 'PC013', 'N013', 'Node description 13');
insert into node (id, external_id, name, description) values (14, 'PC014', 'N014', 'Node description 14');
insert into node (id, external_id, name, description) values (15, 'PC015', 'N015', 'Node description 15');

insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (1, 1, 1, '2014-10-10 08:00:01.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (2, 1, 2, '2014-10-10 08:00:14.070', '2014-10-10 08:02:00.070');
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (3, 1, 3, '2014-10-10 08:00:14.086', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (4, 1, 4, '2014-10-10 08:00:15.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (5, 1, 5, '2014-10-10 08:00:15.072', '2014-10-10 08:02:01.070');
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (6, 1, 6, '2014-10-10 08:00:15.073', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (7, 1, 7, '2014-10-10 08:14:00.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (8, 1, 8, '2014-10-10 08:14:28.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (9, 1, 9, '2014-10-10 08:14:29.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (10, 1, 10, '2014-10-10 08:19:00.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (11, 2, 11, '2014-10-10 14:20:01.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (12, 2, 12, '2014-10-10 14:20:02.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (13, 2, 13, '2014-10-10 14:20:03.070', '2014-10-10 14:22:58.070');
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (14, 2, 14, '2014-10-10 14:20:14.070', null);
insert into experiment_2_node (id, experiment_id, node_id, addition_time, removal_time) values (15, 2, 15, '2014-10-10 14:20:18.070', '2014-10-10 14:21:58.070');

insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (1, 1, 2, 1, '2014-10-10 08:00:01.123', '2014-10-10 08:00:12.123');
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (2, 2, 3, 1, '2014-10-10 08:00:13.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (3, 1, 10, 1, '2014-10-10 08:00:02.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (4, 1, 4, 1, '2014-10-10 08:00:03.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (5, 4, 6, 1, '2014-10-10 08:00:04.123', '2014-10-10 08:00:59.123');
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (6, 5, 6, 1, '2014-10-10 08:00:14.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (7, 5, 7, 1, '2014-10-10 08:00:17.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (8, 8, 9, 1, '2014-10-10 08:00:34.123', '2014-10-10 08:00:50.123');
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (9, 1, 9, 1, '2014-10-10 08:00:45.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (10, 3, 10, 1, '2014-10-10 08:00:54.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (11, 9, 10, 1, '2014-10-10 08:00:23.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (12, 4, 5, 1, '2014-10-10 08:00:24.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (13, 1, 5, 1, '2014-10-10 08:00:25.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (14, 7, 8, 1, '2014-10-10 08:00:11.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (15, 7, 9, 1, '2014-10-10 08:00:21.123', '2014-10-10 08:00:22.123');
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (16, 2, 10, 1, '2014-10-10 08:00:18.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (17, 6, 10, 1, '2014-10-10 08:00:23.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (18, 2, 5, 1, '2014-10-10 08:00:21.123', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (19, 11, 12, 2, '2014-10-10 14:21:13.078', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (20, 11, 13, 2, '2014-10-10 14:21:12.078', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (21, 12, 13, 2, '2014-10-10 14:21:15.078', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (22, 13, 14, 2, '2014-10-10 14:21:16.078', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (23, 14, 15, 2, '2014-10-10 14:21:17.078', null);
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (24, 1, 2, 1, '2014-10-10 08:00:58.123', '2014-10-10 08:01:16.123');
insert into node_2_node (id, first_node_id, second_node_id, experiment_id, connection_time, disconnection_time) values (25, 1, 2, 1, '2014-10-10 08:02:17.123', null);

insert into query (id, experiment_id, node_id, deployment_time, content) values (1, 1, 1, '2014-10-10 08:00:10.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (2, 1, 1, '2014-10-10 08:00:09.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (3, 1, 1, '2014-10-10 08:00:08.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (4, 1, 1, '2014-10-10 08:00:07.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (5, 1, 1, '2014-10-10 08:00:06.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (6, 1, 1, '2014-10-10 08:00:05.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (7, 1, 1, '2014-10-10 08:00:04.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (8, 1, 1, '2014-10-10 08:00:03.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (9, 1, 2, '2014-10-10 08:00:58.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (10, 1, 2, '2014-10-10 08:00:59.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (11, 1, 2, '2014-10-10 08:01:01.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');
insert into query (id, experiment_id, node_id, deployment_time, content) values (12, 1, 2, '2014-10-10 08:01:12.070', 'select a.custId, sum(a.price + b.price) from pattern [every a=ServiceOrder -> b=ProductOrder(custId = a.custId) where timer:within(1 min)].win:time(2 hour) where a.name in (''Repair'', b.name) group by a.custId having sum(a.price + b.price) > 100');

insert into query_attribute (id, query_id, key, value) values (1, 1, 'Test attribute key 1', 'Test attribute value 1');
insert into query_attribute (id, query_id, key, value) values (2, 1, 'Test attribute key 2', 'Test attribute value 2');
insert into query_attribute (id, query_id, key, value) values (3, 1, 'Test attribute key 3', 'Test attribute value 3');
insert into query_attribute (id, query_id, key, value) values (4, 1, 'Test attribute key 4', 'Test attribute value 4');
insert into query_attribute (id, query_id, key, value) values (5, 3, 'Test attribute key 5', 'Test attribute value 5');
insert into query_attribute (id, query_id, key, value) values (6, 3, 'Test attribute key 6', 'Test attribute value 6');
insert into query_attribute (id, query_id, key, value) values (7, 3, 'Test attribute key 7', 'Test attribute value 7');
insert into query_attribute (id, query_id, key, value) values (8, 3, 'Test attribute key 8', 'Test attribute value 8');
insert into query_attribute (id, query_id, key, value) values (9, 3, 'Test attribute key 9', 'Test attribute value 9');
insert into query_attribute (id, query_id, key, value) values (10, 4, 'Test attribute key 10', 'Test attribute value 10');
insert into query_attribute (id, query_id, key, value) values (11, 5, 'Test attribute key 11', 'Test attribute value 11');

insert into query_execution (id, query_id, execution_time) values (1, 1, '2014-10-10 08:01:01.069');
insert into query_execution (id, query_id, execution_time) values (2, 1, '2014-10-10 08:02:21.001');
insert into query_execution (id, query_id, execution_time) values (3, 1, '2014-10-10 08:03:15.032');
insert into query_execution (id, query_id, execution_time) values (4, 1, '2014-10-10 08:04:34.178');
insert into query_execution (id, query_id, execution_time) values (5, 1, '2014-10-10 08:04:36.178');
insert into query_execution (id, query_id, execution_time) values (6, 2, '2014-10-11 08:04:30.178');
insert into query_execution (id, query_id, execution_time) values (7, 2, '2014-10-11 08:04:37.178');
insert into query_execution (id, query_id, execution_time) values (8, 2, '2014-10-11 08:04:39.178');
insert into query_execution (id, query_id, execution_time) values (9, 3, '2014-10-12 08:04:30.178');
insert into query_execution (id, query_id, execution_time) values (10, 3, '2014-10-12 08:04:31.178');
insert into query_execution (id, query_id, execution_time) values (11, 3, '2014-10-12 08:04:32.178');
insert into query_execution (id, query_id, execution_time) values (12, 3, '2014-10-12 08:04:33.178');
insert into query_execution (id, query_id, execution_time) values (13, 3, '2014-10-12 08:04:34.178');
insert into query_execution (id, query_id, execution_time) values (14, 4, '2014-10-13 08:04:26.178');
insert into query_execution (id, query_id, execution_time) values (15, 4, '2014-10-13 08:04:27.178');
insert into query_execution (id, query_id, execution_time) values (16, 4, '2014-10-13 08:04:28.178');
insert into query_execution (id, query_id, execution_time) values (17, 4, '2014-10-13 08:04:29.178');
insert into query_execution (id, query_id, execution_time) values (18, 4, '2014-10-13 08:04:30.178');
insert into query_execution (id, query_id, execution_time) values (19, 5, '2014-10-09 08:04:30.178');
insert into query_execution (id, query_id, execution_time) values (20, 5, '2014-10-09 08:04:31.178');
insert into query_execution (id, query_id, execution_time) values (21, 1, '2014-10-10 08:04:36.178');
insert into query_execution (id, query_id, execution_time) values (22, 1, '2014-10-10 08:04:37.178');
insert into query_execution (id, query_id, execution_time) values (23, 1, '2014-10-10 08:04:38.178');
insert into query_execution (id, query_id, execution_time) values (24, 1, '2014-10-10 08:04:39.178');
insert into query_execution (id, query_id, execution_time) values (25, 1, '2014-10-10 08:04:40.178');
insert into query_execution (id, query_id, execution_time) values (26, 1, '2014-10-10 08:04:41.178');
insert into query_execution (id, query_id, execution_time) values (27, 1, '2014-10-10 08:04:42.178');
insert into query_execution (id, query_id, execution_time) values (28, 1, '2014-10-10 08:04:43.178');
insert into query_execution (id, query_id, execution_time) values (29, 1, '2014-10-10 08:04:44.178');
insert into query_execution (id, query_id, execution_time) values (30, 1, '2014-10-10 08:04:45.178');
insert into query_execution (id, query_id, execution_time) values (31, 1, '2014-10-10 08:04:46.178');
insert into query_execution (id, query_id, execution_time) values (32, 1, '2014-10-10 08:04:47.178');
insert into query_execution (id, query_id, execution_time) values (33, 1, '2014-10-10 08:04:48.178');
insert into query_execution (id, query_id, execution_time) values (34, 1, '2014-10-10 08:04:49.178');
insert into query_execution (id, query_id, execution_time) values (35, 1, '2014-10-10 08:04:50.178');
insert into query_execution (id, query_id, execution_time) values (36, 1, '2014-10-10 08:04:51.178');
insert into query_execution (id, query_id, execution_time) values (37, 1, '2014-10-10 08:04:52.178');
insert into query_execution (id, query_id, execution_time) values (38, 1, '2014-10-10 08:04:53.178');
insert into query_execution (id, query_id, execution_time) values (39, 1, '2014-10-10 08:04:54.178');
insert into query_execution (id, query_id, execution_time) values (40, 1, '2014-10-10 08:04:55.178');
insert into query_execution (id, query_id, execution_time) values (41, 1, '2014-10-10 08:04:56.178');
insert into query_execution (id, query_id, execution_time) values (42, 1, '2014-10-10 08:04:57.178');
insert into query_execution (id, query_id, execution_time) values (43, 1, '2014-10-10 08:04:58.178');
insert into query_execution (id, query_id, execution_time) values (44, 1, '2014-10-10 08:04:59.178');
insert into query_execution (id, query_id, execution_time) values (45, 1, '2014-10-10 08:05:00.178');
insert into query_execution (id, query_id, execution_time) values (46, 1, '2014-10-10 08:05:01.178');
insert into query_execution (id, query_id, execution_time) values (47, 1, '2014-10-10 08:05:02.178');
insert into query_execution (id, query_id, execution_time) values (48, 1, '2014-10-10 08:05:02.256');
insert into query_execution (id, query_id, execution_time) values (49, 1, '2014-10-10 08:05:02.512');
insert into query_execution (id, query_id, execution_time) values (50, 1, '2014-10-10 08:05:02.750');
insert into query_execution (id, query_id, execution_time) values (52, 1, '2014-10-10 09:00:58.750');
insert into query_execution (id, query_id, execution_time) values (53, 1, '2014-10-10 09:00:58.900');
insert into query_execution (id, query_id, execution_time) values (54, 1, '2014-10-10 09:00:59.100');
insert into query_execution (id, query_id, execution_time) values (55, 1, '2014-10-10 09:00:59.750');
insert into query_execution (id, query_id, execution_time) values (56, 1, '2014-10-10 09:01:01.100');
insert into query_execution (id, query_id, execution_time) values (57, 1, '2014-10-10 09:01:01.500');
insert into query_execution (id, query_id, execution_time) values (58, 1, '2014-10-10 09:01:01.750');
insert into query_execution (id, query_id, execution_time) values (59, 1, '2014-10-10 09:01:02.100');
insert into query_execution (id, query_id, execution_time) values (60, 1, '2014-10-10 09:01:02.500');
insert into query_execution (id, query_id, execution_time) values (71, 1, '2014-10-10 09:01:02.750');
insert into query_execution (id, query_id, execution_time) values (72, 1, '2014-10-10 09:01:02.999');
insert into query_execution (id, query_id, execution_time) values (73, 1, '2014-10-10 09:01:03.000');
insert into query_execution (id, query_id, execution_time) values (74, 1, '2014-10-10 09:01:03.045');
insert into query_execution (id, query_id, execution_time) values (75, 1, '2014-10-10 09:01:03.120');
insert into query_execution (id, query_id, execution_time) values (76, 1, '2014-10-10 09:01:03.200');
insert into query_execution (id, query_id, execution_time) values (77, 1, '2014-10-10 09:01:03.450');
insert into query_execution (id, query_id, execution_time) values (78, 1, '2014-10-10 09:01:03.700');
insert into query_execution (id, query_id, execution_time) values (79, 1, '2014-10-10 09:01:03.800');
insert into query_execution (id, query_id, execution_time) values (80, 1, '2014-10-10 09:01:03.999');
