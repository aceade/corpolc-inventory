CREATE TYPE department AS ENUM('Board', 'Finance', 'Maintenance', 'Marketing', 'PublicRelations', 'Research', 'Security', 'Shipping');
CREATE TYPE security_rating AS ENUM('MINIMUM','LOW', 'MEDIUM', 'PRIVATE', 'CONFIDENTIAL', 'HIGH', 'HIGHEST');
CREATE TYPE item_types AS ENUM('FOOD', 'ALCOHOL', 'MEDICAL_EQUIPMENT', 'TOOLS', 'OFFICE_SUPPLIES', 'WEAPONS');