CREATE TABLE rules_accidents (
id serial primary key,
fk_rules_id int not null REFERENCES rules (id),
fk_accident_id int not null REFERENCES accidents (id)
);