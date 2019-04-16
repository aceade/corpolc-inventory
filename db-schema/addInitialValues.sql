INSERT INTO sites(country, region, address, security_level)
VALUES	("Ireland", "Connacht", "Delphi, Mweelrea", 1),
		("Null Island", "Middle of Nowhere", "The Buoy", 2);
		
INSERT INTO employees ("name", department_type, birthday, salary, security_rating)
VALUES	('Test User 1', 'Board', 1000, 1000, 'HIGH'),
		('Test User 2', 'Research', 2000, 500, 'HIGHEST');

INSERT INTO projects (title, summary, budget, security_level, status)
VALUES 	('Spionenkrabben', 'An attempt to train crabs as spies', 1000, 'MEDIUM', 'PROPOSED'),
		('Trololo', 'Singing trolls', 2000, 'HIGH', 'APPROVED');

INSERT INTO site_projects VALUES(1, 1),(2,2);
INSERT INTO employee_projects VALUES(2,1);

-- Only an idiot would have these passwords on their luggage! Or their account.
INSERT INTO users VALUES('test1', 'password', 1),
						('test2', '123456', 2);
INSERT INTO authorities VALUES('test1', 'ROLE_FULL_READONLY'), ('test2', 'ROLE_ADMIN');
