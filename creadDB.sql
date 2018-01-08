CREATE TABLE news
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rss_holder_id INT(11) NOT NULL,
    pub_date DATE,
    title VARCHAR(255),
    description MEDIUMTEXT,
    link VARCHAR(255),
    views INT(11) DEFAULT '0',
    CONSTRAINT fk_news_rss_holder FOREIGN KEY (rss_holder_id) REFERENCES rss_holder (id)
);
CREATE INDEX fk_news_rss_holder_idx ON news (rss_holder_id);
CREATE TABLE roles
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100)
);
CREATE TABLE rss_holder
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    creator VARCHAR(45),
    category VARCHAR(255),
    link VARCHAR(255)
);
CREATE TABLE user
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(45),
    email VARCHAR(80),
    password VARCHAR(255)
);
CREATE UNIQUE INDEX email_UNIQUE ON user (email);
CREATE UNIQUE INDEX login_UNIQUE ON user (login);
CREATE TABLE user_holder_m2m
(
    user_id INT(11) NOT NULL,
    rss_holder_id INT(11) NOT NULL,
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    CONSTRAINT fk_user_has_rss_holder_user1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_has_rss_holder_rss_holder1 FOREIGN KEY (rss_holder_id) REFERENCES rss_holder (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX fk_user_has_rss_holder_rss_holder1_idx ON user_holder_m2m (rss_holder_id);
CREATE INDEX fk_user_has_rss_holder_user1_idx ON user_holder_m2m (user_id);
CREATE TABLE user_roles_m2m
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    roles_id INT(11) NOT NULL,
    user_id INT(11) NOT NULL,
    CONSTRAINT fk_roles_has_user_roles1 FOREIGN KEY (roles_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_roles_has_user_user1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX fk_roles_has_user_roles1_idx ON user_roles_m2m (roles_id);
CREATE INDEX fk_roles_has_user_user1_idx ON user_roles_m2m (user_id);