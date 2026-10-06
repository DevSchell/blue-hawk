import re

with open('pom.xml', 'r') as f:
    content = f.read()

# Replace postgresql dependency with mysql
content = re.sub(
    r'<dependency>\s*<groupId>org\.postgresql</groupId>\s*<artifactId>postgresql</artifactId>\s*<scope>runtime</scope>\s*</dependency>',
    r'<dependency>\n\t\t\t<groupId>com.mysql</groupId>\n\t\t\t<artifactId>mysql-connector-j</artifactId>\n\t\t\t<scope>runtime</scope>\n\t\t</dependency>',
    content
)

# Remove flyway-database-postgresql
content = re.sub(
    r'<dependency>\s*<groupId>org\.flywaydb</groupId>\s*<artifactId>flyway-database-postgresql</artifactId>\s*</dependency>',
    r'',
    content
)

with open('pom.xml', 'w') as f:
    f.write(content)

