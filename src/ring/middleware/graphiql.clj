(ns ring.middleware.graphiql
  "Middleware that add /graphiql endpoints"
  (:require [ring.util.response :as resp]))

(defn wrap-graphiql
  [handler]
  (fn [request]
    (let [uri (:uri request)]
      (cond
        (= "/graphiql" uri) (-> (resp/resource-response "graphiql/graphiql.html")
                                (resp/content-type "text/html"))
        (= "/graphiql.js" uri) (-> (resp/resource-response "graphiql/graphiql.js")
                                   (resp/content-type "text/javascript"))
        (= "/graphiql.css" uri) (-> (resp/resource-response "graphiql/graphiql.css")
                                    (resp/content-type "text/css"))
        :default (handler request)))))