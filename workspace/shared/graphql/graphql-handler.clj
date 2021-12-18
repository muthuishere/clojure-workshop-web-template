(ns moviesappv1.graphql.core
  (:require
    [com.walmartlabs.lacinia.util :refer [attach-resolvers]]
    [com.walmartlabs.lacinia.schema :as schema]
    [com.walmartlabs.lacinia :as lacinia]
    [clojure.data.json :as json]
    [moviesappv1.datomic.movies.handler :refer [get-all-movies insert-movie]]
    [ring.util.http-response :refer :all]
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [ring.util.http-response :refer :all]
    [mount.core :refer [defstate]]

    ))




(defstate compiled-schema
          :start
          (-> "graphql/schema.edn"
              io/resource
              slurp
              edn/read-string
              (attach-resolvers {
                                 :get-all-movies get-all-movies
                                 :insert-movie insert-movie
                                 })
              schema/compile)
          )


(defn execute-request-re-graph
  "execute request with re-graph/apollo format"
  [{:keys [variables query context]}]
  (println "execute-request-re-graph" query )
  (lacinia/execute compiled-schema query variables context))


(defn execute-request [query]
  (println "execute-request" query )
  (let [{:keys [query variables operationName]} (json/read-str query :key-fn keyword)]
    (-> (lacinia/execute compiled-schema query variables nil)
        (json/write-str))))


(defn graphql-call [req]
  (let [body (:body-params req)
        content-type (keyword (get-in req [:headers "content-type"]))]
    (case content-type
      :application/json (ok (execute-request-re-graph body))
      :application/graphql (ok (execute-request (-> req :body slurp))))))