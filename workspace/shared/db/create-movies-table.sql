CREATE TABLE movies(
    plot      VARCHAR(781) NOT NULL
    ,director  VARCHAR(120) NOT NULL
    ,genres    VARCHAR(117) NOT NULL
    ,title     VARCHAR(124) NOT NULL
    ,year      VARCHAR(4) NOT NULL
    ,actors    VARCHAR(157) NOT NULL
    ,id        INTEGER  NOT NULL PRIMARY KEY
    ,runtime    VARCHAR(4) NOT NULL
    ,posterUrl VARCHAR(781) NOT NULL
);
