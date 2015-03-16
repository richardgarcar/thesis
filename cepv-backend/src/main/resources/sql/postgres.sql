-- Table: experiment

CREATE TABLE cepv.experiment
(
  id bigint NOT NULL,
  name character varying(255),
  description character varying(255),
  start_time timestamp without time zone,
  end_time timestamp without time zone,
  version integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_experiment PRIMARY KEY (id)
);

-- Table: node

CREATE TABLE cepv.node
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  extternal_id character varying(255) NOT NULL UNIQUE,
  name character varying(255),
  description character varying(255),
  CONSTRAINT pk_node PRIMARY KEY (id)
);

-- Table: experiment_2_node

CREATE TABLE cepv.experiment_2_node
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  experiment_id bigint,
  node_id bigint,
  addition_time timestamp without time zone NOT NULL,
  removal_time timestamp without time zone,
  CONSTRAINT pk_experiment_2_node PRIMARY KEY (id),
  CONSTRAINT fk1_experiment_2_node FOREIGN KEY (experiment_id)
      REFERENCES cepv.experiment (id),
  CONSTRAINT fk2_experiment_2_node FOREIGN KEY (node_id)
      REFERENCES cepv.node (id)
);

-- Index: fki_fk1_experiment_2_node

CREATE INDEX fki_fk1_experiment_2_node
  ON cepv.experiment_2_node
  USING btree
  (experiment_id);

-- Index: fki_fk2_experiment_2_node

CREATE INDEX fki_fk2_experiment_2_node
  ON cepv.experiment_2_node
  USING btree
  (node_id);

-- Table: node_2_node

CREATE TABLE cepv.node_2_node
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  first_node_id bigint,
  second_node_id bigint,
  experiment_id bigint,
  connection_time timestamp without time zone NOT NULL,
  disconnection_time timestamp without time zone,
  CONSTRAINT pk_node_2_node PRIMARY KEY (id),
  CONSTRAINT fk1_node_2_node FOREIGN KEY (experiment_id)
      REFERENCES cepv.experiment (id),
  CONSTRAINT fk2_node_2_node FOREIGN KEY (first_node_id)
      REFERENCES cepv.node (id),
  CONSTRAINT fk3_node_2_node FOREIGN KEY (second_node_id)
      REFERENCES cepv.node (id)
);

-- Index: fki_fk1_node_2_node

CREATE INDEX fki_fk1_node_2_node
  ON cepv.node_2_node
  USING btree
  (experiment_id);

-- Index: fki_fk2_node_2_node

CREATE INDEX fki_fk2_node_2_node
  ON cepv.node_2_node
  USING btree
  (first_node_id);

-- Index: fki_fk3_node_2_node

CREATE INDEX fki_fk3_node_2_node
  ON cepv.node_2_node
  USING btree
  (second_node_id);

-- Table: query

CREATE TABLE cepv.query
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  node_id bigint,
  deployment_time timestamp without time zone NOT NULL,
  content text NOT NULL,
  CONSTRAINT pk_query PRIMARY KEY (id),
  CONSTRAINT fk_query FOREIGN KEY (node_id)
      REFERENCES cepv.node (id)
);

-- Index: fki_query

CREATE INDEX fki_query
  ON cepv.query
  USING btree
  (node_id);

-- Table: query_attribute

CREATE TABLE cepv.query_attribute
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  query_id bigint,
  KEY character varying(255) NOT NULL,
  VALUE character varying(255) NOT NULL,
  CONSTRAINT pk_query_attribute PRIMARY KEY (id),
  CONSTRAINT fk_query_attribute FOREIGN KEY (query_id)
      REFERENCES cepv.query (id)
);

-- Index: fki_query_attribute

CREATE INDEX FKI_query_attribute
  ON cepv.query_attribute
  USING btree
  (query_id);

-- Table: query_execution

CREATE TABLE cepv.query_execution
(
  id bigint NOT NULL,
  version integer NOT NULL DEFAULT 0,
  query_id bigint,
  execution_time timestamp without time zone NOT NULL,
  CONSTRAINT pk_query_execution PRIMARY KEY (id),
  CONSTRAINT fk_query_execution FOREIGN KEY (query_id)
      REFERENCES cepv.query (id)
);

-- Index: fki_query_execution

CREATE INDEX fki_query_execution
  ON cepv.query_execution
  USING btree
  (query_id);

-- Sequence: hibernate_sequence

CREATE SEQUENCE cepv.hibernate_sequence
