(ns ring.middleware.graphiql
  "Middleware that add /graphiql endpoints"
  (:require [ring.util.response :as resp]))

(defn wrap-graphiql
  [handler]
  (fn [request]
    (let [uri (:uri request)]
      (cond
        (= "/graphiql" uri) (resp/resource-response "graphiql/graphiql.html")
        (= "/graphiql.js" uri) (resp/resource-response "graphiql/graphiql.js")
        (= "/graphiql.css" uri) (resp/resource-response "graphiql/graphiql.css")
        :default (handler request)))))