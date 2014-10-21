----------------------------------------
--- TEST data for H2 in-memory DB  -----
----------------------------------------

CREATE TABLE experiment
(
  id bigint PRIMARY KEY auto_increment,
  version int,
  name varchar(255),
  description varchar(255)
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
  experiment_id bigint,
  node_id bigint,
  FOREIGN KEY (experiment_id) REFERENCES experiment(id),
  FOREIGN KEY (node_id) REFERENCES node(id)
);

insert into experiment (id, version, name, description) values (1 , 0, 'Test experiment 1', 'Test description 1');
insert into experiment (id, version, name, description) values (2 , 0, 'Test experiment 2', 'Test description 2');

insert into node (id, version, name, description) values (1, 0, 'Test node 1', 'Node description 1');
insert into node (id, version, name, description) values (2, 0, 'Test node 2', 'Node description 2');
insert into node (id, version, name, description) values (3, 0, 'Test node 3', 'Node description 3');
insert into node (id, version, name, description) values (4, 0, 'Test node 4', 'Node description 4');
insert into node (id, version, name, description) values (5, 0, 'Test node 5', 'Node description 5');

insert into experiment_2_node (id, experiment_id, node_id) values (1,1,1);
insert into experiment_2_node (id, experiment_id, node_id) values (2,1,2);
insert into experiment_2_node (id, experiment_id, node_id) values (3,2,3);
insert into experiment_2_node (id, experiment_id, node_id) values (4,2,4);
insert into experiment_2_node (id, experiment_id, node_id) values (5,2,5);