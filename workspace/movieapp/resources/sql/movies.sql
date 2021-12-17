
-- :name get-all-movies :? :*
SELECT  * from movies


-- :name get-movie-by-id :? :1
SELECT  * from movies WHERE id = :id


-- :name insert-movie! :! :n
INSERT INTO movies(plot,director,genres,title,year,actors,id,runtime,posterUrl)
VALUES (:plot,:director,:genres,:title,:year,:actors,:id,:runtime,:posterUrl);



-- :name update-movie! :! :n
UPDATE movies
    SET plot = :plot,director = :director,genres=:genres,title=:title,year=:year ,actors=:actors,runtime=:runtime,posterUrl=:posterUrl)
WHERE id =:id


-- :name delete-movie-by-id! :! :n
DELETE FROM movies where id = :id


