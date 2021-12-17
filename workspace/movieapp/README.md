

####HugSql Syntax


```sql


-- The following command flags are available:
--
-- :? - query with a result-set (default)
-- :! - any statement
-- :<! - support for INSERT ... RETURNING
-- :i! - support for insert and jdbc .getGeneratedKeys
-- The result flags are:
--
-- :1 - one row as a hash-map
-- :* - many rows as a vector of hash-maps
-- :n - number of rows affected (inserted/updated/deleted)
-- :raw - passthrough an untouched result (default)


```