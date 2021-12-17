-- :name register-user! :! :n
INSERT INTO USERS ( FIRST_NAME, LAST_NAME, EMAIL, ADMIN,  PASS) VALUES (
        :first_name, :last_name, :email,false,:pass );

-- :name register-admin! :! :n
INSERT INTO USERS ( FIRST_NAME, LAST_NAME, EMAIL, ADMIN,  PASS) VALUES (
        :first_name, :last_name, :email,true,:pass );


-- :name login :? :1
select FIRST_NAME, LAST_NAME, EMAIL, ADMIN from  USERS
where EMAIL =:email and pass =:pass

-- :name get-all-users :? :*
select FIRST_NAME, LAST_NAME, EMAIL, ADMIN from  USERS



