(ns cj-blog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cj-blog.controllers :as controllers]
            [selmer.parser :refer [render render-file]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(selmer.parser/set-resource-path! (clojure.java.io/resource "templates"))

(defroutes app-routes
  (GET "/" [] (render-file "home.html" (controllers/home-page)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
