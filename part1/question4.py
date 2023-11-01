################################################################################
#     ____                          __     _                          __ __
#    / __ \  __  __  ___    _____  / /_   (_)  ____    ____          / // /
#   / / / / / / / / / _ \  / ___/ / __/  / /  / __ \  / __ \        / // /_
#  / /_/ / / /_/ / /  __/ (__  ) / /_   / /  / /_/ / / / / /       /__  __/
#  \___\_\ \__,_/  \___/ /____/  \__/  /_/   \____/ /_/ /_/          /_/   
#                                                                          
#  Question 4
################################################################################
#
# Instructions:
# Question 4 and Question 5 are about writing SQL. THey use the database that is 
# created in the file `pets_db.py`. 
# These questions use a database called SQLite. You do not need to install anything.
# In the file `pets_db.py` three tables are created. Data is then added to each 
# of the tables. The questions below are about how the data in each of the tables
# is related.


# Part 4.A:
# Write SQL to select the pets that are owned by nobody.
# The output should be a list of tuples in the format: (<pet name>, <species>, <age>)
sql_pets_owned_by_nobody = """
SELECT name, species, age
FROM animals
WHERE animal_id NOT IN (SELECT pet_id FROM people_animals);
"""

# Part 4.B:
# Write SQL to select how the number of pets are older than their owners. 
# The output should be an integer.
sql_pets_older_than_owner = """
SELECT COUNT(*)
FROM animals a
JOIN people_animals pa ON a.animal_id = pa.pet_id
JOIN people p ON pa.owner_id = p.person_id
WHERE a.age > p.age;
"""

# Part 4.C: BONUS CHALLENGE! 
# Write SQL to select the pets that are owned by Bessie and nobody else.
# The output should be a list of tuples in the format: (<person name>, <pet name>, <species>)
sql_only_owned_by_bessie = """
SELECT p.name AS person_name, a.name AS pet_name, a.species
FROM animals a
JOIN people_animals pa ON a.animal_id = pa.pet_id
JOIN people p ON pa.owner_id = p.person_id
WHERE p.name = 'bessie' AND NOT EXISTS (
    SELECT 1 
    FROM people_animals pa2 
    WHERE pa2.pet_id = a.animal_id AND pa2.owner_id <> p.person_id
);
"""

# Ejecuta las consultas SQL y muestra los resultados.
if __name__ == "__main__":
    # Ejecuta la consulta 4.A
    c.execute(sql_pets_owned_by_nobody)
    pets_owned_by_nobody = c.fetchall()
    print("Mascotas sin dueño:")
    print(pets_owned_by_nobody)

    # Ejecuta la consulta 4.B
    c.execute(sql_pets_older_than_owner)
    pets_older_than_owner = c.fetchone()[0]
    print("Número de mascotas mayores que sus propietarios:", pets_older_than_owner)

    # Ejecuta la consulta 4.C
    c.execute(sql_only_owned_by_bessie)
    pets_only_owned_by_bessie = c.fetchall()
    print("Mascotas propiedad exclusiva de Bessie:")
    print(pets_only_owned_by_bessie)

    # Cierra la conexión a la base de datos.
    conn.close()