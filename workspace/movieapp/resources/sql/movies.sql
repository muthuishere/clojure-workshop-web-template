
-- :name get-all-movies :? :*
SELECT  * from movies


-- :name get-movie-by-id :? :1
SELECT  * from movies WHERE id = :id


-- :name insert-movie! :! :n
INSERT INTO movies(plot,director,genres,title,year,actors,id,runtime,posterUrl)
VALUES (:plot,:director,:genres,:title,:year,:actors,:id,:runtime,:posterUrl);
